package cn.half.nothing.entity

import java.sql.ResultSet

data class UserEntity(
    var id: Int,
    var nickname: String,
    var username: String,
    var password: String,
    var salt: String,
    var admin: Boolean,
    var realPay: Double,
    var post: PostEntity
) {
    constructor(resultSet: ResultSet) : this(
        resultSet.getInt("id"),
        resultSet.getString("nickname"),
        resultSet.getString("username"),
        resultSet.getString("password"),
        resultSet.getString("salt"),
        resultSet.getBoolean("admin"),
        resultSet.getDouble("real_pay"),
        PostEntity(resultSet)
    ) {
        if (realPay == 0.0) {
            realPay = post.postLevel.payment
        }
    }
}
