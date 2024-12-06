package cn.half.nothing.extension

import cn.half.nothing.utils.JsonUtils
import jakarta.servlet.http.HttpServletRequest

fun HttpServletRequest.setSession(key: String, value: Any): HttpServletRequest {
    session.setAttribute(key, value)
    return this
}

fun HttpServletRequest.getPathParameter(): String? = pathInfo?.takeIf { it.length > 1 }?.substring(1)

fun HttpServletRequest.getBody(): String = buildString { reader.forEachLine { append(it) } }

inline fun <reified T> HttpServletRequest.toJson(): T = JsonUtils.fromJson<T>(this.getBody())
