package br.com.cespedeslourencoadvogados.calculadorapenal.data.model

import kotlinx.serialization.Serializable

/** Modelo de dados para durações de tempo em anos, meses e dias. */
@Serializable
data class PenaltyDuration(
    val years: Int = 0,
    val months: Int = 0,
    val days: Int = 0
)