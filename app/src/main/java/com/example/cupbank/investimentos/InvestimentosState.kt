package com.example.cupbank.investimentos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class InvestimentosState {

    val ativos = mutableStateListOf(
        Ativo("PETR4", "Petrobras PN", 38.42, 1.84, TipoAtivo.ACAO),
        Ativo("ITUB4", "Itaú Unibanco PN", 34.10, 0.72, TipoAtivo.ACAO),
        Ativo("BOVA11", "ETF Ibovespa", 126.85, 1.12, TipoAtivo.FUNDO),
        Ativo("VALE3", "Vale ON", 62.30, -0.45, TipoAtivo.ACAO),
        Ativo("HGLG11", "CSHG Logística FII", 158.20, 0.31, TipoAtivo.FUNDO)
    )

    val ordens = mutableStateListOf<Ordem>()

    var patrimonio by mutableDoubleStateOf(8640.75)
        private set

    var variacaoMes by mutableDoubleStateOf(286.40)
        private set

    var tickerSelecionado by mutableStateOf("PETR4")
        private set

    var quantidade by mutableIntStateOf(1)
        private set

    var mostrarNovoAtivo by mutableStateOf(false)

    private var proximoIdOrdem = 1

    val ativoSelecionado: Ativo? get() = ativoPorTicker(tickerSelecionado)

    fun ativoPorTicker(ticker: String): Ativo? = ativos.firstOrNull { it.ticker == ticker }

    fun selecionar(ticker: String) {
        if (ticker != tickerSelecionado) {
            tickerSelecionado = ticker
            quantidade = 1
        }
    }

    fun aumentarQuantidade() {
        if (quantidade < 999) quantidade++
    }

    fun diminuirQuantidade() {
        if (quantidade > 1) quantidade--
    }

    fun adicionarAtivo(ticker: String, nome: String, preco: Double?, tipo: TipoAtivo): String? {
        val codigo = ticker.trim().uppercase()
        return when {
            codigo.isEmpty() -> "Informe o código do ativo."
            nome.isBlank() -> "Informe o nome do ativo."
            preco == null || preco <= 0.0 -> "Informe um preço maior que zero."
            ativos.any { it.ticker == codigo } -> "Esse ativo já está na lista."
            else -> {
                ativos.add(Ativo(codigo, nome.trim(), preco, 0.0, tipo))
                null
            }
        }
    }

    fun removerAtivo(ticker: String) {
        val doAtivo = ordens.filter { it.ticker == ticker }
        patrimonio -= doAtivo.sumOf { it.total }
        ordens.removeAll(doAtivo)
        ativos.removeAll { it.ticker == ticker }
        if (tickerSelecionado == ticker) {
            tickerSelecionado = ativos.firstOrNull()?.ticker ?: ""
            quantidade = 1
        }
    }

    fun confirmarCompra(ativo: Ativo, quantidade: Int) {
        val ordem = Ordem(proximoIdOrdem++, ativo.ticker, quantidade, ativo.preco)
        ordens.add(0, ordem)
        patrimonio += ordem.total
    }

    fun cancelarOrdem(ordem: Ordem) {
        if (ordens.remove(ordem)) patrimonio -= ordem.total
    }
}
