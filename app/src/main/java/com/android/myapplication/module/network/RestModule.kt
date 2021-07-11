package com.android.myapplication.module.network

import com.android.myapplication.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author at-hien.dao
 */
@InstallIn(SingletonComponent::class)
@Module
class RestModule {

    companion object {
        var apiInstance: Api? = null
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            requestBuilder.addHeader("Content-Type", "application/json")
            val request = requestBuilder.build()
            chain
                .withConnectTimeout(40, TimeUnit.SECONDS)
                .withWriteTimeout(40, TimeUnit.SECONDS)
                .withReadTimeout(40, TimeUnit.SECONDS)
                .proceed(request)
        })

        clientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        jsonParser: Json
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideJsonParser(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java).also {
            apiInstance = it
        }
    }
}
