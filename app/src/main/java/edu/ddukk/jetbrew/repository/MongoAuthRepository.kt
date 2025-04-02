package edu.ddukk.jetbrew.repository

import edu.ddukk.jetbrew.api.MongoAuthApi
import edu.ddukk.jetbrew.model.MongoAuthResponse
import edu.ddukk.jetbrew.model.MongoUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MongoAuthRepository @Inject constructor(private val api: MongoAuthApi) {
    suspend fun login(user: MongoUser): MongoAuthResponse {
        return api.login(user)
    }

    suspend fun register(user: MongoUser): MongoAuthResponse {
        return api.register(user)
    }
}