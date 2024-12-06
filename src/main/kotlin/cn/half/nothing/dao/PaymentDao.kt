package cn.half.nothing.dao

import cn.half.nothing.entity.PaymentEntity

interface PaymentDao {
    fun getById(id: Int): PaymentEntity?
    fun insertPayment(payment: PaymentEntity): Int
    fun updatePayment(payment: PaymentEntity): Int
    fun deletePaymentById(id: Int): Int
    fun getAllPayment(): List<PaymentEntity>
}
