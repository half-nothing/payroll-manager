package cn.half.nothing.dao.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
}
