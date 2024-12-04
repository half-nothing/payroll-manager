package cn.half.nothing.dao.impl

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class UserDaoImplTest {

    @Test
    fun getUserById() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getUserById(1)
        assertNotNull(userEntity)
        println(userEntity)
    }

    @Test
    fun getUserByUsername() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getUserByUsername("Half_nothing")
        assertNotNull(userEntity)
        println(userEntity)
    }
}
