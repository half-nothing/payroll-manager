package cn.half.nothing.servlet

import cn.half.nothing.dao.impl.UserDaoImpl
import cn.half.nothing.utils.SecurityUtils
import cn.hutool.crypto.SecureUtil
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/login"])
class LoginServlet : HttpServlet() {
    private val loginCookieName = "loginState"

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("index.jsp").forward(request, response)
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        request.session.removeAttribute("msg")
        request.session.removeAttribute("username")
        request.session.setAttribute("login", false)
        val userDao = UserDaoImpl.new()
        request.cookies.find { it.name == loginCookieName }?.let {
            val store = it.value.split(".")
            val user = userDao.getUserByUsername(store[0])
            user?.let {
                if (SecureUtil.md5(user.password) == store[1]) {
                    request.session.setAttribute("login", true)
                    request.getRequestDispatcher("login.jsp").forward(request, response)
                }
            }
            return
        }
        val username = request.getParameter("username")
        val password = request.getParameter("password")
        val rememberMe = request.getParameter("rememberMe")
        val user = userDao.getUserByUsername(username)
        user?.let {
            val encryptedPassword = SecurityUtils.encryptPassword(password, it.salt)
            if (encryptedPassword == user.password) {
                rememberMe?.let {
                    val cookie = Cookie(loginCookieName, "$username.${SecureUtil.md5(user.password)}")
                    cookie.maxAge = 3600 * 24 * 7
                    cookie.path = "/"
                    response.addCookie(cookie)
                }
                request.session.setAttribute("login", true)
                request.session.setAttribute("username", username)
                request.getRequestDispatcher("login.jsp").forward(request, response)
                return
            }
        }
        request.session.setAttribute("msg", "用户不存在或密码错误")
        request.getRequestDispatcher("login.jsp").forward(request, response)
    }
}
