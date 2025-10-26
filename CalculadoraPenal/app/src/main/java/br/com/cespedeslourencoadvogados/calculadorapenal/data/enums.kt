package br.com.cespedeslourencoadvogados.calculadorapenal.data

import androidx.annotation.StringRes
import br.com.cespedeslourencoadvogados.calculadorapenal.R

/** Tipo de crime cometido: comum ou hediondo/equiparado. */
enum class TipoCrime(
    @StringRes val label: Int
) {
    COMUM(R.string.tipo_crime_comum),
    HEDIONDO(R.string.tipo_crime_hediondo),
}

/** Status do apenado: primário ou reincidente. */
enum class StatusApenado(
    @StringRes val label: Int
) {
    PRIMARIO(R.string.status_apenado_primario),
    REINCIDENTE(R.string.status_apenado_reincidente),
}