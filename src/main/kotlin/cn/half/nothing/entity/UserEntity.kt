package cn.half.nothing.entity

import java.sql.ResultSet

data class UserEntity(
    val id: Int,
    val username: String,
    val password: String,
    val salt: String,
    val post: String,
    val payment: PostEntity
) {
    constructor(resultSet: ResultSet) : this(
        resultSet.getInt("id"),
        resultSet.getString("username"),
        resultSet.getString("password"),
        resultSet.getString("salt"),
        resultSet.getString("post"),
        PostEntity(
            resultSet.getInt("id2"),
            resultSet.getInt("level"),
            resultSet.getInt("payment")
        )
    )
}
