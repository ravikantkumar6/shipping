package com.sapient.inventory.reactive.handler;

import com.sapient.inventory.dto.Shipment;
import com.sapient.inventory.reactive.service.ShippingReactiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingHandler {

    private final ShippingReactiveService shippingReactiveService;

    public Mono<ServerResponse> getShippings(ServerRequest request) {
        log.info("Get Shipping");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(shippingReactiveService.getShippings(), Shipment.class);
    }

    public Mono<ServerResponse> saveShipping(ServerRequest request) {
        log.info("Save Shipping");
        return request.bodyToMono(Shipment.class)
                .doOnNext(shipment -> shippingReactiveService.saveShipping(shipment))
                        .then(ServerResponse.ok().build());
    }
}
