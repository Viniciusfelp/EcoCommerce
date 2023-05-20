package Services;

import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import repositories.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscar(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void remover(Long id) {
        if(produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }

    public Produto atualizar(Long id, Produto produto) {
        Produto produtoAtual = produtoRepository.findById(id).orElse(null);
        if (produtoAtual == null) {
            return null;
        }
        produtoAtual.setNome(produto.getNome());
        produtoAtual.setDescricao(produto.getDescricao());
        produtoAtual.setPreco(produto.getPreco());
        produtoAtual.setQuantidade(produto.getQuantidade());
        return produtoRepository.save(produtoAtual);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }


}
