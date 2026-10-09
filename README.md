# 🏦 CupBank — Aplicativo Bancário Android

<div align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2026.02.01-4285F4?logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-Yes-757575?logo=materialdesign&logoColor=white)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24-3DDC84?logo=android&logoColor=white)
![Target SDK](https://img.shields.io/badge/Target%20SDK-37-3DDC84?logo=android&logoColor=white)

**Aplicativo de banco fictício completo desenvolvido em Kotlin + Jetpack Compose com Material 3**

</div>

---

## 📱 Sobre o Projeto

CupBank é um aplicativo bancário moderno e completo que simula as principais funcionalidades de um banco digital. Desenvolvido como trabalho acadêmico (Trabalho 2 - Navegação + Listas + Detalhes), o app demonstra implementação de navegação complexa, gerenciamento de estado e integração entre múltiplas telas.

**⚠️ Nota:** Todos os dados vivem apenas na memória usando `mutableStateListOf` e `mutableStateOf`, conforme especificação do projeto acadêmico.

---

## ✨ Funcionalidades Principais

### 💰 Conta Bancária
- **Saldo em tempo real** com atualização automática
- **Extrato completo** de todas as transações
- **Transferências PIX** com validação de chave e saldo
- **Recebimento de PIX** com atualização instantânea do saldo

### 💳 Cartão de Crédito
- **Gerenciamento de transações** do cartão
- **Adicionar gastos** manualmente
- **Remover transações** indesejadas
- **Pagamento de fatura** integrado com o saldo da conta
- **Detalhamento individual** de cada transação

### 📈 Investimentos
- **Listagem de ativos** (Ações e Fundos)
- **Adicionar novos ativos** com ticker, nome, preço e tipo
- **Remover ativos** segurando o item
- **Detalhamento completo** de cada ativo:
  - Preço de abertura calculado
  - Variação do dia em R$
  - Peso na carteira
  - Gestão de ordens (compra/venda)
  - Confirmação/cancelamento de ordens

### 👤 Perfil
- Informações do usuário
- Acesso rápido aos cartões
- Configurações da conta

---

## 🚀 Como Rodar o Projeto

### 📋 Pré-requisitos

- **Android Studio** Ladybug ou superior (recomendado: versão mais recente)
- **JDK 11** ou superior
- **SDK do Android** com API Level 24+
- **Emulador Android** ou **dispositivo físico** (Android 7.0+)
- **Conexão com internet** (apenas para o primeiro Gradle sync)

### 🔧 Passos para Instalação

#### 1️⃣ Clone o Repositório

```bash
git clone https://github.com/seu-usuario/CupBank.git
cd CupBank/CupBank-A2
```

#### 2️⃣ Abra no Android Studio

1. Abra o Android Studio
2. Vá em **File → Open**
3. Selecione a pasta `CupBank-A2` (que contém o arquivo `settings.gradle.kts`)
4. Aguarde o **Gradle Sync** terminar (pode levar alguns minutos na primeira vez)

#### 3️⃣ Configure o Dispositivo

**Opção A - Emulador:**
1. Vá em **Device Manager** (ícone de celular na barra lateral)
2. Crie um novo dispositivo virtual (recomendado: Pixel 6, API 34+)
3. Inicie o emulador

**Opção B - Dispositivo Físico:**
1. Ative **Opções do Desenvolvedor** no seu Android
2. Habilite **Depuração USB**
3. Conecte via USB e autorize o computador

#### 4️⃣ Execute o App

1. Selecione o dispositivo/emulador na barra de ferramentas
2. Clique no botão **Run** ▶️ (ou pressione `Shift + F10`)
3. Aguarde a compilação e instalação

### 🖥️ Executar via Linha de Comando

```bash
# Conceder permissão ao gradlew (Linux/Mac)
chmod +x gradlew

# Compilar o projeto
./gradlew build

# Instalar no dispositivo conectado
./gradlew installDebug

# Ou compilar e instalar em um comando
./gradlew assembleDebug && adb install app/build/outputs/apk/debug/app-debug.apk
```

### 🐛 Solução de Problemas

| Problema | Solução |
|----------|---------|
| **Gradle Sync falhou** | Verifique conexão com internet, delete a pasta `.gradle` e sincronize novamente |
| **SDK não encontrado** | Vá em **File → Project Structure** e configure o SDK correto |
| **App não compila** | Execute **Build → Clean Project** depois **Build → Rebuild Project** |
| **Emulador lento** | Certifique-se de ter aceleração de hardware habilitada (HAXM/KVM) |

---

## 🏗️ Arquitetura e Estrutura

### 🗺️ Mapa de Navegação (8 Telas)

| Tela | Rota | Acesso |
|------|------|--------|
| **🏠 Início** | `inicio` | Aba **Início** (tela inicial) |
| **📊 Extrato** | `extrato` | Aba **Extrato** ou "Ver tudo" no Início |
| **📈 Investir** | `investir` | Aba **Investir** ou botão central **+** |
| **📉 Detalhe do Ativo** | `detalhe_ativo/{ticker}` | Tocar num ativo da lista |
| **👤 Perfil** | `perfil` | Aba **Perfil** |
| **💳 Cartão** | `cartao` | Perfil → **Cartões** ou Início → **Pagar** |
| **🧾 Detalhe da Transação** | `cartao_transacao/{id}` | Tocar numa transação do cartão |
| **💸 Transferência PIX** | `pix` | Início → **PIX** ou **Transferir** |

**📍 Nota:** As rotas ficam centralizadas no objeto `Rotas` (`Rotas.kt`) e o `NavHost` em `CupBankApp.kt`.

### 📂 Estrutura do Código

```
app/src/main/java/com/example/cupbank/
├── MainActivity.kt                    # Activity principal
├── CupBankApp.kt                      # NavHost e configuração de navegação
├── Rotas.kt                           # Definição de todas as rotas
├── CupBankBottomBar.kt               # Barra de navegação inferior
├── Banco.kt                           # Estado do banco (saldo + extrato)
├── BankAppScreen.kt                   # Tela inicial
├── ExtratoScreen.kt                   # Tela de extrato completo
├── TelaTransferenciaPix.kt           # Tela de transferência PIX
├── TelaCartao.kt                      # Tela do cartão + detalhe transação
├── ProfileScreen.kt                   # Tela de perfil
├── investimentos/
│   ├── InvestirScreen.kt             # Lista de ativos
│   ├── DetalheAtivoScreen.kt         # Detalhamento de ativo
│   ├── InvestimentosState.kt         # Estado dos investimentos
│   ├── Modelos.kt                     # Data classes (Ativo, Ordem)
│   ├── Componentes.kt                # Componentes reutilizáveis
│   └── Formatacao.kt                 # Funções de formatação
└── ui/theme/
    ├── Theme.kt                       # Tema Material 3
    ├── Color.kt                       # Paleta de cores
    └── Type.kt                        # Tipografia
```

### 🔄 Gerenciamento de Estado

O app utiliza **três estados principais** criados em `CupBankApp` e compartilhados entre as telas:

1. **`BancoState`** → Saldo e movimentos do extrato
2. **`CartaoState`** → Transações do cartão
3. **`InvestimentosState`** → Ativos e ordens de investimento

Essa abordagem garante que:
- ✅ O estado sobrevive à troca de abas
- ✅ As telas ficam sincronizadas
- ✅ O extrato reflete ações de PIX e pagamento de fatura

### 🔗 Integração Entre Telas

| Ação | Impacto |
|------|---------|
| **Receber PIX** (Início) | ➜ Soma no saldo + cria movimento "PIX recebido" no Extrato |
| **Enviar PIX** (PIX) | ➜ Valida saldo, debita valor + cria "PIX enviado" no Extrato |
| **Pagar Fatura** (Cartão) | ➜ Debita saldo, quita transações + cria "Fatura do cartão" no Extrato |

---

## 🛠️ Tecnologias Utilizadas

### Core
- **[Kotlin 2.2.10](https://kotlinlang.org/)** - Linguagem de programação
- **[Android Gradle Plugin 9.3.2](https://developer.android.com/build)** - Sistema de build

### UI & Design
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** - Framework UI declarativo
- **[Material 3](https://m3.material.io/)** - Sistema de design
- **[Compose BOM 2026.02.01](https://developer.android.com/jetpack/compose/bom)** - Versionamento de bibliotecas Compose

### Navegação
- **[Navigation Compose 2.9.8](https://developer.android.com/jetpack/compose/navigation)** - Navegação entre telas
- **Bottom Navigation Bar** - Navegação por abas

### Arquitetura & Estado
- **[Compose State Management](https://developer.android.com/jetpack/compose/state)** - Gerenciamento de estado
- **Single Source of Truth** - Estado centralizado

### Bibliotecas AndroidX
- **Core KTX 1.19.0** - Extensões Kotlin para Android
- **Activity Compose 1.13.0** - Integração Activity + Compose
- **Lifecycle Runtime KTX 2.11.0** - Ciclo de vida

### Testes
- **JUnit 4.13.2** - Testes unitários
- **Espresso 3.7.0** - Testes de UI
- **Compose UI Test** - Testes de componentes

---

## 📚 Requisitos Acadêmicos Atendidos

### ✅ Navegação
- [x] **NavigationBar** com 4 abas + botão central "+"
- [x] **NavHost** com 8 destinos configurados
- [x] **Navegação por botões** usando `navController.navigate()`
- [x] **TopAppBar** com botão de voltar em telas de detalhe
- [x] **Argumentos de navegação** (ticker de ativo, ID de transação)

### ✅ Listas e Cards
- [x] **2 listas com LazyColumn + Card:**
  - Lista de ativos de investimento
  - Lista de transações do cartão
- [x] **Adicionar itens** via formulários
- [x] **Remover itens** (long press ou botão de exclusão)

### ✅ Data Classes e Modelos
- [x] **3 data classes criadas:**
  - `Movimento` (extrato bancário) em `Banco.kt`
  - `Ativo` + `Ordem` (investimentos) em `investimentos/Modelos.kt`
  - `TransacaoCartao` (cartão de crédito) em `TelaCartao.kt`

### ✅ Telas de Detalhe
- [x] **2 telas de detalhamento:**
  - Detalhe do Ativo (com cálculos complexos)
  - Detalhe da Transação do Cartão
- [x] **Busca de item por argumento de rota**
- [x] **Complexidade extra:** Cálculos de patrimônio, peso na carteira, gestão de ordens

---

## 🎓 Documentação do Processo (Trabalho Acadêmico)

### 1. Evolução do Trabalho 1 para o Trabalho 2

O projeto evoluiu de uma tela básica para um aplicativo completo com 8 telas integradas. Principais mudanças:
- ✨ Implementação de navegação completa com `NavHost`
- ✨ Criação de sistema de extrato bancário
- ✨ Adição de módulo de investimentos
- ✨ Gerenciamento de cartão de crédito
- ✨ Integração entre todas as funcionalidades

### 2. Justificativa das Telas

| Tela | Justificativa |
|------|---------------|
| **Extrato** | Centraliza histórico de todas as transações (PIX, pagamentos, faturas) |
| **Investir** | Lista dinâmica de ativos com CRUD completo |
| **Detalhe do Ativo** | Exibe cálculos complexos e gestão de ordens |
| **Cartão** | Gerencia transações de crédito com adição/remoção |
| **Detalhe da Transação** | Mostra informações completas de cada gasto |
| **PIX** | Formulário de transferência com validações |

### 3. Decisões de Organização

#### Estados Compartilhados
- `BancoState`, `CartaoState` e `InvestimentosState` criados em `CupBankApp`
- Garantem persistência ao trocar abas e integração entre telas

#### Rotas Centralizadas
- Objeto `Rotas` com constantes para todas as rotas
- Facilita manutenção e evita erros de digitação

#### Pacote Único
- Todo código em `com.example.cupbank` (exceto `investimentos/`)
- Simplifica navegação pelo código

### 4. Complexidade Extra: Detalhe do Ativo

A tela de detalhamento de ativo vai além do requisito mínimo:
- 📊 **Cálculo de preço de abertura** baseado na variação percentual
- 💰 **Valor da variação em reais**
- 📈 **Peso na carteira** considerando todas as ordens
- 🎯 **Gestão de ordens** direto na tela de detalhe
- ✅ **Confirmação/cancelamento** que atualiza o patrimônio total

### 5. Desafios e Soluções

- **Desafio:** Sincronização de estado entre telas
  - **Solução:** Estados elevados para `CupBankApp`

- **Desafio:** Navegação com argumentos tipados
  - **Solução:** Uso de `navArgument` com `NavType`

- **Desafio:** Persistência de dados ao trocar abas
  - **Solução:** `remember` no nível do `CupBankApp`

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.

---

## 👥 Autores

Desenvolvido como Trabalho 2 da disciplina de Desenvolvimento Mobile.

---

## 📞 Suporte

Para dúvidas ou problemas:
1. Verifique a seção de **Solução de Problemas**
2. Consulte a documentação oficial do [Jetpack Compose](https://developer.android.com/jetpack/compose)
3. Revise os comentários no código-fonte

---
