package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

        // Status do Apenado
    }
}

/** Input de tempo em anos, meses e dias. */
@Composable
fun DurationInput(
    @StringRes label: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        // Label
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodyMedium
        )
        // Campos
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Anos
            DurationField(
                type = DurationType.YEARS,
                modifier = Modifier.weight(1f)
            )
            // Meses
            DurationField(
                type = DurationType.MONTHS,
                modifier = Modifier.weight(1f)
            )
            // Dias
            DurationField(
                type = DurationType.DAYS,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/** Tipo de input de duração (anos, meses ou dias). */
enum class DurationType {
    YEARS,
    MONTHS,
    DAYS
}

/** Input para quantidade de anos, meses ou dias. */
@Composable
fun DurationField(
    type: DurationType,
    modifier: Modifier = Modifier
) {
    val label = stringResource(
        when (type) {
            DurationType.YEARS -> R.string.anos
            DurationType.MONTHS -> R.string.meses
            DurationType.DAYS -> R.string.dias
        }
    )

    OutlinedTextField(
        label = { Text(label) },
        value = "",
        onValueChange = {},
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
        ),
        modifier = modifier
    )
}


/** Prévia do formulário da calculadora penal. */
@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}