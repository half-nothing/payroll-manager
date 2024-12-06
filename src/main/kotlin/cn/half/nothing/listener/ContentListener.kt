package cn.half.nothing.listener

import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener
import org.apache.logging.log4j.ThreadContext

@WebListener
class ContentListener : ServletContextListener {
    override fun contextDestroyed(sce: ServletContextEvent?) {
        ThreadContext.clearAll()
    }
}
