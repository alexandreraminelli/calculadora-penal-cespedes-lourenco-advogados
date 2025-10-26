package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.compose.foundation.Image
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

/** Formulário da Calculadora Penal. */
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Imagem da logo
        Image(
            painter = painterResource(R.drawable.logo_cor),
            contentDescription = null,
        )
    }
}


/** Prévia do formulário da calculadora penal. */
@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}