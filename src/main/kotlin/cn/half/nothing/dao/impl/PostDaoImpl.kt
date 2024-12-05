package cn.half.nothing.dao.impl

import cn.half.nothing.dao.PostDao
import cn.half.nothing.entity.PostEntity
import cn.half.nothing.extension.useCommit
import cn.half.nothing.utils.DBUtils
import javax.sql.DataSource

class PostDaoImpl(private val dataSource: DataSource) : PostDao {
    override fun getIdByPostName(postName: String): Int? {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement("SELECT `id` FROM `posts` WHERE `post_name` = ?;")
            statement.setString(1, postName)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return resultSet.getInt("id")
            }
            return null
        }
    }

    override fun getByPostName(postName: String): PostEntity? {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                """
                    SELECT 
                        `posts`.`id` AS `post_id`, 
                        `posts`.`post_name`, 
                        `levels`.`id` AS `level_id`, 
                        `levels`.`level`, 
                        `levels`.`payment`
                    FROM `levels` 
                        INNER JOIN `posts` ON `levels`.`level` = `posts`.`post_level`
                    WHERE `posts`.`post_name` = ?
                """.trimIndent()
            )
            statement.setString(1, postName)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return PostEntity(resultSet)
            }
            return null
        }
    }

    companion object {
        fun new(): PostDao = PostDaoImpl(DBUtils.getDataSource())
    }
}
