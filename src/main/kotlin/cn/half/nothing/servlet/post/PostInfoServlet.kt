package cn.half.nothing.servlet.post

import cn.half.nothing.model.Reply
import cn.half.nothing.dao.impl.PostDaoImpl
import cn.half.nothing.entity.PostEntity
import cn.half.nothing.extension.code
import cn.half.nothing.extension.getPathParameter
import cn.half.nothing.extension.json
import cn.half.nothing.extension.toJson
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/posts/info/*", "/posts/*"])
class PostInfoServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val postId = request.getPathParameter()
        if (postId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Post ID not found"))
            return
        }
        val postDao = PostDaoImpl.new()
        val postEntity = postDao.getById(postId.toInt())
        if (postEntity == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Post not found"))
            return
        }
        response.code(HttpServletResponse.SC_OK).json(Reply.success(postEntity))
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val postEntity = request.toJson<PostEntity>()
        val postDao = PostDaoImpl.new()
        if (postDao.insertPost(postEntity) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Add failed"))
        }
    }

    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) {
        val postId = request.getPathParameter()
        if (postId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Post ID not found"))
            return
        }
        val postEntity = request.toJson<PostEntity>()
        if (postId.toInt() != postEntity.id) {
            response.code(HttpServletResponse.SC_BAD_REQUEST).json(Reply.fail("Post ID not match"))
            return
        }
        val postDao = PostDaoImpl.new()
        if (postDao.updatePost(postEntity) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Update failed"))
        }
    }

    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {
        val postId = request.getPathParameter()
        if (postId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Post ID not found"))
            return
        }
        val postDao = PostDaoImpl.new()
        if (postDao.deletePostById(postId.toInt()) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Delete failed"))
        }
    }
}
