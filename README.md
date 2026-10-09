# CupBank — Trabalho 2 (Navegação + listas + detalhes)

Aplicativo de banco fictício feito em Kotlin + Jetpack Compose (Material 3).
Todos os dados vivem só na memória (`mutableStateListOf`), como pede o enunciado.

## Como rodar

1. Abra no Android Studio a pasta que contém o `settings.gradle.kts` (File → Open).
2. Aguarde o Gradle Sync (precisa de internet na primeira vez).
3. Selecione um emulador/dispositivo (minSdk 24) e rode a configuração `app`.

## Mapa de telas (8 telas, todas navegáveis)

| Tela | Rota | Como chegar |
|---|---|---|
| Início | `inicio` | Aba **Início** (tela inicial) |
| Extrato | `extrato` | Aba **Extrato** ou "Ver tudo" / card de extrato recente no Início |
| Investir (lista de ativos) | `investir` | Aba **Investir** ou botão central **+** |
| Detalhe do ativo | `detalhe_ativo/{ticker}` | Tocar num ativo da lista |
| Perfil | `perfil` | Aba **Perfil** |
| Cartão (lista de transações) | `cartao` | Perfil → **Cartões** ou Início → **Pagar** |
| Detalhe da transação | `cartao_transacao/{id}` | Tocar numa transação do cartão |
| Transferência PIX | `pix` | Início → **PIX** ou **Transferir** |

As rotas ficam no objeto `Rotas` (`Rotas.kt`) e o `NavHost` em `CupBankApp.kt`.

## Requisitos do trabalho × onde estão

- **BottomNavigation/NavigationBar:** `CupBankBottomBar.kt` (4 abas + botão central "+").
- **Botões com `navController.navigate(...)`:** `CupBankApp.kt`.
- **TopAppBar com voltar (`popBackStack()`):** `DetalheAtivoScreen.kt`; as telas de Cartão, Detalhe da transação e PIX também têm botão de voltar.
- **2 data classes de itens (3 no total):** `Ativo` (+ `Ordem`) em `investimentos/Modelos.kt`; `TransacaoCartao` em `TelaCartao.kt`; `Movimento` em `Banco.kt`.
- **2 listas com `LazyColumn` + `Card`:**
  - Ativos (aba Investir): adicionar pelo formulário "Novo ativo" (botão **+ Investir** ou **+** central); remover segurando o item.
  - Transações do cartão: adicionar pelo botão flutuante **+** (formulário); remover pela lixeira.
- **2 telas de Detalhes com o item certo (argumento de rota):** Detalhe do ativo (`ticker`) e Detalhe da transação (`id`), ambos buscando o item no estado.
- **Extra no Detalhe do ativo:** valores calculados (preço de abertura, variação do dia em R$, peso na carteira), posição do usuário combinando `Ativo` + `Ordem`, e confirmar/cancelar ordens ali mesmo (atualiza o patrimônio da aba Investir).

## Integração entre as telas

- **Receber PIX (Início):** soma no saldo e cria uma linha "PIX recebido" no topo do **Extrato**.
- **Enviar PIX (tela PIX):** valida chave e saldo, debita e cria "PIX enviado" no Extrato.
- **Pagar fatura (Cartão):** debita o saldo, quita as transações e cria "Fatura do cartão" no Extrato.

## Estrutura do código (`app/src/main/java/com/example/cupbank`)

- `MainActivity.kt` — só a Activity (tema + `CupBankApp`).
- `CupBankApp.kt`, `Rotas.kt`, `CupBankBottomBar.kt` — navegação.
- `Banco.kt` — saldo e Extrato compartilhados (`BancoState`).
- `BankAppScreen.kt` (Início), `ExtratoScreen.kt`, `TelaTransferenciaPix.kt`, `ProfileScreen.kt`, `TelaCartao.kt`.
- `investimentos/` — tela Investir, Detalhe do ativo, modelos e estado.

## Documentação do processo e das decisões (seção 4)

> Preencher com as palavras do trio e **inserir prints/vídeo do app em cada etapa**.

**1. Como estava o projeto no Trabalho 1 e o que mudou?**
_(texto + print)_

**2. Por que essas telas novas? O que cada uma faz?**
_(texto + prints de Extrato, Investir, Detalhe do ativo, Cartão, Detalhe da transação, PIX)_

**3. Decisões de organização do código**
- Um único pacote `com.example.cupbank`; rotas num objeto `Rotas`; um único `NavHost` em `CupBankApp`.
- Estados (`BancoState`, `CartaoState`, `InvestimentosState`) criados em `CupBankApp` e passados às telas, para sobreviver à troca de abas e para o Extrato enxergar o que acontece no Início, PIX e Cartão.

**4. Complexidade extra na tela de Detalhes e por que foi escolhida**
_(ver "Extra no Detalhe do ativo" acima + print)_

**5. Dificuldades de cada integrante e como resolveram**
_(texto)_
