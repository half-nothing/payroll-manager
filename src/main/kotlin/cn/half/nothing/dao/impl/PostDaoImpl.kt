package cn.half.nothing.dao.impl

import cn.half.nothing.dao.PostDao
import cn.half.nothing.entity.PostEntity
import cn.half.nothing.extension.useCommit
import cn.half.nothing.utils.DBUtils
import java.sql.Statement
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

    override fun getById(id: Int): PostEntity? {
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
                    WHERE `posts`.`id` = ?
                """.trimIndent()
            )
            statement.setInt(1, id)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return PostEntity(resultSet)
            }
            return null
        }
    }

    override fun insertPost(post: PostEntity): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement(
                "INSERT INTO `posts`(`post_name`, `post_level`) VALUE (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            )
            return with(statement) {
                setString(1, post.postName)
                setString(2, post.postLevel.name)
                val effectRows = executeUpdate()
                val resultSet = generatedKeys
                if (resultSet.next()) {
                    post.id = resultSet.getInt(1)
                }
                effectRows
            }
        }
    }

    override fun updatePost(post: PostEntity): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement("UPDATE `posts` SET `post_name` = ?, `post_level` = ? WHERE `id` = ?;")
            return with(statement) {
                setString(1, post.postName)
                setString(2, post.postLevel.name)
                setInt(3, post.id)
                executeUpdate()
            }
        }
    }

    override fun deletePostById(id: Int): Int {
        dataSource.connection.useCommit {
            val statement = it.prepareStatement("DELETE FROM `posts` WHERE `id` = ?")
            return with(statement) {
                setInt(1, id)
                executeUpdate()
            }
        }
    }

    override fun getAllPost(): List<PostEntity> {
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
                """.trimIndent()
            )
            val resultSet = statement.executeQuery()
            val postEntities = mutableListOf<PostEntity>()
            while (resultSet.next()) {
                postEntities.add(PostEntity(resultSet))
            }
            return postEntities
        }
    }

    companion object {
        fun new(): PostDao = PostDaoImpl(DBUtils.getDataSource())
    }
}
