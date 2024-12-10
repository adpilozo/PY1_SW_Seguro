package com.salazar.inventoryservice.controller;

import com.salazar.inventoryservice.dto.InventoryRequest;
import com.salazar.inventoryservice.dto.InventoryResponse;
import com.salazar.inventoryservice.model.Inventory;
import com.salazar.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping
    public ResponseEntity<InventoryResponse> addProductToInventory(@RequestBody InventoryRequest inventoryRequest) {
        // Crear el objeto de inventario
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());

        // Guardar el producto en el repositorio
        inventoryRepository.save(inventory);

        // Crear la respuesta
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setSkuCode(inventory.getSkuCode());
        inventoryResponse.setIsInStock(inventory.getQuantity() > 0);

        // Retornar la respuesta
        return new ResponseEntity<>(inventoryResponse, HttpStatus.CREATED);
    }
}
