# 📊 AI Stock Wallet - Spring Boot + Spring AI

Projeto desenvolvido com **Spring Boot** que integra dados de mercado financeiro com inteligência artificial para gerar análises inteligentes sobre uma carteira de ações.

A aplicação utiliza a API da **OpenAI** via **Spring AI** e dados de mercado da **Twelve Data API**, permitindo consultas analíticas e respostas em linguagem natural.

---

## 🚀 Tecnologias Utilizadas

O projeto foi construído com as seguintes tecnologias:

- **Spring Boot** – Framework para desenvolvimento rápido de aplicações Java.
- **Java 21** – Linguagem de programação.
- **Maven** – Automação de build e gerenciamento de dependências.
- **MySQL** – Banco de dados relacional para armazenamento da carteira de ações.
- **Flyway** – Controle de versão e migração do banco de dados.
- **Spring AI** – Integração com modelos de linguagem (LLMs).
- **Twelve Data API** – API para obtenção de preços e histórico de ações.
- **OpenAI API** – Processamento de linguagem natural e geração de insights.

---

## ⚙️ Configuração

### 1️⃣ Pré-requisitos

- Java 21 instalado
- Maven instalado
- MySQL em execução

---

### 2️⃣ Variáveis de Ambiente

Configure as seguintes variáveis no seu sistema ou no `application.yml`:

yaml

-OPEN_AI_API_KEY=your_openai_key
-TWELVE_DATA_API_KEY=your_twelve_data_key
-SENHA_MYSQL=your_mysql_root_password

Ou como variáveis de ambiente no seu sistema:

-export OPEN_AI_API_KEY=your_openai_key
-export TWELVE_DATA_API_KEY=your_twelve_data_key
-export SENHA_MYSQL=your_mysql_root_password

🔗 Endpoints da API
📈 GET /ai/wallet

Descrição:
Calcula o valor atual da carteira com base nos preços mais recentes das ações e utiliza a OpenAI para gerar uma resposta formatada.

Exemplo de Resposta:
Análise textual com o valor consolidado da carteira, podendo incluir formatação em tabela.

🛠 GET /ai/with-tools

Descrição:
Similar ao endpoint /ai/wallet, mas demonstra explicitamente o uso de Tools (Ferramentas) do Spring AI (stockTools, walletTools) antes de enviar os dados à OpenAI.

Exemplo de Resposta:
Análise textual detalhando o valor da carteira.

📅 GET /ai/highest-day/{days}

Descrição:
Determina qual foi o dia de maior valor da carteira dentro de um período especificado.

Parâmetros:

days → Número de dias a serem analisados no histórico.

Exemplo de Requisição:

GET /ai/highest-day/30

Exemplo de Resposta:
Análise textual indicando o dia em que a carteira atingiu o maior valor.

🧠 Ferramentas (Tools)

O projeto utiliza o conceito de Tools do Spring AI, permitindo que a OpenAI invoque funções automaticamente quando necessário.

🔹 StockTools

Responsável por consultar dados de mercado:

getLatestStockPrice(company)
Retorna o preço mais recente de uma ação.

getHistoricalStockPrice(days, company)
Retorna os preços históricos de uma ação durante um período específico.

🔹 WalletTools

Responsável por consultar dados da carteira do usuário:

getNumberOfShares()
Retorna a quantidade de ações de cada empresa na carteira.

