package di

import org.koin.dsl.module
import domain.validation.IValidationUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import domain.usercase.ExploreBlockUseCaseGetBlockUseCase
import domain.usercase.GetTransactionHistoryUseCase
import domain.usercase.IExploreBlockUseCaseGetBlockUseCase
import domain.usercase.IGetTransactionHistoryUseCase
import domain.validation.ValidationUseCaseUseCase

val useCaseModule = module {
    //VALIDATION
    singleOf(::ValidationUseCaseUseCase) { bind<IValidationUseCase>() }

    //AUTH

    //TRANSACTION HISTORY
    singleOf(::GetTransactionHistoryUseCase) { bind<IGetTransactionHistoryUseCase>() }

    //APARTMENT
    singleOf(::ExploreBlockUseCaseGetBlockUseCase) { bind<IExploreBlockUseCaseGetBlockUseCase>() }



}
