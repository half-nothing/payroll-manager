package cn.half.nothing.utils

import com.alibaba.fastjson2.to
import com.alibaba.fastjson2.toJSONString

object JsonUtils {
    fun toJson(obj: Any): String {
        return obj.toJSONString()
    }

    inline fun <reified T> fromJson(json: String): T {
        return json.to<T>()
    }
}
