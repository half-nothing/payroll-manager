package cn.half.nothing.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class JsonUtilsTest {
    data class Card(val id: Int, val name: String)
    data class Person(val name: String, val age: Int, val card: Card)

    @Test
    fun toJson() {
        val person = Person("Hallo", 18, Card(1, "Ttt"))
        val json = JsonUtils.toJson(person)
        assertNotNull(json)
        println(json)
    }

    @Test
    fun fromJson() {
        val json = "{\"age\":18,\"card\":{\"name\":\"Ttt\"},\"name\":\"Hallo\"}"
        val person = JsonUtils.fromJson<Person>(json)
        println(person)
    }
}
