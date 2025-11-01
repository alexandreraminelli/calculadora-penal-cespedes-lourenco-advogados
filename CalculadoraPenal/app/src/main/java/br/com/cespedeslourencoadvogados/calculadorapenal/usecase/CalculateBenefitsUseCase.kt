package br.com.cespedeslourencoadvogados.calculadorapenal.usecase

import br.com.cespedeslourencoadvogados.calculadorapenal.data.StatusApenado
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationInput
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.CalculationResult
import br.com.cespedeslourencoadvogados.calculadorapenal.data.model.PenaltyDuration
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.roundToLong


/** Classe que recebe os dados de entrada da calculadora e retorna os resultados. */
class CalculateBenefitsUseCase constructor() {

    /** Executa o cálculo principal. */
    fun calcular(input: CalculationInput): CalculationResult {

        // Define a data base real para o cálculo, abatendo a detração
        val dataInicioEfetiva = calcularDataInicioEfetiva(input.startDate, input.detraction)

        // Converte a pena total em dias para facilitar os cálculos
        val penaTotalEmDias = obterPenaTotalEmDias(dataInicioEfetiva, input.totalPenalty)

        // Obtém as frações corretas com base nas regras
        val fracaoProgressao = obterFracaoProgressao(input.crimeType, input.convictStatus)
        val fracaoLivramento =
            obterFracaoLivramentoCondicional(input.crimeType, input.convictStatus)

        // Calcula os dias necessários para cada benefício
        val diasParaSemiaberto = (penaTotalEmDias * fracaoProgressao).roundToLong()
        val diasParaAberto = (penaTotalEmDias * (fracaoProgressao * 2)).roundToLong()
        val diasParaLivramento = (penaTotalEmDias * fracaoLivramento).roundToLong()

        // Etapa 5: Calcula as datas finais dos benefícios [cite: 1715, 1717, 1719]
        val dataSemiaberto = calcularDataBeneficio(dataInicioEfetiva, diasParaSemiaberto)
        val dataAberto = calcularDataBeneficio(dataInicioEfetiva, diasParaAberto)
        val dataLivramento = calcularDataBeneficio(dataInicioEfetiva, diasParaLivramento)

        return CalculationResult(
            semiOpenRegimeDate = dataSemiaberto,
            openRegimeDate = dataAberto,
            conditionalReleaseDate = dataLivramento
        )
    }

    /** Calcula a data de início efetiva, descontando o tempo de detração. */
    private fun calcularDataInicioEfetiva(
        dataInicio: LocalDate, detracao: PenaltyDuration
    ): LocalDate {
        return dataInicio.minusYears(detracao.years.toLong()).minusMonths(detracao.months.toLong())
            .minusDays(detracao.days.toLong())
    }

    /** Converte a duração da pena (anos, meses, dias) em um total de dias. */
    private fun obterPenaTotalEmDias(dataInicio: LocalDate, penaTotal: PenaltyDuration): Long {
        val dataTermino =
            dataInicio.plusYears(penaTotal.years.toLong()).plusMonths(penaTotal.months.toLong())
                .plusDays(penaTotal.days.toLong())

        // Conta o número de dias entre a data de início e a data de término
        return ChronoUnit.DAYS.between(dataInicio, dataTermino)
    }

    /** Retorna a fração para Progressão de Regime. */
    private fun obterFracaoProgressao(tipo: TipoCrime, status: StatusApenado): Double {
        return when (tipo) {
            TipoCrime.COMUM -> when (status) {
                StatusApenado.PRIMARIO -> 0.16 // 16%
                StatusApenado.REINCIDENTE -> 0.20 // 20%
            }

            TipoCrime.HEDIONDO -> when (status) {
                StatusApenado.PRIMARIO -> 0.40 // 40%
                StatusApenado.REINCIDENTE -> 0.60 // 60%
            }
        }
    }

    /** Retorna a fração para Livramento Condicional */
    private fun obterFracaoLivramentoCondicional(tipo: TipoCrime, status: StatusApenado): Double {
        return when {
            // "Vedado o livramento condicional para crimes hediondos com resultado de morte"
            tipo == TipoCrime.HEDIONDO -> 2.0 / 3.0 // 2/3 da pena
            status == StatusApenado.REINCIDENTE -> 1.0 / 2.0 // 1/2 da pena
            status == StatusApenado.PRIMARIO -> 1.0 / 3.0 // 1/3 da pena
            else -> 1.0 // Fallback de segurança, não deve ocorrer
        }
    }

    /** Calcula a data final de um benefício. */
    private fun calcularDataBeneficio(dataInicio: LocalDate, diasParaCumprir: Long): LocalDate {
        if (diasParaCumprir <= 0) return dataInicio
        return dataInicio.plusDays(diasParaCumprir - 1)
    }
}