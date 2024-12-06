let data;
let level;
let payment;

window.onload = () => {
    level = document.getElementById("level");
    payment = document.getElementById("payment");
}

function clearPopup() {
    data = {};
    level.value = "";
    payment.value = "";
}

function showPopup() {
    document.getElementById("overlay").style.display = "block";
}

function hidePopup() {
    document.getElementById("overlay").style.display = "none";
}

async function submitAndHidePopup() {
    hidePopup();
    data.name = level.value;
    data.payment = parseFloat(payment.value);
    if (data.id) {
        await axios.put(`/payments/info/${data.id}`, data);
    } else {
        await axios.post(`/payments/info`, data);
    }
    window.location = "/payments/list";
}


async function addPayment() {
    clearPopup();
    showPopup();
}

/**
 * 编辑基础薪资
 * @param paymentId {number} 基础薪资id
 */
async function editPayment(paymentId) {
    clearPopup();
    const response = await axios.get(`/payments/info/${paymentId}`);
    data = response.data.data;
    level.value = data.name;
    payment.value = data.payment;
    showPopup();
}

/**
 * 删除基础薪资
 * @param paymentId {number} 基础薪资id
 */
async function deletePayment(paymentId) {
    await axios.delete(`/payments/${paymentId}`);
    window.location = "/payments/list";
}
