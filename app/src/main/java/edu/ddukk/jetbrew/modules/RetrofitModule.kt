package edu.ddukk.jetbrew.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import edu.ddukk.jetbrew.api.MongoAuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(dagger.hilt.components.SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://172.16.169.11:4040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMongoAuthApi(retrofit: Retrofit): MongoAuthApi {
        return retrofit.create(MongoAuthApi::class.java)
    }
}