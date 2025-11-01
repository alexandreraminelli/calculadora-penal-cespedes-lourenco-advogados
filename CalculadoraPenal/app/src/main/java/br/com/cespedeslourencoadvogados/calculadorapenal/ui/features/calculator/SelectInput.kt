package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.cespedeslourencoadvogados.calculadorapenal.R
import br.com.cespedeslourencoadvogados.calculadorapenal.data.OptionEnum
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime

/** SelectedButton para selecionar opções. */
@Composable
fun <E> SelectInput(
    @StringRes label: Int,
    options: List<E>,
    selected: E,
    onSelectionChanged: (E) -> Unit,
    modifier: Modifier = Modifier
) where E : Enum<E>, E : OptionEnum {
    /** Item selecionado. */
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodyMedium
        )
        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            options.forEachIndexed { index, option ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    onClick = { onSelectionChanged(option) },
                    selected = (index == options.indexOf(selected)),
                    label = { Text(stringResource(option.label)) }
                )
            }
        }
    }
}

/** Pré-visualização do SelectInput. */
@Preview(showBackground = true)
@Composable
fun SelectInputPreview() {
    SelectInput(
        label = R.string.label_tipo_crime,
        options = TipoCrime.values().toList(),
        selected = TipoCrime.values().first(),
        onSelectionChanged = {}
    )
}