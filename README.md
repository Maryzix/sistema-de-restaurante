
# 🍔 Sistema de Restaurante

Sistema de gerenciamento de pedidos para um restaurante, desenvolvido em Java com foco em Programação Orientada a Objetos, princípios de organização de código e padrões de projeto.

O projeto permite criar e personalizar lanches, gerenciar pedidos, registrar pagamentos e gorjetas e realizar o fechamento financeiro do dia.

## 📌 Funcionalidades

- **Gerenciamento de pedidos:** criação, consulta, listagem, modificação e cancelamento.
- **Cardápio:** criação de X-Burger, X-Salada e X-Bacon.
- **Personalização de lanches:** remoção de ingredientes e adição de extras.
- **Controle de pagamentos:** registro de pagamentos e gorjetas.
- **Fechamento de caixa:** cálculo do faturamento, total de gorjetas e valor total recebido.
- **Controle de transações:** prevenção de registros duplicados.
- **Relatórios:** identificação de combinações de lanches e lanches-base diferentes.

## 🛠️ Tecnologias utilizadas

- Java 15
- Maven
- JUnit 5
- Lombok
- Git e GitHub

## 🏗️ Arquitetura e padrões de projeto

Durante o desenvolvimento, foram aplicados diferentes padrões de projeto para organizar as responsabilidades e facilitar a manutenção e evolução do sistema.

| Padrão | Aplicação |
|---|---|
| Factory | Criação dos diferentes tipos de lanches. |
| Decorator | Personalização dos lanches com ingredientes extras. |
| Facade | Centralização das operações do restaurante. |
| Singleton | Garantia de uma única instância do caixa. |

### Outros conceitos aplicados

- Programação Orientada a Objetos (POO).
- Encapsulamento e interfaces.
- Collections: `ArrayList`, `HashMap` e `HashSet`.
- Sobrescrita de métodos: `equals()` e `hashCode()`.
- Tratamento de exceções e validações.
- Separação de responsabilidades.
- Testes automatizados com JUnit 5.

## 📂 Estrutura do projeto

```text
src/
├── main/
│   └── java/
│       └── br/com/restaurante/
│           ├── app/
│           │   ├── Main.java
│           │   └── MenuConsole.java
│           │
│           ├── cardapio/
│           │   ├── ItemCardapio.java
│           │   ├── Lanche.java
│           │   ├── LancheFactory.java
│           │   ├── ComCheddar.java
│           │   ├── ComBaconExtra.java
│           │   ├── ComQueijoExtra.java
│           │   ├── ComAlfaceExtra.java
│           │   ├── ComTomateExtra.java
│           │   └── ComCebolaExtra.java
│           │
│           ├── pedido/
│           │   ├── Pedido.java
│           │   ├── GerenciadorDePedidos.java
│           │   └── RestauranteFacade.java
│           │
│           └── financeiro/
│               ├── Caixa.java
│               └── Transacao.java
│
└── test/
    └── java/
        └── financeiro/
            └── RestauranteBonusTest.java
```

## ⚙️ Como executar o projeto

### Pré-requisitos

- JDK 15 ou superior.
- Maven.
- IDE de sua preferência (IntelliJ IDEA, Eclipse ou VS Code).

### Clonar o repositório

```bash
git clone https://github.com/Maryzix/sistema-de-restaurante.git
```

### Acessar a pasta do projeto

```bash
cd sistema-de-restaurante
```

### Executar os testes

```bash
mvn clean test
```

### Compilar e empacotar

```bash
mvn clean package
```

Para iniciar a aplicação, execute a classe `Main.java` pela sua IDE.

## 💰 Exemplo de fechamento financeiro

O sistema registra o valor de cada pedido e sua respectiva gorjeta.

Exemplo:

| Descrição | Valor |
|---|---:|
| Faturamento dos pedidos | R$ 38,00 |
| Gorjetas recebidas | R$ 7,00 |
| **Total recebido** | **R$ 45,00** |

As transações são armazenadas em um `HashSet<Transacao>`, utilizando o número do pedido como critério de igualdade para evitar registros duplicados.

## 🧪 Testes

O projeto utiliza JUnit 5 para validar funcionalidades importantes, incluindo:

- Identificação de combinações repetidas de lanches.
- Cálculo de faturamento e gorjetas.
- Prevenção de pagamentos duplicados.
- Cancelamento de pedidos não pagos.

Para executar os testes:

```bash
mvn clean test
```

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido como parte da minha jornada de aprendizado em Java, com o objetivo de transformar conceitos teóricos de orientação a objetos e padrões de projeto em uma aplicação prática.

Mais do que implementar funcionalidades, a proposta foi trabalhar na organização do código, na separação de responsabilidades e na criação de uma estrutura que facilite futuras melhorias.

---

**Desenvolvido com Java ☕**
