package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.Logo
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.theme.CalculadoraPenalTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/** Tela de resultados. */
@Composable
fun ResultsScreen(
    result: CalculationResult,
    modifier: Modifier = Modifier
) {
    /** Formatador para exibir as datas. */
    val formatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Cards de Resultados
        ResultsCard(result = result)

        Spacer(Modifier.height(16.dp))
        Logo()
    }
}

/** Preview da Tela de Resultados completa. */
@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    // Dados de exemplo para o preview
    val previewResult = CalculationResult(
        semiOpenRegimeDate = LocalDate.now().plusMonths(6),
        openRegimeDate = LocalDate.now().plusYears(1),
        conditionalReleaseDate = LocalDate.now().plusYears(2)
    )
    CalculadoraPenalTheme {
        ResultsScreen(result = previewResult)
    }
}