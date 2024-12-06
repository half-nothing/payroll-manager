package cn.half.nothing.servlet.auth

import cn.half.nothing.Constant.LOGIN_COOKIE_NAME
import cn.half.nothing.dao.impl.UserDaoImpl
import cn.half.nothing.extension.redirect
import cn.half.nothing.extension.setSession
import cn.half.nothing.utils.SecurityUtils
import cn.hutool.crypto.SecureUtil
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/auth/login"])
class LoginServlet : HttpServlet() {
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        request.session.invalidate()
        val userDao = UserDaoImpl.new()
        request.cookies.find { it.name == LOGIN_COOKIE_NAME }?.let {
            val store = it.value.split(".")
            val user = userDao.getByUsername(store[0])
            user?.let {
                if (SecureUtil.md5(user.password) == store[1]) {
                    request.setSession("login", true).setSession("user", user)
                    response.redirect(request, "home.jsp")
                }
            }
            return
        }
        val username = request.getParameter("username")
        val password = request.getParameter("password")
        val rememberMe = request.getParameter("rememberMe")
        val user = userDao.getByUsername(username)
        user?.let {
            val encryptedPassword = SecurityUtils.encryptPassword(password, it.salt)
            if (encryptedPassword == user.password) {
                rememberMe?.let {
                    val cookie = Cookie(LOGIN_COOKIE_NAME, "$username.${SecureUtil.md5(user.password)}")
                    cookie.maxAge = 3600 * 24 * 7
                    cookie.path = "/"
                    response.addCookie(cookie)
                }
                request.setSession("login", true).setSession("user", user)
                response.redirect(request, "home.jsp")
                return
            }
        }
        request.setSession("msg", "用户不存在或密码错误")
        response.redirect(request, "login.jsp")
    }
}
