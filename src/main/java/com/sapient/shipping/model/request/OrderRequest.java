package com.sapient.shipping.model.request;

import com.sapient.shipping.model.Address;
import com.sapient.shipping.model.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderRequest {
    private String OrderDetail;
    private Address address;
    private String productId;
    private String orderId;
    private OrderStatus orderStatus;
}
