package controllers;

import Services.ProdutoService;
import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity salvar(Produto produto) {
        var produtoA =  produtoService.salvar(produto);
        URI uri = URI.create("/produtos/" + produtoA.getId());
        return ResponseEntity.created(uri).body(produtoA);
    }

    @GetMapping
    public ResponseEntity listar() {
        Optional<List<Produto>> produtos = Optional.ofNullable(produtoService.listar());
        if (produtos.isPresent()) {
            return ResponseEntity.ok(produtos.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(Long id) {
        Optional<Produto> produto = Optional.ofNullable(produtoService.buscar(id));
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(Long id, Produto produto) {
        Optional<Produto> produtoAtualizado = Optional.ofNullable(produtoService.atualizar(id, produto));
        if (produtoAtualizado.isPresent()) {
            return ResponseEntity.ok(produtoAtualizado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(Long id) {
        produtoService.remover(id);
        return ResponseEntity.ok().build();
    }
}
