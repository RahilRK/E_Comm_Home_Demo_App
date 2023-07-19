package com.example.e_comm_home_demo_app.di

import com.example.e_comm_home_demo_app.common.AuthIntercept
import com.example.e_comm_home_demo_app.data.remote.ApiInterface
import com.example.e_comm_home_demo_app.data.repository.MainRepositoryImpl
import com.example.e_comm_home_demo_app.domain.repository.DomainRepository
import com.example.e_comm_home_demo_app.common.Constants.BASE_URL
import com.example.e_comm_home_demo_app.data.local.MyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authIntercept: AuthIntercept): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authIntercept)
            .build()

        return builder.build()
    }

    @Provides
    fun provideHttpHandleIntercept(): AuthIntercept = AuthIntercept()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun provideDomainRepository(apiInterface: ApiInterface, dao: MyDao): DomainRepository {
        return MainRepositoryImpl(apiInterface, dao)
    }
}