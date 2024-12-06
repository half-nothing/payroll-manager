package cn.half.nothing.dao.impl

import cn.half.nothing.entity.PaymentEntity
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PaymentDaoImplTest {

    @Test
    fun getAllPayment() {
        val paymentDao = PaymentDaoImpl.new()
        val paymentEntities = paymentDao.getAllPayment()
        paymentEntities.forEach {
            println(it)
        }
    }

    @Test
    fun getById() {
        val paymentDao = PaymentDaoImpl.new()
        val paymentEntity = paymentDao.getById(1)
        assertNotNull(paymentEntity)
        println(paymentEntity)
    }

    @Test
    fun insertPayment() {
        val paymentDao = PaymentDaoImpl.new()
        val paymentEntity = PaymentEntity(-1, "1", 5000.0)
        println(paymentEntity)
        assertEquals(paymentDao.insertPayment(paymentEntity), 1)
        println(paymentEntity)
    }

    @Test
    fun updatePayment() {
        val paymentDao = PaymentDaoImpl.new()
        val paymentEntity = paymentDao.getById(11)
        assertNotNull(paymentEntity)
        paymentEntity.payment = 6000.0
        println(paymentEntity)
        assertEquals(paymentDao.updatePayment(paymentEntity), 1)
    }

    @Test
    fun deletePaymentById() {
        val paymentDao = PaymentDaoImpl.new()
        assertEquals(paymentDao.deletePaymentById(11), 1)
    }
}
