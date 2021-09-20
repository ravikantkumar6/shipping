package com.sapient.inventory.nonreactive.service.impl;

import com.sapient.inventory.dto.Shipment;
import com.sapient.inventory.nonreactive.service.ShippingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShippingServiceIml implements ShippingService {

    @Override
    public List<Shipment> getAllShipping() {
        return null;
    }
}
