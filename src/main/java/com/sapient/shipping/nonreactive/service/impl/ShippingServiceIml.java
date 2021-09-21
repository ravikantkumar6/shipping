package com.sapient.shipping.nonreactive.service.impl;

import com.sapient.shipping.dto.ShipmentDao;
import com.sapient.shipping.model.Shipment;
import com.sapient.shipping.model.enums.OrderStatus;
import com.sapient.shipping.model.request.OrderRequest;
import com.sapient.shipping.nonreactive.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingServiceIml implements ShippingService {

    private final ShipmentDao shipmentDao;
    private final RestTemplate restTemplate;

    @Override
    public List<Shipment> getAllShipping() {
        return shipmentDao.findAll();
    }

    @Override
    public Shipment saveShipment(OrderRequest orderRequest) {
        Shipment shipment = shipmentDao.saveShipment(orderRequest);
        orderRequest.setOrderStatus(OrderStatus.SHIPPED);
        String url = "http://localhost:9001/api/order/v1/rest/order";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(orderRequest), String.class);
        return shipment;
    }
}
