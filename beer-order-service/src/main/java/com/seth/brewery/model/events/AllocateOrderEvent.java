package com.seth.brewery.model.events;


import com.seth.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderEvent {

    private BeerOrderDto beerOrderDto;
}
