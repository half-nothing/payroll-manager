package cn.half.nothing.dao.impl

import cn.half.nothing.dao.UserDao
import cn.half.nothing.entity.UserEntity
import cn.half.nothing.extension.useCommit
import cn.half.nothing.utils.DBUtils
import java.sql.Statement
import javax.sql.DataSource

class UserDaoImpl(private val dataSource: DataSource) : UserDao {
    override fun getById(id: Int): UserEntity? {
        dataSource.connection.useCommit {
            val statement =
                it.prepareStatement(
                    """
                    SELECT 
                        `users`.*, 
                        `posts`.`id` AS `post_id`, 
                        `posts`.`post_name`, 
                        `levels`.`id` AS `level_id`, 
                        `levels`.`level`, 
                        `levels`.`payment`
                    FROM `levels` 
                        INNER JOIN `posts` ON `levels`.`level` = `posts`.`post_level` 
                        INNER JOIN `users` ON `posts`.`id` = `users`.`post`
                    WHERE `users`.`id` = ?
                """.trimIndent()
                )
            statement.setInt(1, id)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return UserEntity(resultSet)
            }
            return null
        }
    }

    override fun getByUsername(username: String): UserEntity? {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    SELECT 
                        `users`.*, 
                        `posts`.`id` AS `post_id`, 
                        `posts`.`post_name`, 
                        `levels`.`id` AS `level_id`, 
                        `levels`.`level`, 
                        `levels`.`payment`
                    FROM `levels` 
                        INNER JOIN `posts` ON `levels`.`level` = `posts`.`post_level` 
                        INNER JOIN `users` ON `posts`.`id` = `users`.`post`
                    WHERE `username` = ?
                """.trimIndent()
            )
            statement.setString(1, username)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return UserEntity(resultSet)
            }
            return null
        }
    }

    override fun getAllUser(): List<UserEntity> {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    SELECT 
                        `users`.*, 
                        `posts`.`id` AS `post_id`, 
                        `posts`.`post_name`, 
                        `levels`.`id` AS `level_id`, 
                        `levels`.`level`, 
                        `levels`.`payment`
                    FROM `levels` 
                        INNER JOIN `posts` ON `levels`.`level` = `posts`.`post_level` 
                        INNER JOIN `users` ON `posts`.`id` = `users`.`post`
                """.trimIndent()
            )
            val resultSet = statement.executeQuery()
            val userEntities = mutableListOf<UserEntity>()
            while (resultSet.next()) {
                userEntities.add(UserEntity(resultSet))
            }
            return userEntities
        }
    }

    override fun updateUser(user: UserEntity): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    UPDATE `users` 
                    SET `nickname` = ?, `username` = ?, `admin` = ?, `post` = ?, `real_pay` = ? 
                    WHERE `id` = ?
                """.trimIndent()
            )
            return with(statement) {
                setString(1, user.nickname)
                setString(2, user.username)
                setBoolean(3, user.admin)
                setInt(4, user.post.id)
                setDouble(5, user.realPay)
                setInt(6, user.id)
                executeUpdate()
            }
        }
    }

    override fun deleteUserById(id: Int): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    DELETE FROM `users` WHERE `id` = ?
                """.trimIndent()
            )
            return with(statement) {
                setInt(1, id)
                executeUpdate()
            }
        }
    }

    override fun insertUser(user: UserEntity): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    INSERT INTO `users`(`nickname`, `username`, `password`, `salt`, `admin`, `post`, `real_pay`)
                    VALUE(?, ?, ?, ?, ?, ?, ?)
                """.trimIndent(),
                    Statement.RETURN_GENERATED_KEYS
            )
            return with(statement) {
                setString(1, user.nickname)
                setString(2, user.username)
                setString(3, user.password)
                setString(4, user.salt)
                setBoolean(5, user.admin)
                setInt(6, user.post.id)
                setDouble(7, user.realPay)
                val effectRows = executeUpdate()
                val resultSet = generatedKeys
                if (resultSet.next()) {
                    user.id = resultSet.getInt(1)
                }
                effectRows
            }
        }
    }

    companion object {
        fun new(): UserDao = UserDaoImpl(DBUtils.getDataSource())
    }
}
