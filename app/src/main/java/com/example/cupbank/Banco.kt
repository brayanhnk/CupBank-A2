package com.example.cupbank

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Movimento(
    val id: Int,
    val titulo: String,
    val detalhe: String,
    val valor: Double,
    val data: String
)

class BancoState {

    var saldo by mutableDoubleStateOf(3284.50)
        private set

    val movimentos = mutableStateListOf(
        Movimento(3, "PIX recebido", "De: Maria Souza", 250.00, "07/10"),
        Movimento(2, "Conta de luz", "Pagamento", -142.30, "06/10"),
        Movimento(1, "Salário", "Depósito", 2800.00, "05/10")
    )

    private var proximoId = 4

    fun receberPix(origem: String, valor: Double) {
        registrar("PIX recebido", "De: $origem", valor)
    }

    fun enviarPix(destino: String, valor: Double?, descricao: String): String? {
        return when {
            destino.isBlank() -> "Informe a chave PIX ou o contato."
            valor == null || valor <= 0.0 -> "Informe um valor maior que zero."
            valor > saldo -> "Saldo insuficiente."
            else -> {
                val detalhe = if (descricao.isBlank()) "Para: $destino" else "Para: $destino · $descricao"
                registrar("PIX enviado", detalhe, -valor)
                null
            }
        }
    }

    fun pagarFatura(valor: Double): Boolean {
        if (valor > saldo) return false
        registrar("Fatura do cartão", "Cartão final 9010", -valor)
        return true
    }

    private fun registrar(titulo: String, detalhe: String, valor: Double) {
        saldo += valor
        val agora = SimpleDateFormat("dd/MM HH:mm", Locale.forLanguageTag("pt-BR")).format(Date())
        movimentos.add(0, Movimento(proximoId++, titulo, detalhe, valor, agora))
    }
}
