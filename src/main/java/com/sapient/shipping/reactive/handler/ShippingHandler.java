package com.sapient.shipping.reactive.handler;

import com.sapient.shipping.model.request.OrderRequest;
import com.sapient.shipping.reactive.service.ShippingReactiveService;
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
                .bodyValue(shippingReactiveService.getShippings());
    }

    public Mono<ServerResponse> saveShipping(ServerRequest request) {
        log.info("Save Shipping");
        return request
                .bodyToMono(OrderRequest.class)
                .flatMap(orderRequest -> Mono.fromCallable(() -> this.shippingReactiveService.saveShipping(orderRequest, false)))
                .flatMap(orderHeader -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderHeader));
    }
}
