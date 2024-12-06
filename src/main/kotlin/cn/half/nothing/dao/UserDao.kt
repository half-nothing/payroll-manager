package cn.half.nothing.dao

import cn.half.nothing.entity.UserEntity

interface UserDao {
    fun getById(id: Int): UserEntity?
    fun getByUsername(username: String): UserEntity?
    fun getAllUser(): List<UserEntity>
    fun updateUser(user: UserEntity): Int
    fun deleteUserById(id: Int): Int
    fun insertUser(user: UserEntity): Int
}
