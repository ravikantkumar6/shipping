package com.sapient.shipping.dto;

import com.sapient.shipping.model.Shipment;
import com.sapient.shipping.model.request.OrderRequest;
import com.sapient.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShipmentDao {

    private final ShippingRepository shippingRepository;

    public Shipment saveShipment(OrderRequest orderRequest) {
        Shipment shipment = new Shipment();
        shipment.setOrderId(orderRequest.getOrderId());
        shipment.setAddress(orderRequest.getAddress());
        shipment.setShippingDate(LocalDate.now(ZoneOffset.UTC));
        shipment.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
        shipment.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
        shipment = shippingRepository.save(shipment);
        return shipment;
    }

    public List<Shipment> findAll() {
        return shippingRepository.findAll();
    }
}
