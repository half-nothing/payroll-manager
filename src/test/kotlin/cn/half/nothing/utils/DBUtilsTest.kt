package cn.half.nothing.utils

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class DBUtilsTest {

    @Test
    fun getDataSource() {
        val connection = DBUtils.getDataSource().connection
        assertNotNull(connection)
        val result = connection.prepareStatement("SELECT VERSION() as `version`").executeQuery()
        assertTrue(result.next())
        println("Mysql Version: " + result.getString("version"))
        connection.commit()
        connection.close()
    }
}
