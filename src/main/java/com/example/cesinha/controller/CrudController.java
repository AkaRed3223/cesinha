package com.example.cesinha.controller;

import com.example.cesinha.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public abstract class CrudController<T, ID, R> {

    private final CrudService<T, ID, R> crudService;

    @GetMapping
    public ResponseEntity<List<T>> fetchAll() {
        return ResponseEntity.ok(crudService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> fetchById(@PathVariable ID id) {
        return ResponseEntity.ok(crudService.fetchById(id));
    }

    @PostMapping
    public ResponseEntity<T> insert(@RequestBody R request) {
        T createdEntity = crudService.insert(request);
        ID entityId = crudService.getEntityId(createdEntity);

        URI location = URI.create(String.format("/%s/%s", getResourceName(), entityId));
        return ResponseEntity.created(location).body(createdEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody R request) {
        T updatedEntity = crudService.update(id, request);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    protected abstract String getResourceName();
}


