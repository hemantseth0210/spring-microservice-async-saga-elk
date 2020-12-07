package com.seth.beer.order.service.statemachine.actions;

import com.seth.beer.order.service.config.JmsConfig;
import com.seth.beer.order.service.domain.BeerOrderEventEnum;
import com.seth.beer.order.service.domain.BeerOrderStatusEnum;
import com.seth.beer.order.service.services.BeerOrderManagerImpl;
import com.seth.brewery.model.events.AllocationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_FAILURE_QUEUE, AllocationFailureEvent.builder()
                .orderId(UUID.fromString(beerOrderId))
                .build());
        log.debug("Sent Allocation Failure Event to the queue for order Id: "+ beerOrderId);
    }
}
