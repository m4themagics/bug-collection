package com.bugvoyage.bugcollection.controller;

import com.bugvoyage.bugcollection.model.Beetle;
import com.bugvoyage.bugcollection.service.BeetleService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/beetles")
public class BeetleController {

    @NotNull
    private final BeetleService beetleService;

    // Получение списка всех жуков
    @GetMapping
    public List<Beetle> getAllBeetles() {
        return beetleService.findAllBeetles();
    }

    // Получение жука по ID
    @GetMapping("/{id}")
    public ResponseEntity<Beetle> getBeetleById(@PathVariable(value = "id") Integer beetleId) {
        return beetleService.findBeetleById(beetleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового жука
    @PostMapping
    public Beetle createBeetle(@RequestBody Beetle beetle) {
        return beetleService.saveBeetle(beetle);
    }

    // Обновление информации о жуке
    @PutMapping("/{id}")
    public ResponseEntity<Beetle> updateBeetle(@PathVariable(value = "id") Integer beetleId,
                                               @RequestBody Beetle beetleDetails) {
        return beetleService.updateBeetle(beetleId, beetleDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление жука
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeetle(@PathVariable(value = "id") Integer beetleId) {
        return beetleService.deleteBeetle(beetleId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}