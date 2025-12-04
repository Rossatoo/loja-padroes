# Loja de Pedidos – Padrões de Projeto (Factory Method, Facade, Strategy)

Aplicação web simples desenvolvida em **Java + Spring Boot + Thymeleaf** para demonstrar, na prática, o uso de três categorias de padrões de projeto:

- **Factory Method** (Criacional)  
- **Facade** (Estrutural)  
- **Strategy** (Comportamental)  

A aplicação simula uma **loja de pedidos**, permitindo:

- Cadastrar produtos (físicos e digitais);
- Listar produtos cadastrados;
- Criar pedidos com itens de produto;
- Aplicar diferentes estratégias de desconto;
- Listar pedidos realizados.

> ⚠️ Todos os dados são armazenados **em memória** (listas), sem banco de dados. O foco do projeto é didático, para a disciplina de Padrões de Projeto.

---

## 🧰 Tecnologias utilizadas

- Java 21  
- Spring Boot 3 (Spring Web MVC)  
- Thymeleaf  
- Maven  
- Lombok  
- HTML + CSS com Bootstrap 5 (via CDN)

---

## 📂 Estrutura geral do projeto

Principais pacotes (os nomes podem variar levemente):

- `modelo`  
  Classes de domínio: `Produto`, `ProdutoFisico`, `ProdutoDigital`, `ItemPedido`, `Pedido`.

- `factory`  
  Implementação do **Factory Method**:  
  `CadastroProduto` (abstrata), `CadastroProdutoFisico`, `CadastroProdutoDigital`.

- `desconto`  
  Implementação do **Strategy**:  
  `EstrategiaDesconto`, `SemDesconto`, `DescontoClienteVip`, `DescontoValorAlto`.

- `servico`  
  Regras de negócio básicas: `ProdutoService`, `PedidoService`.

- `fachada`  
  Implementação do **Facade**: `LojaFacade`, que concentra operações de cadastro de produtos e criação/listagem de pedidos.

- `controller`  
  Camada web (Spring MVC): `ProdutoController`, `PedidoController`.

- `resources/templates`  
  Páginas Thymeleaf:  
  `produtos.html`, `novo-produto.html`, `novo-pedido.html`, `pedidos.html`, `pedido-detalhe.html`.

- `resources/static/css`  
  Estilo customizado: `style.css`.

---

## 🎯 Padrões de Projeto implementados

### Factory Method (padrão criacional)

**Ideia:** encapsular a criação de objetos `Produto` em uma hierarquia de “cadastros”.

- Classe abstrata `CadastroProduto` define o método `registrarProduto(...)` e o Factory Method `criarProduto(...)`.
- `CadastroProdutoFisico` cria instâncias de `ProdutoFisico`.
- `CadastroProdutoDigital` cria instâncias de `ProdutoDigital`.

A `LojaFacade` usa essas fábricas para criar o produto certo com base no tipo informado (FÍSICO ou DIGITAL), evitando espalhar `new ProdutoFisico(...)` / `new ProdutoDigital(...)` pelo código.

---

### Facade (padrão estrutural)

**Ideia:** fornecer uma interface simples para as operações de negócio da loja.

A classe `LojaFacade`:

- Centraliza o uso de `ProdutoService` e `PedidoService`;
- Expõe métodos como:
  - `cadastrarProduto(...)`
  - `listarProdutos()`
  - `criarPedidoSimples(...)`
  - `listarPedidos()`

Os controllers (`ProdutoController`, `PedidoController`) conversam com a **fachada**, e não diretamente com todos os serviços, deixando a camada web mais limpa e menos acoplada aos detalhes internos.

---

### Strategy (padrão comportamental)

**Ideia:** encapsular diferentes algoritmos de desconto em classes separadas.

- Interface `EstrategiaDesconto` define o método `aplicarDesconto(Pedido, valorBase)`.
- Implementações:
  - `SemDesconto` – não altera o valor.
  - `DescontoClienteVip` – aplica desconto se o cliente for VIP.
  - `DescontoValorAlto` – aplica desconto se o valor do pedido passar de um limite (ex.: R$ 200,00).

O `PedidoService` recebe uma instância de `EstrategiaDesconto` e a utiliza ao calcular o total do pedido.  
O controller escolhe qual estratégia usar com base na opção selecionada na tela (nenhum, VIP, valor alto).

---

## 🌐 Funcionalidades e telas

- `GET /produtos`  
  Lista de produtos cadastrados.  
  Botões para:
  - **Cadastrar novo produto**
  - **Criar pedido**

- `GET /produtos/novo`  
  Formulário para cadastrar um novo produto (nome, tipo, preço).

- `POST /produtos`  
  Cadastra o produto em memória via `LojaFacade`.

- `GET /pedidos/novo`  
  Formulário para criar um pedido:
  - Nome do cliente;
  - Cliente VIP (checkbox);
  - Produto (selecionado a partir dos produtos cadastrados);
  - Quantidade;
  - Tipo de desconto (nenhum, cliente VIP, valor alto).

- `POST /pedidos`  
  Cria o pedido usando a `LojaFacade`, aplica a Strategy de desconto escolhida e exibe a tela `pedido-detalhe.html`.

- `GET /pedidos`  
  Lista todos os pedidos criados durante a execução da aplicação (armazenados em memória).

---

## 🚀 Como executar o projeto

### Requisitos

- Java 21 instalado  
- Maven instalado (se for rodar via linha de comando)  
  ou IntelliJ IDEA / outra IDE com suporte a Spring Boot

### Rodando pelo IntelliJ (mais simples)

1. Importar o projeto como **Maven Project**.
2. Abrir a classe principal (por exemplo, `LojaApplication`).
3. Clicar em **Run** na IDE.
4. Acessar no navegador:

   - `http://localhost:8080/produtos`
   - `http://localhost:8080/pedidos`
   - `http://localhost:8080/pedidos/novo`

### Rodando pelo terminal (Maven)

```bash
mvn spring-boot:run
