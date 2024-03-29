package nay.kirill.glassOfWater.main.di

import nay.kirill.glassOfWater.counter.WaterCounterViewModel
import nay.kirill.glassOfWater.data.ConfigRepositoryImpl
import nay.kirill.glassOfWater.data.HealthParamsRepositoryImpl
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.glassOfWater.stat.WaterStatisticsViewModel
import nay.kirill.healthcare.domain.repositories.ConfigRepository
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.GetTodayParamsUseCase
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.UpdateTodayWaterUseCase
import nay.kirill.settings.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GetTodayParamsUseCase)
    singleOf(::UpdateTodayWaterUseCase)
    singleOf(::GetAllParamsUseCase)

    singleOf(::GetAppConfigUseCase)
    singleOf(::SaveAppConfigUseCase)
    singleOf(::ObserveAppConfigUseCase)

    singleOf(::HealthParamsRepositoryImpl).bind<HealthParamsRepository>()
    singleOf(::ConfigRepositoryImpl).bind<ConfigRepository>()

    factoryOf(::WaterCounterViewModel)
    factoryOf(::WaterStatisticsViewModel)
    factoryOf(::SettingsViewModel)

    singleOf(::Navigation)
}

expect val platformModule: Module