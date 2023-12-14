package com.bugvoyage.bugcollection.controller;

import com.bugvoyage.bugcollection.ResourceNotFoundException;
import com.bugvoyage.bugcollection.model.Beetle;
import com.bugvoyage.bugcollection.model.User;
import com.bugvoyage.bugcollection.service.BeetleService;
import com.bugvoyage.bugcollection.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/beetles")
public class BeetleController {

    private final BeetleService beetleService;
    private final UserService userService; // Добавлен сервис для работы с пользователями

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
    @PostMapping("/{userId}") // Добавлен путь для получения ID пользователя
    public ResponseEntity<Beetle> createBeetle(@PathVariable(value = "userId") Integer userId, // Получение ID пользователя из пути
                                               @RequestBody Beetle beetle) {
        User user = userService.findUserById(userId) // Получение пользователя по ID
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        beetle.setUser(user); // Установка пользователя для жука
        Beetle savedBeetle = beetleService.saveBeetle(beetle);
        return ResponseEntity.ok(savedBeetle); // Возвращаем сохраненного жука
    }

    // Обновление информации о жуке
    @PutMapping("/{id}")
    public ResponseEntity<Beetle> updateBeetle(@PathVariable(value = "id") Integer beetleId,
                                               @RequestBody Beetle beetleDetails) {
        Beetle updatedBeetle = beetleService.updateBeetle(beetleId, beetleDetails)
                .orElseThrow(() -> new ResourceNotFoundException("Beetle not found with id " + beetleId));
        return ResponseEntity.ok(updatedBeetle);
    }

    // Удаление жука
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeetle(@PathVariable(value = "id") Integer beetleId) {
        boolean deleted = beetleService.deleteBeetle(beetleId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}