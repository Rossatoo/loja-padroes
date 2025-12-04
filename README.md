# Loja de Pedidos – Padrões de Projeto (Factory Method, Facade, Strategy)

Este repositório contém uma aplicação web simples desenvolvida em **Java + Spring Boot + Thymeleaf** para demonstrar, na prática, o uso de três categorias de padrões de projeto:

- **Factory Method** (Criacional)  
- **Facade** (Estrutural)  
- **Strategy** (Comportamental)  

A aplicação simula uma **loja de pedidos**, permitindo:

- Cadastrar produtos (físicos e digitais);
- Listar produtos cadastrados;
- Criar pedidos com itens de produto;
- Aplicar diferentes estratégias de desconto;
- Listar pedidos realizados.

> ⚠️ Observação: todos os dados são armazenados **em memória** (listas), sem banco de dados, pois o foco é didático nos padrões de projeto.

---

## 🧰 Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web (MVC)**
- **Thymeleaf**
- **Maven**
- **Lombok** (para getters/setters em algumas classes)
- HTML + CSS (Bootstrap 5 via CDN)

---

## 🧱 Arquitetura geral

A aplicação segue uma estrutura em camadas:

- **Camada de modelo (domínio)**  
  `Produto`, `ProdutoFisico`, `ProdutoDigital`, `ItemPedido`, `Pedido`

- **Camada de serviços**  
  `ProdutoService`, `PedidoService`

- **Camada de fachada (Facade)**  
  `LojaFacade` – centraliza operações de cadastro de produtos e criação/listagem de pedidos

- **Camada web (controllers + views)**  
  - Controllers: `ProdutoController`, `PedidoController`  
  - Views: Thymeleaf (`produtos.html`, `novo-produto.html`, `novo-pedido.html`, `pedidos.html`, `pedido-detalhe.html`)

---

