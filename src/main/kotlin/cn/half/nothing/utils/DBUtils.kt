package cn.half.nothing.utils

import org.apache.commons.dbcp2.BasicDataSourceFactory
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.FileInputStream
import java.net.URL
import java.util.*
import javax.sql.DataSource
import kotlin.system.exitProcess

object DBUtils {
    private val LOGGER: Logger = LogManager.getLogger(DBUtils::class)
    private var dataSource: DataSource

    init {
        try {
            val prop = Properties()
            val configUrl: URL? = DBUtils.javaClass.classLoader.getResource("dbcpconfig.properties")
            require(configUrl != null) { "dbcpconfig.properties not found" }
            val inputStream = FileInputStream(configUrl.path)
            prop.load(inputStream)
            dataSource = BasicDataSourceFactory.createDataSource(prop)
        } catch (e: Exception) {
            LOGGER.fatal(e.toString())
            exitProcess(-1)
        }
    }

    fun getDataSource(): DataSource {
        return dataSource
    }
}
