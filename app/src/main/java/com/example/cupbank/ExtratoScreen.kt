package com.example.cupbank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupbank.investimentos.CorNegativo
import com.example.cupbank.investimentos.CorPositivo
import com.example.cupbank.investimentos.paraReais
import kotlin.math.abs

@Composable
fun ExtratoScreen(banco: BancoState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CupBankBackground),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Column {
                Text("Sua conta", color = CupBankTextSecondary, fontSize = 13.sp)
                Text(
                    "Extrato",
                    color = CupBankTextPrimary,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CupBankSurface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("Saldo disponível", color = CupBankTextSecondary, fontSize = 13.sp)
                    Text(
                        text = banco.saldo.paraReais(),
                        color = CupBankTextPrimary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        if (banco.movimentos.isEmpty()) {
            item {
                Text("Nenhuma movimentação ainda.", color = CupBankTextSecondary, fontSize = 13.sp)
            }
        } else {
            items(banco.movimentos, key = { it.id }) { mov -> MovimentoCard(mov) }
        }
    }
}

@Composable
private fun MovimentoCard(mov: Movimento) {
    val entrada = mov.valor >= 0
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CupBankSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mov.titulo,
                    color = CupBankTextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${mov.detalhe} • ${mov.data}",
                    color = CupBankTextSecondary,
                    fontSize = 12.sp
                )
            }
            Text(
                text = (if (entrada) "+ " else "- ") + abs(mov.valor).paraReais(),
                color = if (entrada) CorPositivo else CorNegativo,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
