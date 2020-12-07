package com.seth.beer.inventory.service.services;


import com.seth.beer.inventory.service.config.JmsConfig;
import com.seth.beer.inventory.service.domain.BeerInventory;
import com.seth.beer.inventory.service.repositories.BeerInventoryRepository;
import com.seth.brewery.model.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent){
        log.debug("Got Inventory: "+ newInventoryEvent.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(newInventoryEvent.getBeerDto().getId())
                .upc(newInventoryEvent.getBeerDto().getUpc())
                .quantityOnHand(newInventoryEvent.getBeerDto().getQuantityOnHand())
                .build());
    }

}
