package com.seth.beer.service.services.order;

import com.seth.beer.service.repositories.BeerRepository;
import com.seth.brewery.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto){
        AtomicInteger beersNotFound = new AtomicInteger();
        beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
            if(beerRepository.findByUpc(orderLine.getUpc()) == null){
                beersNotFound.incrementAndGet();
            }
        });

        return beersNotFound.get() == 0;
    }
}
