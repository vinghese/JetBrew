package edu.ddukk.jetbrew.repository

import edu.ddukk.jetbrew.dao.UserDao
import edu.ddukk.jetbrew.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryHilt @Inject constructor(private val userDao: UserDao) {
    suspend fun registerUser(user: User) = userDao.insertUser(user)
    suspend fun loginUser(email: String, password: String) = userDao.getUser(email, password) != null
}