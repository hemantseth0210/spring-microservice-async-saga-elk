package com.seth.beer.service.services.order;

import com.seth.beer.service.config.JmsConfig;
import com.seth.brewery.model.events.ValidateOrderEvent;
import com.seth.brewery.model.events.ValidateOrderResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderEvent validateOrderEvent){
        Boolean isValid = beerOrderValidator.validateOrder(validateOrderEvent.getBeerOrderDto());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResultEvent.builder()
                .orderId(validateOrderEvent.getBeerOrderDto().getId())
                .isValid(isValid)
                .build());
    }
}
