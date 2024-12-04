package cn.half.nothing.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/logout"])
class LogoutServlet : HttpServlet() {
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        super.doPost(request, response)
    }
}
