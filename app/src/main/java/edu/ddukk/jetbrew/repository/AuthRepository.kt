package edu.ddukk.jetbrew.repository

import edu.ddukk.jetbrew.dao.UserDao
import edu.ddukk.jetbrew.model.User

class AuthRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return userDao.getUser(email, password) != null
    }
}