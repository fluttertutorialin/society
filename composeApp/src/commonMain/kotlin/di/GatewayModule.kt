package di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import data.getway.fake.FakeUserGateway
import data.getway.remote.ApartmentGateway
import data.getway.remote.pagesource.NotificationPagingSource
import domain.getway.IApartmentGateway
import domain.getway.IUserGateway

val gatewayModule = module {
    //FAKE
    singleOf(::FakeUserGateway) { bind<IUserGateway>() }

    //REMOTE
    singleOf(::ApartmentGateway) { bind<IApartmentGateway>() }
    //singleOf(::UserGateway) { bind<IUserGateway>() }

    //SINGLE
    singleOf(::NotificationPagingSource)
}
