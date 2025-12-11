# Wallet Watcher

O Wallet Watcher é uma solução completa de gerenciamento financeiro pessoal desenvolvida com arquitetura Full Stack. A aplicação oferece ferramentas para monitoramento de fluxo de caixa, acompanhamento de taxas de câmbio globais em tempo real, gestão de portfólio de investimentos com cálculo automático de juros compostos e um consultor financeiro personalizado impulsionado por Inteligência Artificial Generativa.

## Índice

- [Visão Geral](#visão-geral)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Stack Tecnológica](#stack-tecnológica)
- [Arquitetura](#arquitetura)
- [Integrações Externas](#integrações-externas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Endpoints da API](#endpoints-da-api)
- [Autor](#autor)

## Visão Geral

Este projeto foi desenvolvido com o objetivo de demonstrar a implementação de uma API RESTful robusta utilizando Java Spring Boot, integrada a uma interface frontend desacoplada. O sistema endereça necessidades complexas de gestão financeira, como rastreamento de despesas, simulações de conversão monetária e projeções de investimentos em renda fixa baseadas em indicadores econômicos oficiais (Selic/CDI).

## Funcionalidades Principais

* **Dashboard Financeiro:** Visualização em tempo real do saldo, receitas e despesas através de gráficos de rosca dinâmicos para análise de saúde financeira.
* **Gestão de Transações:** Operações completas de CRUD (Criação, Leitura, Atualização e Remoção) para registros financeiros.
* **Câmbio Global:** Monitoramento em tempo real das principais moedas mundiais (USD, EUR, GBP, JPY, entre outras) em relação ao Real (BRL), incluindo simulador de conversão integrado.
* **Portfólio de Investimentos:**
    * Gestão de ativos de renda fixa (CDB, LCI, Tesouro Direto, Debêntures).
    * Cálculo automático de juros compostos baseado na data de aporte do ativo.
    * Integração com indicadores econômicos oficiais para estimativa de rendimentos.
    * Lógica de resgate que credita automaticamente os rendimentos ao saldo principal.
* **Consultor Financeiro via IA:** Integração com o Google Gemini AI para analisar o histórico de transações do usuário e fornecer insights acionáveis e conselhos financeiros em linguagem natural.

## Stack Tecnológica

### Backend
* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.x
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL (Produção) / H2 Database (Desenvolvimento)
* **Gerenciamento de Dependências:** Maven
* **Containerização:** Docker (Dockerfile incluso)

### Frontend
* **Estrutura:** HTML5
* **Estilização:** CSS3 (Design Responsivo Personalizado)
* **Lógica:** Vanilla JavaScript (ES6+)
* **Visualização de Dados:** Chart.js
* **Ícones:** Phosphor Icons

## Arquitetura

A aplicação segue o padrão de arquitetura MVC (Model-View-Controller) adaptado para APIs REST:

1.  **Camada Controller:** Gerencia as requisições HTTP e define os endpoints.
2.  **Camada Service/Repository:** Gerencia a regra de negócio e interações com o banco de dados via Spring Data JPA.
3.  **Camada Model:** Representa as entidades de dados (Despesa, Investimento) mapeadas para o banco relacional.
4.  **Camada Client:** O frontend consome a API de forma assíncrona utilizando a Fetch API.

## Integrações Externas

O sistema integra-se com três serviços externos distintos para fornecer dados em tempo real:

1.  **AwesomeAPI:** Utilizada para obter taxas de câmbio em tempo real para moedas globais.
2.  **Banco Central do Brasil (SGS):** Recupera indicadores econômicos oficiais (Meta Selic) para cálculo dinâmico do CDI e rendimentos da poupança.
3.  **Google Gemini AI:** Processa dados financeiros anonimizados para gerar relatórios de consultoria personalizados.

## Pré-requisitos

* Java Development Kit (JDK) 17 ou superior
* Maven 3.6+
* PostgreSQL (Opcional, caso utilize H2)
* Git

## Instalação e Execução

1.  **Clonar o repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO/wallet-watcher.git](https://github.com/SEU_USUARIO/wallet-watcher.git)
    cd wallet-watcher
    ```

2.  **Configurar Variáveis de Ambiente:**
    Abra o arquivo `src/main/resources/application.properties` e configure as credenciais do seu banco de dados. Para as funcionalidades de IA, insira sua Chave de API do Google Gemini no arquivo `AdvisorController.java` (ou configure como variável de ambiente do sistema).

3.  **Compilar o projeto:**
    ```bash
    mvn clean install
    ```

4.  **Executar a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

5.  **Acessar a aplicação:**
    Abra seu navegador e navegue para `http://localhost:8080`.

## Endpoints da API

### Transações
* `GET /despesas`: Retorna todas as transações.
* `POST /despesas`: Cria uma nova transação.

### Investimentos
* `GET /investimentos`: Retorna todos os investimentos ativos com valores atuais calculados.
* `POST /investimentos`: Cria um novo investimento (deduzindo do saldo principal).
* `DELETE /investimentos/{id}`: Realiza o resgate do investimento (creditando saldo com rendimentos).

### Dados Externos & IA
* `GET /cambio`: Retorna taxas de câmbio atuais.
* `GET /indicadores`: Retorna taxas oficiais (Selic, CDI).
* `GET /advisor`: Aciona a análise de IA do estado financeiro atual.

---

## Autor

**Pedro Ferreira**
