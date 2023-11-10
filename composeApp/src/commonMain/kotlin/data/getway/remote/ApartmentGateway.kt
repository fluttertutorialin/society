package data.getway.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import data.remote.mapper.toEntity
import data.remote.model.BlockDto
import data.remote.model.ServerResponse
import domain.entity.Block
import domain.getway.IApartmentGateway
import util.GeneralException

class ApartmentGateway(client: HttpClient) : BaseGateway(client = client), IApartmentGateway {
    override suspend fun getBlocks(): List<Block> {
        return tryToExecute<ServerResponse<List<BlockDto>>> {
            get("v3.1/all")
        }.value?.toEntity() ?: throw GeneralException.NotFoundException
    }
}
