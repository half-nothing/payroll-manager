package cn.half.nothing.dao

import cn.half.nothing.entity.UserEntity

interface UserDao {
    fun getUserById(id: Int): UserEntity?
    fun getUserByUsername(username: String): UserEntity?
}
