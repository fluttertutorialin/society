package domain.usercase

import domain.entity.Block
import domain.getway.IApartmentGateway

interface IExploreBlockUseCaseGetBlockUseCase {
    suspend fun getBlocks(): List<Block>
}

class ExploreBlockUseCaseGetBlockUseCase(private val apartmentGateway: IApartmentGateway) :
    IExploreBlockUseCaseGetBlockUseCase {
    override suspend fun getBlocks(): List<Block> {
        return apartmentGateway.getBlocks()
    }
}

