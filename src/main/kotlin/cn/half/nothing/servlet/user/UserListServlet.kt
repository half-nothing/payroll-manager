package cn.half.nothing.servlet.user

import cn.half.nothing.dao.impl.UserDaoImpl
import cn.half.nothing.extension.redirect
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/users/list"])
class UserListServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.session.removeAttribute("userList")
        val userDao = UserDaoImpl.new()
        val userList = userDao.getAllUser()
        request.session.setAttribute("userList", userList)
        response.redirect(request, "manager.jsp")
    }
}
