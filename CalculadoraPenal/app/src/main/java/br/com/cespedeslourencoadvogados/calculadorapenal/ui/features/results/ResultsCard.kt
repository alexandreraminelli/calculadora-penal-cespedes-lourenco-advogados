package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.results

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cespedeslourencoadvogados.calculadorapenal.R
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/** Card para exibir os resultados. */
@Composable
fun ResultsCard(
    result: CalculationResult,
    modifier: Modifier = Modifier
) {
    // Formatador para exibir as datas
    val formatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Regime Semiaberto
        ResultCard(
            labelResId = R.string.resultado_semiaberto,
            date = result.semiOpenRegimeDate.format(formatter)
        )
        // Regime Aberto
        ResultCard(
            labelResId = R.string.resultado_aberto,
            date = result.openRegimeDate.format(formatter)
        )
        // Livramento Condicional
        ResultCard(
            labelResId = R.string.regime_livramento,
            date = result.conditionalReleaseDate.format(formatter)
        )
    }
}

/** Card de resultado. */
@Composable
private fun ResultCard(
    @StringRes labelResId: Int,
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Título
            Text(
                text = stringResource(labelResId),
                style = MaterialTheme.typography.titleMedium
            )
            // Data
            Text(
                text = date,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


/** Preview do ResultsCard. */
@Preview(showBackground = true)
@Composable
fun ResultsCardPreview() {
    val previewResult = CalculationResult(
        semiOpenRegimeDate = LocalDate.now().plusMonths(6),
        openRegimeDate = LocalDate.now().plusYears(1),
        conditionalReleaseDate = LocalDate.now().plusYears(2)
    )
    ResultsCard(result = previewResult, modifier = Modifier.padding(16.dp))
}