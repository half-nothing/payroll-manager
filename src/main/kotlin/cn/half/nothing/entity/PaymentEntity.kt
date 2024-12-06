package cn.half.nothing.entity

import java.sql.ResultSet

data class PaymentEntity(
    var id: Int,
    var name: String,
    var payment: Double
) {
    constructor(resultSet: ResultSet) : this(
        resultSet.getInt("level_id"),
        resultSet.getString("level"),
        resultSet.getDouble("payment")
    )
}
