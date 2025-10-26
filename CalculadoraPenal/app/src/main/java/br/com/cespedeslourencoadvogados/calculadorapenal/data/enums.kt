package br.com.cespedeslourencoadvogados.calculadorapenal.data

import androidx.annotation.StringRes
import br.com.cespedeslourencoadvogados.calculadorapenal.R

/** Interface para enums de opções de form. */
interface OptionEnum {
    @get:StringRes
    val label: Int
}

/** Tipo de crime cometido: comum ou hediondo/equiparado. */
enum class TipoCrime(
    @StringRes override val label: Int,
) : OptionEnum {
    COMUM(R.string.tipo_crime_comum),
    HEDIONDO(R.string.tipo_crime_hediondo),
}

/** Status do apenado: primário ou reincidente. */
enum class StatusApenado(
    @StringRes override val label: Int
): OptionEnum {
    PRIMARIO(R.string.status_apenado_primario),
    REINCIDENTE(R.string.status_apenado_reincidente),
}