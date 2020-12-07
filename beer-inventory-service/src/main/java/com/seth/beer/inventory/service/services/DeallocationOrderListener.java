package com.seth.beer.inventory.service.services;

import com.seth.beer.inventory.service.config.JmsConfig;
import com.seth.brewery.model.events.DeallocateOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationOrderListener {

    private final AllocationOrderService allocationOrderService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderEvent deallocateOrderEvent) {
        allocationOrderService.deallocateOrder(deallocateOrderEvent.getBeerOrderDto());
    }

}
