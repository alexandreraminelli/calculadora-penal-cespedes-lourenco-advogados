package br.com.cespedeslourencoadvogados.calculadorapenal.ui.features.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cespedeslourencoadvogados.calculadorapenal.data.StatusApenado
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationInput
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.PenaltyDuration
import br.com.cespedeslourencoadvogados.calculadorapenal.usecase.CalculateBenefitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

/**
 * ViewModel para a tela da Calculadora.
 * Gerencia o estado da UI e lida com os eventos do usuário.
 */
class CalculatorViewModel : ViewModel() {
    // Instanciar UseCase
    private val calculateBenefitsUseCase = CalculateBenefitsUseCase()

    // Criar StateFlow privado que guarda o estado
    private val _uiState = MutableStateFlow(CalculatorUiState())

    // Expor StateFlow como imutável para a UI observar
    val uiState = _uiState.asStateFlow()

    // Pena total (filtro para aceitar apenas dígitos)
    fun onPenaTotalAnosChanged(value: String) {
        _uiState.update { it.copy(penaTotalAnos = value.filter { c -> c.isDigit() }) }
    }

    fun onPenaTotalMesesChanged(value: String) {
        _uiState.update { it.copy(penaTotalMeses = value.filter { c -> c.isDigit() }) }
    }

    fun onPenaTotalDiasChanged(value: String) {
        _uiState.update { it.copy(penaTotalDias = value.filter { c -> c.isDigit() }) }
    }

    // Data de Início
    fun onDataInicioChanged(date: LocalDate?) {
        _uiState.update { it.copy(dataInicio = date) }
    }

    // Detração
    fun onDetracaoAnosChanged(value: String) {
        _uiState.update { it.copy(detracaoAnos = value.filter { c -> c.isDigit() }) }
    }

    fun onDetracaoMesesChanged(value: String) {
        _uiState.update { it.copy(detracaoMeses = value.filter { c -> c.isDigit() }) }
    }

    fun onDetracaoDiasChanged(value: String) {
        _uiState.update { it.copy(detracaoDias = value.filter { c -> c.isDigit() }) }
    }

    // Seletores
    fun onTipoCrimeChanged(value: TipoCrime) {
        _uiState.update { it.copy(tipoCrime = value) }
    }

    fun onStatusApenadoChanged(value: StatusApenado) {
        _uiState.update { it.copy(statusApenado = value) }
    }

    /**
     * Chamado quando o botão "Calcular" é clicado.
     */
    fun onCalcularClicked() {
        val state = _uiState.value

        // Se a data de início não for informada, não faz nada.
        if (state.dataInicio == null) {
            // TODO: Exibir um erro para o usuário (snackbar)
            return
        }

        // Monta o objeto de Input para o cálculo
        val input = CalculationInput(
            totalPenalty = PenaltyDuration(
                state.penaTotalAnos.toIntOrNull() ?: 0,
                state.penaTotalMeses.toIntOrNull() ?: 0,
                state.penaTotalDias.toIntOrNull() ?: 0
            ),
            startDate = state.dataInicio,
            detraction = PenaltyDuration(
                state.detracaoAnos.toIntOrNull() ?: 0,
                state.detracaoMeses.toIntOrNull() ?: 0,
                state.detracaoDias.toIntOrNull() ?: 0
            ),
            crimeType = state.tipoCrime,
            convictStatus = state.statusApenado
        )

        // Executa o cálculo em uma Coroutine
        viewModelScope.launch {
            val result = calculateBenefitsUseCase.calcular(input)
            // 6. Salva o resultado no estado (isso vai disparar a navegação)
            _uiState.update { it.copy(calculationResult = result) }
        }
    }

    /**
     * Chamado pela UI após a navegação ser concluída,
     * para resetar o evento de navegação.
     */
    fun onNavigationDone() {
        _uiState.update { it.copy(calculationResult = null) }
    }

}