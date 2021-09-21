package com.sapient.shipping.reactive.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.shipping.dto.ShipmentDao;
import com.sapient.shipping.model.Shipment;
import com.sapient.shipping.model.enums.OrderStatus;
import com.sapient.shipping.model.request.OrderRequest;
import com.sapient.shipping.reactive.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingReactiveService {

    public static final String ORDER_UPDATE = "order.update";
    private final ShipmentDao shipmentDao;
    private final WebClientUtil webClientUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Shipment saveShipping(OrderRequest orderRequest, Boolean isEvent) {
        log.info("Saving Shipping");
        Shipment shipment = shipmentDao.saveShipment(orderRequest);
        updateOrderShipped(orderRequest, isEvent);
        return shipment;
    }

    private void updateOrderShipped(OrderRequest orderRequest, Boolean isEvent) {
        orderRequest.setOrderStatus(OrderStatus.SHIPPED);
        if (isEvent) {
            sendEvent(orderRequest, ORDER_UPDATE);
        } else {
            String url = "http://localhost:9001/api/order/v1/update/order";
            webClientUtil.getResponseEntityMono(url, HttpMethod.PUT, new HttpEntity<>(orderRequest), String.class);
        }
    }

    public List<Shipment> getShippings() {
        return shipmentDao.findAll();
    }

    private void sendEvent(OrderRequest orderRequest, String topic) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String message = mapper.writeValueAsString(orderRequest);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException e) {
            log.error("Exception occured in parsing the message: {}", orderRequest);
        }
    }
}
