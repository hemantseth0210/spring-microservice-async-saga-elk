package com.seth.beer.order.service.statemachine.actions;

import com.seth.beer.order.service.config.JmsConfig;
import com.seth.beer.order.service.domain.BeerOrder;
import com.seth.beer.order.service.domain.BeerOrderEventEnum;
import com.seth.beer.order.service.domain.BeerOrderStatusEnum;
import com.seth.beer.order.service.repositories.BeerOrderRepository;
import com.seth.beer.order.service.services.BeerOrderManagerImpl;
import com.seth.beer.order.service.web.mappers.BeerOrderMapper;
import com.seth.brewery.model.events.ValidateOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(UUID.fromString(beerOrderId));

        beerOrderOptional.ifPresentOrElse(beerOrder -> {
            jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, ValidateOrderEvent.builder()
                    .beerOrderDto(beerOrderMapper.beerOrderToDto(beerOrder))
                    .build());
        }, () -> log.error("Order Not Found. Id: " + beerOrderId));

        log.debug("Sent Validate Order Event to queue for Order Id: " + beerOrderId);
    }
}
