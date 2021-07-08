package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web.rest;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.ListDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

public class ReadResource<T> {

    @Autowired
    BaseService<T> service;

    @GetMapping
    public ResponseEntity<ListDTO<T>> findAll() {
        List<T> list = service.findAll();
        return ResponseEntity.ok(new ListDTO<T>(list));
    }

    @GetMapping("/{entity}")
    public ResponseEntity<T> find(@PathVariable("entity") Long id) {
        Optional<T> result = service.find(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }

}
