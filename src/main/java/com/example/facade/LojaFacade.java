package com.example.facade;

import com.example.desconto.EstrategiaDesconto;
import com.example.factory.CadastroProdutoDigital;
import com.example.factory.CadastroProdutoFisico;
import com.example.model.ItemPedido;
import com.example.model.Pedido;
import com.example.model.Produto;
import com.example.service.PedidoService;
import com.example.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LojaFacade {

    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    public LojaFacade(ProdutoService produtoService, PedidoService pedidoService) {
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
    }

    // PRODUTOS

    public void cadastrarProduto(String nome, String tipo, BigDecimal preco){
        Produto produto;

        if("FISICO".equalsIgnoreCase(tipo)) {
            CadastroProdutoFisico cadastro = new CadastroProdutoFisico();
            produto = cadastro.registrarProduto(nome, preco);
        } else if ("DIGITAL".equalsIgnoreCase(tipo)) {
            CadastroProdutoDigital cadastro = new CadastroProdutoDigital();
            produto = cadastro.registrarProduto(nome, preco);
        } else {
            throw new IllegalArgumentException("Tipo de Produto invalido: " + tipo);
        }

        produtoService.adicionar(produto);
    }

    public List<Produto> listarProdutos(){
        return produtoService.listarTodos();
    }

    // PEDIDOS

    public Pedido criarPedidoSimples(String cliente,
                                     boolean vip,
                                     String nomeProduto,
                                     int quantidade,
                                     EstrategiaDesconto estrategiaDesconto){
        Produto produto = produtoService.buscarPorNome(nomeProduto);

        Pedido pedido = new Pedido(cliente, vip);
        pedido.adicionarItem(new ItemPedido(produto, quantidade));

        pedidoService.setEstrategiaDesconto(estrategiaDesconto);
        pedidoService.calcularTotal(pedido);
        pedidoService.registrar(pedido);

        return pedido;
    }

    public List<Pedido> listarPedidos(){
        return pedidoService.listarTodos();
    }
}
