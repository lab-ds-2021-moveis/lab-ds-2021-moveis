package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ReadWriteResource<T> extends ReadResource<T> {

    @PostMapping
    public ResponseEntity<T> save(@RequestBody T entity) {
        T saved = service.save(entity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody T entity) {
        T saved = service.save(entity);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping("/{entity}")
    public ResponseEntity<Void> delete(@PathVariable("entity") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
