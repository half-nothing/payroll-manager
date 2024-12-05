package cn.half.nothing.bean

import cn.half.nothing.dao.impl.PostDaoImpl
import cn.half.nothing.dao.impl.UserDaoImpl
import cn.half.nothing.entity.UserEntity

data class UserInfo(
    val id: Int,
    val nickName: String,
    val username: String,
    val postLevel: String,
    val postPayment: Int,
    val postName: String,
    val realPayment: Double,
    val admin: Boolean
) {
    constructor(userEntity: UserEntity) : this(
        userEntity.id,
        userEntity.nickname,
        userEntity.username,
        userEntity.post.postLevel.postLevel,
        userEntity.post.postLevel.postPayment,
        userEntity.post.postName,
        userEntity.realPay,
        userEntity.admin
    )

    fun toUserEntity(): UserEntity? {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getUserById(id)
        userEntity ?: return null
        userEntity.nickname = nickName
        userEntity.username = username
        userEntity.realPay = realPayment
        userEntity.admin = admin
        if (userEntity.post.postName != postName) {
            val postDao = PostDaoImpl.new()
            postDao.getIdByPostName(postName)?.let {
                userEntity.post.id = it
            }
        }
        return userEntity
    }

    companion object {
        fun fromUserEntity(userEntity: UserEntity): UserInfo = UserInfo(userEntity)
    }
}
