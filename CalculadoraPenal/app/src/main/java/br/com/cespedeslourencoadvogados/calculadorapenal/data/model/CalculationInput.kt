package br.com.cespedeslourencoadvogados.calculadorapenal.data.model

import br.com.cespedeslourencoadvogados.calculadorapenal.data.StatusApenado
import br.com.cespedeslourencoadvogados.calculadorapenal.data.TipoCrime
import java.time.LocalDate

/** Modelo de dados coletados pelo formulário da calculadora. */
data class CalculationInput(
    val totalPenalty: PenaltyDuration,
    val startDate: LocalDate,
    val detraction: PenaltyDuration,
    val crimeType: TipoCrime,
    val convictStatus: StatusApenado
)
