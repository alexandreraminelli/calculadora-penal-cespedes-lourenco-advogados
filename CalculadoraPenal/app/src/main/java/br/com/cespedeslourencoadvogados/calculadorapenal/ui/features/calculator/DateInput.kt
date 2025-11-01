package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import br.com.cespedeslourencoadvogados.calculadorapenal.R
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

/** Input de data. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    @StringRes label: Int,
    value: LocalDate?,
    onValueChange: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier
) {
    /** Estado para controlar a data. */
    var selectedDate by remember { mutableStateOf<Date?>(null) }

    /** Estado para controlar a visibilidade do data dialog. */
    var isDatePickerVisible by remember { mutableStateOf(false) }

    /** Objeto para formatar Date para String. */
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(label), style = MaterialTheme.typography.bodyMedium
        )
        Box {
            OutlinedTextField(
                value = value?.format(dateFormatter) ?: "",
                onValueChange = {},
                label = { Text(stringResource(R.string.selecionar_data)) },
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = stringResource(R.string.selecionar_data)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = androidx.compose.ui.graphics.Color.Transparent)
                    .clickable(onClick = { isDatePickerVisible = true })
            )
        }
    }

    // Exibir DatePickerDialog
    if (isDatePickerVisible) {
        // Converter LocalDate para Millis
        val initialMillis = value?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()

        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)

        DatePickerDialog(
            onDismissRequest = {
                isDatePickerVisible = false
            }, // fechar ao clicar fora
            confirmButton = {
                TextButton(
                    onClick = {
                        isDatePickerVisible = false
                        // Converter Millis para LocalDate
                        val selectedMillis = datePickerState.selectedDateMillis
                        val localDate = selectedMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                        onValueChange(localDate)
                    }) {
                    Text(stringResource(R.string.confirmar))
                }
            }, dismissButton = {
                TextButton(onClick = { isDatePickerVisible = false }) {
                    Text(stringResource(R.string.cancelar))
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
}

/** Pré-visualização do DateInput. */
@Preview(showBackground = true)
@Composable
fun DateInputPreview() {
    DateInput(R.string.label_inicio_pena)
}