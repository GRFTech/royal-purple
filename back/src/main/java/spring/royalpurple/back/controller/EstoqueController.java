package spring.royalpurple.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.royalpurple.back.controller.http.requests.EstoqueRequest;
import spring.royalpurple.back.controller.http.responses.EstoqueResponse;
import spring.royalpurple.back.service.EstoqueService;

import java.util.List;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<EstoqueResponse> create(@Valid @RequestBody EstoqueRequest request) {
        return ResponseEntity.ok(estoqueService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<EstoqueResponse>> findAll() {
        return ResponseEntity.ok(estoqueService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody EstoqueRequest request) {
        return ResponseEntity.ok(estoqueService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estoqueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
