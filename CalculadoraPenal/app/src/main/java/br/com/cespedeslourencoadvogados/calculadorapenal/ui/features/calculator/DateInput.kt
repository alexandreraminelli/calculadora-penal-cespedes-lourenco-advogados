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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Input de data. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataInput(
    @StringRes label: Int,
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
        Box {
            OutlinedTextField(
                value = selectedDate?.let { dateFormatter.format(it) } ?: "",
                onValueChange = {},
                label = { Text(stringResource(label)) },
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
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = {isDatePickerVisible = false}, // fechar ao clicar fora
            confirmButton = {
                TextButton(
                    onClick = {
                        isDatePickerVisible = false
                        selectedDate = datePickerState.selectedDateMillis?.let { Date(it) }
                    }
                ) {
                    Text(stringResource(R.string.confirmar))
                }
            },
            dismissButton = {
                TextButton(onClick =  {isDatePickerVisible = false}) {
                    Text(stringResource(R.string.cancelar))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}