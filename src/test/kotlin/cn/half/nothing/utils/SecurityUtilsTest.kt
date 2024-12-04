package cn.half.nothing.utils

import org.junit.jupiter.api.Test

class SecurityUtilsTest {

    @Test
    fun getSalt() {
        val salt = SecurityUtils.getSalt()
        require(salt.isNotEmpty())
        println(salt)
    }

    @Test
    fun encryptPassword() {
        val password = "123456"
        val salt = "4LwnxzGGAzZtJH1zA4Fxc27wZ8xQjWop"
        val encryptedPassword = SecurityUtils.encryptPassword(password, salt)
        println(encryptedPassword)
        require(encryptedPassword == "e810c36b167a1edfebad63b5157457540cf834afbd522099b3975883fd1f5ef4")
    }
}
