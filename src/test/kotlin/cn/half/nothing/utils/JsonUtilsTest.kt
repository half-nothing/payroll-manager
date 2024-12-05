package cn.half.nothing.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class JsonUtilsTest {
    data class Person(val name: String, val age: Int)

    @Test
    fun toJson() {
        val person = Person("Hallo", 18)
        val json = JsonUtils.toJson(person)
        assertNotNull(json)
        println(json)
    }

    @Test
    fun fromJson() {
        val json = "{\"name\":\"Hallo\", \"age\":18}"
        val person = JsonUtils.fromJson<Person>(json)
        assertEquals(person.name, "Hallo")
        assertEquals(person.age, 18)
        println(person)
    }
}
