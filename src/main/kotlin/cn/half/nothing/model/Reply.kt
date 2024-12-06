package cn.half.nothing.model

data class Reply(
    val status: Boolean,
    val message: String? = null,
    val data: Any? = null
) {
    companion object {
        fun success(data: Any): Reply {
            return Reply(true, "success", data)
        }

        fun fail(msg: String): Reply {
            return Reply(false, msg)
        }
    }
}
