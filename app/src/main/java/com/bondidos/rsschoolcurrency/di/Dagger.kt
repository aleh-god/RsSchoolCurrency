package com.bondidos.rsschoolcurrency.di

import android.app.Application
import com.bondidos.rsschoolcurrency.data.CurrencyRepositoryImpl
import com.bondidos.rsschoolcurrency.data.network_service.CurrencyService
import com.bondidos.rsschoolcurrency.domain.repository.CurrencyRepository
import com.bondidos.rsschoolcurrency.domain.use_cases.CalculateMultiplierUseCase
import com.bondidos.rsschoolcurrency.presentation.fragments.HomeFragment
import com.bondidos.rsschoolcurrency.domain.mapers.CalculateRatesMapper
import com.bondidos.rsschoolcurrency.domain.mapers.DomainToUiCurrencyMapper
import com.bondidos.rsschoolcurrency.domain.mapers.MoveToTheTopMapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val base_url = "https://us-central1-epam-laba-13-1527598553135.cloudfunctions.net"

@Component(modules = [RepositoryModule::class, Domain::class, Binding::class])
@Singleton
interface AppComponent {
    fun injectMain(home: HomeFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
interface Binding {
    @Binds
    fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository
}

@Module
object Domain {
    @Provides
    fun provideModelUiMapper(): DomainToUiCurrencyMapper = DomainToUiCurrencyMapper()

    @Provides
    fun provideMoveToTheTopMapper(): MoveToTheTopMapper = MoveToTheTopMapper()

    @Provides
    fun calculateRatesMapper(): CalculateRatesMapper = CalculateRatesMapper()

    @Provides
    fun calculateMultiplier(): CalculateMultiplierUseCase = CalculateMultiplierUseCase()
}

@Module(includes = [NetworkModule::class])
object RepositoryModule

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService =
        retrofit.create(CurrencyService::class.java)

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory
            .create(moshi)
}