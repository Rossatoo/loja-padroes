package com.example.controller;


import com.example.facade.LojaFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

    private final LojaFacade lojaFacade;

    public ProdutoController(LojaFacade lojaFacade) {
        this.lojaFacade = lojaFacade;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("produtos", lojaFacade.listarProdutos());
        return "produtos";
    }

    @GetMapping("/novo")
    public String novoForm(){
        return "novo-produto";
    }

    @PostMapping
    public String salvar(@RequestParam String nome,
                         @RequestParam String tipo,
                         @RequestParam BigDecimal preco) {
        lojaFacade.cadastrarProduto(nome, tipo, preco);
        return "redirect:/produtos";
    }
}
