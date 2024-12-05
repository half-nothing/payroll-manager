package cn.half.nothing.dao

import cn.half.nothing.entity.PostEntity

interface PostDao {
    fun getIdByPostName(postName: String): Int?
    fun getByPostName(postName: String): PostEntity?
}
