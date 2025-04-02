package edu.ddukk.jetbrew.api

import edu.ddukk.jetbrew.model.MongoAuthResponse
import edu.ddukk.jetbrew.model.MongoUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MongoAuthApi {
    @POST("/api/login")
    suspend fun login(@Body user: MongoUser): MongoAuthResponse

    @POST("/api/register")
    suspend fun register(@Body user: MongoUser): MongoAuthResponse
}