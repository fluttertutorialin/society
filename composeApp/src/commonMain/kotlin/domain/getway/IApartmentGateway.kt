package domain.getway

import domain.entity.Block

interface IApartmentGateway {
    suspend fun getBlocks(): List<Block>
}
