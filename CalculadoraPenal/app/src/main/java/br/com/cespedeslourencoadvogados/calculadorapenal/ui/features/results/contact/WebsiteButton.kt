package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.results.contact

import br.com.cespedeslourencoadvogados.calculadorapenal.R
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

/** Botão para abrir o site. */
@Composable
fun WebsiteButton(
    modifier: Modifier = Modifier
) {
    /** URL do site. */
    val url = "https://cespedeslourencoadvogados.com.br"

    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

    Button(
        onClick = { context.startActivity(intent) }, modifier = modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.botao_site))
    }
}