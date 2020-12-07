package com.seth.beer.inventory.service.services;

import com.seth.beer.inventory.service.config.JmsConfig;
import com.seth.brewery.model.events.AllocateOrderEvent;
import com.seth.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationOrderListener {

    private final AllocationOrderService allocationOrderService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderEvent allocateOrderEvent) {
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.beerOrderDto(allocateOrderEvent.getBeerOrderDto());

        try {
            Boolean allocationResult = allocationOrderService.allocateOrder(allocateOrderEvent.getBeerOrderDto());
            if (allocationResult) {
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }
            builder.allocationError(false);
        } catch (Exception e) {
            log.error("Allocation failed for Order Id: " + allocateOrderEvent.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
