package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cespedeslourencoadvogados.calculadorapenal.R
import br.com.cespedeslourencoadvogados.calculadorapenal.data.StatusApenado
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult

/** Formulário da Calculadora Penal. */
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel(),
    onNavigateToResults: (CalculationResult) -> Unit,
) {
    // Observar estado vindo do ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Criar efeito para observar o resultado
    LaunchedEffect(uiState.calculationResult) {
        uiState.calculationResult?.let { result ->
            onNavigateToResults(result)
            viewModel.onNavigationDone()
        }
    }

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
        DurationInput(
            label = R.string.label_pena_total,
            anos = uiState.penaTotalAnos,
            onAnosChanged = viewModel::onPenaTotalAnosChanged,
            meses = uiState.penaTotalMeses,
            onMesesChanged = viewModel::onPenaTotalMesesChanged,
            dias = uiState.penaTotalDias,
            onDiasChanged = viewModel::onPenaTotalDiasChanged,
        )

        // Data de Início
        DateInput(
            label = R.string.label_inicio_pena,
            value = uiState.dataInicio,
            onValueChange = viewModel::onDataInicioChanged,
        )

        // Tempo de Detração
        DurationInput(
            label = R.string.label_detracao,
            anos = uiState.detracaoAnos,
            onAnosChanged = viewModel::onDetracaoAnosChanged,
            meses = uiState.detracaoMeses,
            onMesesChanged = viewModel::onDetracaoMesesChanged,
            dias = uiState.detracaoDias,
            onDiasChanged = viewModel::onDetracaoDiasChanged
        )

        // Tipo de Crime
        SelectInput(
            label = R.string.label_tipo_crime,
            options = TipoCrime.values().toList(),
            selected = uiState.tipoCrime,
            onSelectionChanged = viewModel::onTipoCrimeChanged
        )

        // Status do Apenado
        SelectInput(
            label = R.string.label_status_apenado,
            options = StatusApenado.values().toList(),
            selected = uiState.statusApenado,
            onSelectionChanged = viewModel::onStatusApenadoChanged
        )

        // Botão de enviar
        Spacer(modifier = Modifier)
        Button(
            onClick = viewModel::onCalcularClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.calcular))
        }
    }
}


/** Prévia do formulário da calculadora penal. */
@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(
        onNavigateToResults = {}
    )
}