package com.seth.beer.order.service.services.listeners;

import com.seth.beer.order.service.config.JmsConfig;
import com.seth.beer.order.service.services.BeerOrderManager;
import com.seth.brewery.model.events.ValidateOrderResultEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationOrderResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResultEvent validateOrderResultEvent) {
        final UUID beerOrderId = validateOrderResultEvent.getOrderId();
        log.debug("Validation Order Result for Order Id: "+ beerOrderId);

        beerOrderManager.processValidationResult(beerOrderId, validateOrderResultEvent.getIsValid());
    }
}
