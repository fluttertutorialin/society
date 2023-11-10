package di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        networkModule,
        screenModelsModule,
        useCaseModule,
        gatewayModule,
    )
}
