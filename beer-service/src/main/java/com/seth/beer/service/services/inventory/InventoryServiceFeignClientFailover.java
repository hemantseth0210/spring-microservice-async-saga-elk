package com.seth.beer.service.services.inventory;

import com.seth.beer.service.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient{

    private final InventoryFailOverFeignClient inventoryFailOverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId) {
        return inventoryFailOverFeignClient.getOnhandInventory();
    }
}
