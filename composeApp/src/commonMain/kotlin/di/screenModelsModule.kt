package di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import presentation.screen.auth.login.LoginScreenModel
import presentation.screen.auth.otp.OtpScreenModel
import presentation.screen.auth.signup.RegistrationScreenModel
import presentation.screen.notification.NotificationScreenModel
import presentation.screen.profile.ProfileScreenModel

val screenModelsModule = module {
    factoryOf(::LoginScreenModel)
    factoryOf(::RegistrationScreenModel)
    factoryOf(::OtpScreenModel)
    factoryOf(::NotificationScreenModel)
    factoryOf(::ProfileScreenModel)
}
