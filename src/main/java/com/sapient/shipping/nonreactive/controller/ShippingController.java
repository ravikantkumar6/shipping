package com.sapient.shipping.nonreactive.controller;

import com.sapient.shipping.model.Shipment;
import com.sapient.shipping.model.request.OrderRequest;
import com.sapient.shipping.nonreactive.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping("/shippings")
    public ResponseEntity<List<Shipment>> getShipment() {
        return new ResponseEntity<List<Shipment>>(shippingService.getAllShipping(), HttpStatus.OK);
    }

    @PostMapping("/shipping")
    public ResponseEntity<Shipment> saveShipment(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<Shipment>(shippingService.saveShipment(orderRequest), HttpStatus.OK);
    }
}
