package com.sapient.inventory.reactive.service;

import com.sapient.inventory.dto.Shipment;
import com.sapient.inventory.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingReactiveService {

    private final ShippingRepository shippingRepository;

    public Shipment saveShipping(Shipment shipment) {
        log.info("Saving Shipping");
        if (shipment.getCreatedDate() == null) {
            shipment.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            shipment.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
        }
        return shippingRepository.save(shipment);
    }

    public List<Shipment> getShippings() {
        return shippingRepository.findAll();
    }
}
