package cn.half.nothing.filter

import cn.half.nothing.extension.redirect
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/users/*", "/posts/*", "/payments/*", "/logout"])
class LoginFitter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, filter: FilterChain) {
        request as HttpServletRequest
        response as HttpServletResponse
        val session = request.session
        if (session.getAttribute("login") == null) {
            response.redirect(request, "login.jsp")
            return
        }
        filter.doFilter(request, response)
    }
}
