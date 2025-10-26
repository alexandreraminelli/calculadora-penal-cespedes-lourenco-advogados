package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cespedeslourencoadvogados.calculadorapenal.R

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
        placeholder = { Text("0") },
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

/** Pré-visualização do input de duração. */
@Preview(showBackground = true)
@Composable
fun DurationInputPreview() {
    DurationInput(label = R.string.label_pena_total)
}