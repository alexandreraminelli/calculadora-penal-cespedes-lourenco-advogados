package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import br.com.cespedeslourencoadvogados.calculadorapenal.data.StatusApenado
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import java.time.LocalDate

/** Estado do formulário da calculadora. */
data class CalculatorUiState(
    // Campos de Pena Total
    val penaTotalAnos: String = "",
    val penaTotalMeses: String = "",
    val penaTotalDias: String = "",

    // Data de início
    val dataInicio: LocalDate? = null,

    // Campos de Detração
    val detracaoAnos: String = "",
    val detracaoMeses: String = "",
    val detracaoDias: String = "",

    // Campos de Seleção
    val tipoCrime: TipoCrime = TipoCrime.COMUM,
    val statusApenado: StatusApenado = StatusApenado.PRIMARIO,

    // Resultado do calculo
    val calculationResult: CalculationResult? = null
)