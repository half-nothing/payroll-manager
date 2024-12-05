package cn.half.nothing.dao

import cn.half.nothing.entity.UserEntity

interface UserDao {
    fun getUserById(id: Int): UserEntity?
    fun getUserByUsername(username: String): UserEntity?
    fun getAllUser(): List<UserEntity>
    fun updateUser(user: UserEntity): Int
    fun deleteUserById(id: Int): Int
    fun insertUser(user: UserEntity): Int
}
