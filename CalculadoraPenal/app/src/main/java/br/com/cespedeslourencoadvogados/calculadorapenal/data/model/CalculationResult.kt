package br.com.cespedeslourencoadvogados.calculadorapenal.data.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate

/** Modelo de dados retornado pela calculadora. */
@Serializable
data class CalculationResult(
    /** Data prevista para progressão ao regime semiaberto. */
    @Serializable(with = LocalDateSerializer::class)
    val semiOpenRegimeDate: LocalDate,
    /** Data prevista para progressão ao regime aberto. */
    @Serializable(with = LocalDateSerializer::class)
    val openRegimeDate: LocalDate,
    /** Data prevista para livramento condicional */
    @Serializable(with = LocalDateSerializer::class)
    val conditionalReleaseDate: LocalDate
)

object LocalDateSerializer : KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "LocalDate",
        PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}