package com.seth.beer.order.service.services;

import com.seth.beer.order.service.domain.BeerOrder;
import com.seth.brewery.model.BeerOrderDto;

import java.util.UUID;

public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);

    void beerOrderAllocationPassed(BeerOrderDto beerOrderDto);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrderDto);

    void beerOrderAllocationFailed(BeerOrderDto beerOrderDto);

    void beerOrderPickedUp(UUID id);

    void cancelOrder(UUID id);
}
