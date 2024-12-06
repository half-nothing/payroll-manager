package cn.half.nothing.dao

import cn.half.nothing.entity.PostEntity

interface PostDao {
    fun getIdByPostName(postName: String): Int?
    fun getByPostName(postName: String): PostEntity?
    fun getById(id: Int): PostEntity?
    fun insertPost(post: PostEntity): Int
    fun updatePost(post: PostEntity): Int
    fun deletePostById(id: Int): Int
    fun getAllPost(): List<PostEntity>
}
