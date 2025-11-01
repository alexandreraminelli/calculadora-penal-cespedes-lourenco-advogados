package br.com.cespedeslourencoadvogados.calculadorapenal.data.model

/** Modelo de dados para durações de tempo em anos, meses e dias. */
data class PenaltyDuration(
    val years: Int = 0,
    val months: Int = 0,
    val days: Int = 0
)