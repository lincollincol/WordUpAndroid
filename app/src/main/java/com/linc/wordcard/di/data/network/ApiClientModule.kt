package com.linc.wordcard.di.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                level = LogLevel.BODY
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                url("http:///192.168.88.19:8885")
                contentType(ContentType.Application.Json)
            }
        }
    }

}