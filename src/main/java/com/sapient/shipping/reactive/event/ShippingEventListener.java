package com.sapient.shipping.reactive.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.shipping.model.request.OrderRequest;
import com.sapient.shipping.reactive.service.ShippingReactiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingEventListener {

    public static final String SHIPPING_CREATE = "shipping.create";

    private final ShippingReactiveService shippingReactiveService;

    @KafkaListener(topics = SHIPPING_CREATE, groupId = "SHIPPING")
    public void createShipping(ConsumerRecord<String, Message> message) {
        try {
            log.info("Create Shipping : " + message.value());
            ObjectMapper mapper = new ObjectMapper();
            shippingReactiveService.saveShipping(mapper.readValue(String.valueOf(message.value()), OrderRequest.class), true);
        } catch (Exception ex) {
            log.error("Exception occured in parsing the message: " + ex);
        }
    }
}
