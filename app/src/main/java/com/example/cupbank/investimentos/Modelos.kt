package com.example.cupbank.investimentos

enum class TipoAtivo(val rotulo: String, val singular: String) {
    ACAO("Ações", "Ação"),
    FUNDO("Fundos", "Fundo")
}

data class Ativo(
    val ticker: String,
    val nome: String,
    val preco: Double,
    val variacao: Double,
    val tipo: TipoAtivo
)

data class Ordem(
    val id: Int,
    val ticker: String,
    val quantidade: Int,
    val precoUnitario: Double
) {
    val total: Double get() = precoUnitario * quantidade
}
