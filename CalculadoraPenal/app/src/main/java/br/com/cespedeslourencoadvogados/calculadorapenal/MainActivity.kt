package br.com.cespedeslourencoadvogados.calculadorapenal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator.CalculatorScreen
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
        topBar = { CalculadoraPenalAppBar() },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        CalculatorScreen(modifier = Modifier.padding(innerPadding))
    }
}

/** Appbar da aplicação. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraPenalAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text("Título") },
        modifier = modifier
    )
}

/** Prévia da aplicação. */
@Preview
@Composable
fun AppPreview() {
    CalculadoraPenalTheme {
        CalculadoraPenalApp()
    }
}