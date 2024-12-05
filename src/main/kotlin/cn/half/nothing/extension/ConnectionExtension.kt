package cn.half.nothing.extension

import java.sql.Connection

inline fun <R> Connection.useCommit(block: (Connection) -> R): R {
    this.use {
        val ret = block(this)
        commit()
        return ret
    }
}
