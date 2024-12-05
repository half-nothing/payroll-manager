package cn.half.nothing.entity

import java.sql.ResultSet

data class PostLevelEntity(
    var id: Int,
    var postLevel: String,
    var postPayment: Int
) {
    constructor(resultSet: ResultSet) : this(
        resultSet.getInt("level_id"),
        resultSet.getString("level"),
        resultSet.getInt("payment")
    )
}
