package cn.half.nothing.servlet.payment

import cn.half.nothing.model.Reply
import cn.half.nothing.dao.impl.PaymentDaoImpl
import cn.half.nothing.entity.PaymentEntity
import cn.half.nothing.extension.code
import cn.half.nothing.extension.getPathParameter
import cn.half.nothing.extension.json
import cn.half.nothing.extension.toJson
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/payments/info/*", "/payments/*"])
class PaymentInfoServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val paymentId = request.getPathParameter()
        if (paymentId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Payment ID not found"))
            return
        }
        val paymentDao = PaymentDaoImpl.new()
        val paymentEntity = paymentDao.getById(paymentId.toInt())
        if (paymentEntity == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Payment not found"))
            return
        }
        response.code(HttpServletResponse.SC_OK).json(Reply.success(paymentEntity))
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val paymentEntity = request.toJson<PaymentEntity>()
        val paymentDao = PaymentDaoImpl.new()
        if (paymentDao.insertPayment(paymentEntity) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Add failed"))
        }
    }

    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) {
        val paymentId = request.getPathParameter()
        if (paymentId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Payment ID not found"))
            return
        }
        val paymentEntity = request.toJson<PaymentEntity>()
        if (paymentId.toInt() != paymentEntity.id) {
            response.code(HttpServletResponse.SC_BAD_REQUEST).json(Reply.fail("Payment ID not match"))
            return
        }
        val paymentDao = PaymentDaoImpl.new()
        if (paymentDao.updatePayment(paymentEntity) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Update failed"))
        }
    }

    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {
        val paymentId = request.getPathParameter()
        if (paymentId == null) {
            response.code(HttpServletResponse.SC_NOT_FOUND).json(Reply.fail("Payment ID not found"))
            return
        }
        val paymentDao = PaymentDaoImpl.new()
        if (paymentDao.deletePaymentById(paymentId.toInt()) == 1) {
            response.code(HttpServletResponse.SC_OK).json(Reply.success(""))
        } else {
            response.code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).json(Reply.fail("Delete failed"))
        }
    }
}
