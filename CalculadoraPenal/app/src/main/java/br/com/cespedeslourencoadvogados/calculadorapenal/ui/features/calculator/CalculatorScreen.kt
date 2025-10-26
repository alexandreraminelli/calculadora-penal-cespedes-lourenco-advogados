package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/** Formulário da Calculadora Penal. */
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        Text(text = "Calculadora Penal")
    }
}


/** Prévia do formulário da calculadora penal. */
@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}