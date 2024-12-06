package cn.half.nothing.servlet.payment

import cn.half.nothing.dao.impl.PaymentDaoImpl
import cn.half.nothing.extension.redirect
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/payments/list"])
class PaymentListServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.session.removeAttribute("paymentList")
        val paymentDao = PaymentDaoImpl.new()
        val paymentList = paymentDao.getAllPayment()
        request.session.setAttribute("paymentList", paymentList)
        response.redirect(request, "payment.jsp")
    }
}
