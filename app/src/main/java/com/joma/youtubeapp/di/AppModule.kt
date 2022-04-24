package com.joma.youtubeapp.di

import com.joma.youtubeapp.BuildConfig.BASE_URL
import com.joma.youtubeapp.data.remote.ApiService
import com.joma.youtubeapp.repository.PlaylistRepository
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

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providerApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providerClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(2000, TimeUnit.SECONDS)
            .writeTimeout(2000, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(
                        HttpLoggingInterceptor
                            .Level
                            .BODY
                    )
            ).build()
    }

    @Provides
    @Singleton
    fun providerRepository(api: ApiService): PlaylistRepository {
        return PlaylistRepository(api)
    }
}