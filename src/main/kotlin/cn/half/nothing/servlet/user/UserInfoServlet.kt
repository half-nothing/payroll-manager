package cn.half.nothing.servlet.user

import cn.half.nothing.model.Reply
import cn.half.nothing.model.UserInfo
import cn.half.nothing.dao.impl.PostDaoImpl
import cn.half.nothing.dao.impl.UserDaoImpl
import cn.half.nothing.entity.UserEntity
import cn.half.nothing.extension.code
import cn.half.nothing.extension.getPathParameter
import cn.half.nothing.extension.json
import cn.half.nothing.extension.toJson
import cn.half.nothing.utils.SecurityUtils
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/users/info/*", "/users/*"])
class UserInfoServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val userId = request.getPathParameter()
        if (userId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("User ID not found"))
            return
        }
        val userDao = UserDaoImpl.new()
        val userEntity = userDao.getById(userId.toInt())
        if (userEntity == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("User not found"))
            return
        }
        response.code(HttpServletResponse.SC_OK).json(Reply.success(UserInfo.fromUserEntity(userEntity)))
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val userInfo = request.toJson<UserInfo>()
        val postDao = PostDaoImpl.new()
        val postEntity = postDao.getByPostName(userInfo.postName)
        if (postEntity == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Post not found"))
            return
        }
        val salt = SecurityUtils.getSalt()
        val userEntity = UserEntity(
            -1,
            userInfo.nickName,
            userInfo.username,
            SecurityUtils.encryptPassword("123456", salt),
            salt,
            false,
            userInfo.realPayment,
            postEntity
        )
        val userDao = UserDaoImpl.new()
        if (userDao.insertUser(userEntity) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Add failed"))
        }
    }

    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) {
        val userId = request.getPathParameter()
        if (userId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("User ID not found"))
            return
        }
        val userInfo = request.toJson<UserInfo>()
        if (userId.toInt() != userInfo.id) {
            response.code(HttpServletResponse.SC_BAD_REQUEST).json(Reply.fail("User ID not match"))
            return
        }
        val userEntity = userInfo.toUserEntity()
        if (userEntity == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("User not found"))
            return
        }
        val userDao = UserDaoImpl.new()
        if (userDao.updateUser(userEntity) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Update failed"))
        }
    }

    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {
        val userId = request.getPathParameter()
        if (userId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("User ID not found"))
            return
        }
        val userDao = UserDaoImpl.new()
        if (userDao.deleteUserById(userId.toInt()) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Delete failed"))
        }
    }
}
