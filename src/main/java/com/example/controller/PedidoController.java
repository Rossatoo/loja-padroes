package com.example.controller;

import com.example.desconto.DescontoClienteVip;
import com.example.desconto.DescontoValorAlto;
import com.example.desconto.EstrategiaDesconto;
import com.example.desconto.SemDesconto;
import com.example.facade.LojaFacade;
import com.example.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final LojaFacade lojaFacade;

    public PedidoController(LojaFacade lojaFacade) {
        this.lojaFacade = lojaFacade;
    }

    @GetMapping
    public String listarPedidos(Model model){
        model.addAttribute("pedidos", lojaFacade.listarPedidos());
        return "pedidos";
    }

    @GetMapping("/novo")
    public String novoPedidoForm(Model model) {
        model.addAttribute("produtos", lojaFacade.listarProdutos());
        return "novo-pedido.html";
    }

    @PostMapping
    public String criarPedido(@RequestParam String cliente,
                              @RequestParam(defaultValue = "false") boolean vip,
                              @RequestParam String nomeProduto,
                              @RequestParam int quantidade,
                              @RequestParam String tipoDesconto,
                              Model model) {

        EstrategiaDesconto estrategia = switch(tipoDesconto) {
            case "VIP" -> new DescontoClienteVip();
            case "VALOR_ALTO" -> new DescontoValorAlto();
            default -> new SemDesconto();
        };

        Pedido pedido = lojaFacade.criarPedidoSimples(cliente, vip, nomeProduto, quantidade, estrategia);

        model.addAttribute("pedido", pedido);
        return "pedido-detalhe";
    }
}
