package br.com.cespedeslourencoadvogados.calculadorapenal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator.CalculatorScreen
import br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.results.ResultsScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder
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

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    /** Controlador de navegação. */
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.CALCULATOR, // rota inicial
        modifier = modifier
    ) {
        // Tela da Calculadora
        composable(Routes.CALCULATOR) {
            CalculatorScreen(
                onNavigateToResults = { result ->
                    navController.navigate(Routes.resultsScreen(result))
                }
            )
        }

        // Tela de Resultados
        composable(
            route = Routes.RESULTS,
            arguments = listOf(navArgument("resultJson") { type = NavType.StringType })
        ) { backStackEntry ->
            // Receber argumento JSON
            val resultJson = backStackEntry.arguments?.getString("resultJson")
            if (resultJson != null) {
                // Decodificar e converter JSON de volta para objeto
                val decodedJson = URLDecoder.decode(resultJson, "UTF-8")
                val result = Json.decodeFromString(
                    CalculationResult.serializer(),
                    decodedJson
                )

                // Exibe a tela de resultado com os dados
                ResultsScreen(
                    result = result,
                    onBackClicked = { navController.popBackStack() }
                )
            }
        }
    }
}
