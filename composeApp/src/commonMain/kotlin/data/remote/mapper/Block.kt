package data.remote.mapper

import data.remote.model.BlockDto
import domain.entity.Block

fun BlockDto.toEntity() = Block(
    id = id ?: "",
    block = block ?: ""
)

fun List<BlockDto>.toEntity() = map { it.toEntity() }