package br.com.cespedeslourencoadvogados.calculadorapenal.ui.navigation

import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import kotlinx.serialization.json.Json
import java.net.URLEncoder

/** Rotas do app. */
object Routes {
    const val CALCULATOR = "calculator"
    const val RESULTS = "results/{resultJson}"

    fun resultsScreen(result: CalculationResult): String {
        // Converter objeto para JSON
        val resultJson = Json.encodeToString(
            CalculationResult.serializer(), result
        )
        // Codificar JSON para ser passado como URL
        val encodedJson = URLEncoder.encode(resultJson, "UTF-8")
        return "results/$encodedJson"
    }
}
