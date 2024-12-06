package cn.half.nothing.entity

import java.sql.ResultSet

data class PostEntity(
    var id: Int,
    var postName: String,
    var postLevel: PaymentEntity
) {
    constructor(resultSet: ResultSet) : this(
        resultSet.getInt("post_id"),
        resultSet.getString("post_name"),
        PaymentEntity(resultSet)
    )
}
