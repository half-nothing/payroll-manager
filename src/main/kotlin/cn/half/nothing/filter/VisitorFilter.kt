package cn.half.nothing.filter

import jakarta.servlet.*
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.annotation.WebInitParam
import java.util.concurrent.atomic.AtomicInteger

@WebFilter(
    filterName = "VisitorCounter",
    urlPatterns = ["*.jsp"],
    initParams = [WebInitParam(name = "count", value = "0")]
)
class VisitorFilter : Filter {
    private val count = AtomicInteger(0)

    override fun doFilter(request: ServletRequest, response: ServletResponse, filter: FilterChain) {
        request.servletContext.setAttribute("historyVisitor", count.incrementAndGet())
        filter.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig?) {
        count.set(Integer.parseInt(filterConfig?.getInitParameter("count")))
    }
}
