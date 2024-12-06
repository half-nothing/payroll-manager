package cn.half.nothing.dao.impl

import cn.half.nothing.utils.SecurityUtils
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UserDaoImplTest {

    @Test
    fun getById() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getById(1)
        assertNotNull(userEntity)
        println(userEntity)
    }

    @Test
    fun getByUsername() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getByUsername("Half_nothing")
        assertNotNull(userEntity)
        println(userEntity)
    }

    @Test
    fun getAllUser() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getAllUser()
        assertNotNull(userEntity)
        userEntity.forEach {
            println(it)
        }
    }

    @Test
    fun updateUser() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getById(1)
        assertNotNull(userEntity)
        userEntity.admin = true
        assertEquals(userDao.updateUser(userEntity), 1)
    }

    @Test
    fun deleteUserById() {
        val userDao = UserDaoImpl.new()
        assertEquals(userDao.deleteUserById(10), 1)
    }

    @Test
    fun insertUser() {
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getById(5)
        assertNotNull(userEntity)
        userEntity.admin = true
        userEntity.username = SecurityUtils.getSalt()
        println(userEntity)
        assertEquals(userDao.insertUser(userEntity), 1)
        println(userEntity)
    }
}
