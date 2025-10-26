package br.com.cespedeslourencoadvogados.calculadorapenal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator.CalculatorScreen
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.layout.AppBar
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.theme.CalculadoraPenalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraPenalTheme {
                CalculadoraPenalApp()
            }
        }
    }
}

/** Layout root da aplicação. */
@Composable
fun CalculadoraPenalApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { AppBar() },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        CalculatorScreen(modifier = Modifier.padding(innerPadding))
    }
}


/** Prévia da aplicação. */
@Preview
@Composable
fun AppPreview() {
    CalculadoraPenalTheme {
        CalculadoraPenalApp()
    }
}