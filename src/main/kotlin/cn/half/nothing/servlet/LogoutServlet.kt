package cn.half.nothing.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/logout"])
class LogoutServlet : HttpServlet() {
    private val loginCookieName = "loginState"
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.session.invalidate()
        val cookie = Cookie(loginCookieName, null)
        cookie.maxAge = 0
        response.addCookie(cookie)
        response.sendRedirect("home.jsp")
    }
}
