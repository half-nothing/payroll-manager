package cn.half.nothing.extension

import cn.half.nothing.utils.JsonUtils
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

fun HttpServletResponse.code(statusCode: Int): HttpServletResponse {
    this.status = statusCode
    return this
}

fun HttpServletResponse.json(data: Any) {
    this.contentType = "application/json; charset=utf-8"
    this.writer.write(JsonUtils.toJson(data))
}

fun HttpServletResponse.redirect(request: HttpServletRequest, path: String) {
    val fullUrl = request.requestURL
    var rootUrl = fullUrl.substring(0, fullUrl.indexOf(request.servletPath))
    if (!path.startsWith("/") && !rootUrl.endsWith("/")) {
        rootUrl += "/"
    }
    this.sendRedirect(rootUrl + path)
}

fun HttpServletResponse.removeCookie(key: String): HttpServletResponse {
    val cookie = Cookie(key, null)
    cookie.maxAge = 0
    cookie.path = "/"
    addCookie(cookie)
    return this
}
