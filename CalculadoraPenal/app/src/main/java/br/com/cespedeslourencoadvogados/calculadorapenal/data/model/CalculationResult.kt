package br.com.cespedeslourencoadvogados.calculadorapenal.data.model

import java.time.LocalDate

/** Modelo de dados retornado pela calculadora. */
data class CalculationResult(
    /** Data prevista para progressão ao regime semiaberto. */
    val semiOpenRegimeDate: LocalDate,
    /** Data prevista para progressão ao regime aberto. */
    val openRegimeDate: LocalDate,
    /** Data prevista para livramento condicional */
    val conditionalReleaseDate: LocalDate
)
