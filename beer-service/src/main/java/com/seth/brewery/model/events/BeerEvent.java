package com.seth.brewery.model.events;

import com.seth.brewery.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5815577940065181210L;

    private BeerDto beerDto;
}
