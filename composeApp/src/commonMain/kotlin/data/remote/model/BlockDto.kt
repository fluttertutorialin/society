package data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("block")
    val block: String?,
)