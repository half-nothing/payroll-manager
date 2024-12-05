package cn.half.nothing.utils

import cn.half.nothing.extension.useCommit
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class DBUtilsTest {

    @Test
    fun getDataSource() {
        DBUtils.getDataSource().connection.useCommit {
            assertNotNull(it)
            val result = it.prepareStatement("SELECT VERSION() as `version`").executeQuery()
            assertTrue(result.next())
            println("Mysql Version: " + result.getString("version"))
        }
    }
}
