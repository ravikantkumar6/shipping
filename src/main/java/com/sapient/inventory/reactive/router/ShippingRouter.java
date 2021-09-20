package com.sapient.inventory.reactive.router;

import com.sapient.inventory.dto.Shipment;
import com.sapient.inventory.reactive.handler.ShippingHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class ShippingRouter {

    @Bean
    @RouterOperations({@RouterOperation(path = "/shippings", beanClass = Shipment.class, beanMethod = "getShipping"),
            @RouterOperation(path = "/shipping", beanClass = Shipment.class, beanMethod = "saveShipping",
                    operation = @Operation(
                            operationId = "saveShipping",
                            requestBody = @RequestBody(required = true, description = "Enter Request body as Json Object",
                                    content = @Content(
                                            schema = @Schema(implementation = Shipment.class)))))
    })
    public RouterFunction<ServerResponse> route(ShippingHandler shippingHandler) {

        return RouterFunctions
                .route(GET("/shippings").and(accept(MediaType.APPLICATION_JSON)), shippingHandler::getShippings)
                .andRoute(POST("/shipping").and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON)), shippingHandler::saveShipping);
    }
}
