package cn.half.nothing.listener

import jakarta.servlet.annotation.WebListener
import jakarta.servlet.http.HttpSessionEvent
import jakarta.servlet.http.HttpSessionListener
import java.util.concurrent.atomic.AtomicInteger

@WebListener
class VisitorListener : HttpSessionListener {
    private val count = AtomicInteger(0)

    override fun sessionCreated(sessionEvent: HttpSessionEvent) {
        sessionEvent.session.servletContext.setAttribute("onlineVisitor", count.incrementAndGet())
    }

    override fun sessionDestroyed(sessionEvent: HttpSessionEvent) {
        sessionEvent.session.servletContext.setAttribute("onlineVisitor", count.decrementAndGet())
    }
}
