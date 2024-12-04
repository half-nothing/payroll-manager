package cn.half.nothing.dao.impl

import cn.half.nothing.dao.UserDao
import cn.half.nothing.entity.UserEntity
import cn.half.nothing.utils.DBUtils
import javax.sql.DataSource

class UserDaoImpl(private val dataSource: DataSource) : UserDao {
    override fun getUserById(id: Int): UserEntity? {
        dataSource.connection.use {
            val statement =
                it.prepareStatement(
                    "SELECT `users`.*, `posts`.`id` as id2, `posts`.`payment` " +
                            "FROM `users` INNER JOIN `posts` ON `posts`.`level` = `users`.`level` " +
                            "WHERE `users`.`id`=?"
                )
            statement.setInt(1, id)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return UserEntity(resultSet)
            }
            return null
        }
    }

    override fun getUserByUsername(username: String): UserEntity? {
        dataSource.connection.use {
            val statement = it.prepareStatement(
                "SELECT `users`.*, `posts`.`id` as id2, `posts`.`payment` " +
                        "FROM `users` INNER JOIN `posts` ON `posts`.`level` = `users`.`level` " +
                        "WHERE `users`.`username`=?"
            )
            statement.setString(1, username)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return UserEntity(resultSet)
            }
            return null
        }
    }

    companion object {
        fun new(): UserDao = UserDaoImpl(DBUtils.getDataSource())
    }
}
