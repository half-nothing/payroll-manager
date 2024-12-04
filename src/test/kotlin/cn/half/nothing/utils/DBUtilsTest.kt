package cn.half.nothing.utils

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class DBUtilsTest {

    @Test
    fun getDataSource() {
        DBUtils.getDataSource().connection.use {
            assertNotNull(it)
            val result = it.prepareStatement("SELECT VERSION() as `version`").executeQuery()
            assertTrue(result.next())
            println("Mysql Version: " + result.getString("version"))
        }
    }
}
