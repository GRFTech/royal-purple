package spring.royalpurple.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.royalpurple.back.controller.http.requests.TipoItemRequest;
import spring.royalpurple.back.controller.http.responses.TipoItemResponse;
import spring.royalpurple.back.service.TipoItemService;

import java.util.List;

@RestController
@RequestMapping("/tipo-items")
public class TipoItemController {

    private final TipoItemService service;

    public TipoItemController(TipoItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoItemResponse> create(@Valid @RequestBody TipoItemRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TipoItemResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoItemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoItemResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody TipoItemRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
