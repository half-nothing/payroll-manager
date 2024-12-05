package cn.half.nothing.servlet.auth

import cn.half.nothing.Constant.LOGIN_COOKIE_NAME
import cn.half.nothing.extension.redirect
import cn.half.nothing.extension.removeCookie
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/auth/logout"])
class LogoutServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.removeCookie(LOGIN_COOKIE_NAME)
        response.redirect(request, "login.jsp")
    }
}
