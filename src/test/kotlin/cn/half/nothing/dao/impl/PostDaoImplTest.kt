package cn.half.nothing.dao.impl

import cn.half.nothing.entity.PostEntity
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PostDaoImplTest {

    @Test
    fun getIdByPostName() {
        val postDao = PostDaoImpl.new()
        assertEquals(postDao.getIdByPostName("经理"), 2)
        assertNull(postDao.getIdByPostName("AAA"))
    }

    @Test
    fun getByPostName() {
        val postDao = PostDaoImpl.new()
        val postEntity = postDao.getByPostName("董事长")
        assertNotNull(postEntity)
        println(postEntity)
    }

    @Test
    fun getById() {
        val postDao = PostDaoImpl.new()
        val postEntity = postDao.getById(1)
        assertNotNull(postEntity)
        println(postEntity)
    }

    @Test
    fun insertPost() {
        val postDao = PostDaoImpl.new()
        val paymentDao = PaymentDaoImpl.new()
        paymentDao.getById(1)?.let {
            val postEntity = PostEntity(-1, "11", it)
            println(postEntity)
            assertEquals(postDao.insertPost(postEntity), 1)
            println(postEntity)
        }
    }

    @Test
    fun updatePost() {
        val postDao = PostDaoImpl.new()
        val postEntity = postDao.getById(11)
        assertNotNull(postEntity)
        postEntity.postLevel.name = "M3"
        assertEquals(postDao.updatePost(postEntity), 1)
        println(postEntity)
    }

    @Test
    fun deletePostById() {
        val postDao = PostDaoImpl.new()
        assertEquals(postDao.deletePostById(11), 1)
    }

    @Test
    fun getAllPost() {
        val postDao = PostDaoImpl.new()
        val postEntities = postDao.getAllPost()
        postEntities.forEach {
            println(it)
        }
    }
}
