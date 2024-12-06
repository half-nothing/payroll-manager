package cn.half.nothing.dao.impl

import cn.half.nothing.dao.PaymentDao
import cn.half.nothing.entity.PaymentEntity
import cn.half.nothing.extension.useCommit
import cn.half.nothing.utils.DBUtils
import java.sql.Statement
import javax.sql.DataSource

class PaymentDaoImpl(private val dataSource: DataSource) : PaymentDao {
    override fun getById(id: Int): PaymentEntity? {
        dataSource.connection.useCommit {
            val statement =
                it.prepareStatement("SELECT `id` AS `level_id`, `level`, `payment` FROM `levels` WHERE id = ?")
            statement.setInt(1, id)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return PaymentEntity(resultSet)
            }
            return null
        }
    }

    override fun insertPayment(payment: PaymentEntity): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    INSERT INTO `levels`(`level`, `payment`) VALUE (?, ?)
                """.trimIndent(),
                    Statement.RETURN_GENERATED_KEYS
            )
            return with(statement) {
                setString(1, payment.name)
                setDouble(2, payment.payment)
                val effectRows = executeUpdate()
                val resultSet = generatedKeys
                if (resultSet.next()) {
                    payment.id = resultSet.getInt(1)
                }
                effectRows
            }
        }
    }

    override fun updatePayment(payment: PaymentEntity): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement("UPDATE `levels` SET `level` = ?, `payment` = ? WHERE `id` = ?")
            return with(statement) {
                setString(1, payment.name)
                setDouble(2, payment.payment)
                setInt(3, payment.id)
                executeUpdate()
            }
        }
    }

    override fun deletePaymentById(id: Int): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement("DELETE FROM `levels` WHERE id = ?")
            return with(statement) {
                setInt(1, id)
                executeUpdate()
            }
        }
    }

    override fun getAllPayment(): List<PaymentEntity> {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement("SELECT `id` AS `level_id`, `level`, `payment` FROM `levels`")
            val resultSet = statement.executeQuery()
            val postLevelEntities = mutableListOf<PaymentEntity>()
            while (resultSet.next()) {
                postLevelEntities.add(PaymentEntity(resultSet))
            }
            return postLevelEntities
        }
    }

    companion object {
        fun new() = PaymentDaoImpl(DBUtils.getDataSource())
    }
}
