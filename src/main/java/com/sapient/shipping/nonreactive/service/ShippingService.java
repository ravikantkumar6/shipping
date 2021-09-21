package com.sapient.shipping.nonreactive.service;

import com.sapient.shipping.model.Shipment;
import com.sapient.shipping.model.request.OrderRequest;

import java.util.List;

public interface ShippingService {
    List<Shipment> getAllShipping();

    Shipment saveShipment(OrderRequest orderRequest);
}
