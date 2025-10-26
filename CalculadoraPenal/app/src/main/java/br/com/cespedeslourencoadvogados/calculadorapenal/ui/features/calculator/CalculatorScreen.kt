package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cespedeslourencoadvogados.calculadorapenal.R
import br.com.cespedeslourencoadvogados.calculadorapenal.data.StatusApenado
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime

/** Formulário da Calculadora Penal. */
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Imagem da logo
        Image(
            painter = painterResource(R.drawable.logo_cor),
            contentDescription = null,
        )

        // Pena Total
        DurationInput(R.string.label_pena_total)

        // Data de Início
        DataInput(R.string.label_inicio_pena)

        // Tempo de Detração
        DurationInput(R.string.label_detracao)

        // Tipo de Crime
        SelectInput(
            label = R.string.label_tipo_crime,
            options = TipoCrime.values().toList()
        )

        // Status do Apenado
        SelectInput(
            label = R.string.label_status_apenado,
            options = StatusApenado.values().toList()
        )
    }
}


/** Prévia do formulário da calculadora penal. */
@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}