package cn.half.nothing.servlet.post

import cn.half.nothing.dao.impl.PostDaoImpl
import cn.half.nothing.extension.redirect
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/posts/list"])
class PostListServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.session.removeAttribute("postList")
        val postDao = PostDaoImpl.new()
        val postList = postDao.getAllPost()
        request.session.setAttribute("postList", postList)
        response.redirect(request, "post.jsp")
    }
}
