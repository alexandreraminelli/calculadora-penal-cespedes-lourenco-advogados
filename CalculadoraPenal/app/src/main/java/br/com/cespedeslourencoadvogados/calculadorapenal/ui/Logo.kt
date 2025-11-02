package br.com.cespedeslourencoadvogados.calculadorapenal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.cespedeslourencoadvogados.calculadorapenal.R

/** Logo da Cespedes Lourenço Advogados. Adaptável ao tema. */
@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    val logoId = if (isSystemInDarkTheme()) {
        R.drawable.logo_dark
    } else {
        R.drawable.logo_light
    }

    Image(
        painter = painterResource(logoId),
        contentDescription = null,
        modifier = modifier
    )
}