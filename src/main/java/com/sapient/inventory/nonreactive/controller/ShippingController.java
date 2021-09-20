package com.sapient.inventory.nonreactive.controller;

import com.sapient.inventory.dto.Shipment;
import com.sapient.inventory.nonreactive.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping("/get")
    public ResponseEntity<List<Shipment>> getShipment() {
        return new ResponseEntity<List<Shipment>>(shippingService.getAllShipping(), HttpStatus.OK);
    }
}
