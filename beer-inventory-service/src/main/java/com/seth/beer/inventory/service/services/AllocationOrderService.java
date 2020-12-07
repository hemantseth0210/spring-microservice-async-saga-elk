package com.seth.beer.inventory.service.services;

import com.seth.brewery.model.BeerOrderDto;

public interface AllocationOrderService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
    void deallocateOrder(BeerOrderDto beerOrderDto);
}
