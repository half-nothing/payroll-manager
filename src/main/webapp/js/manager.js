let data = undefined;

let username;
let name;
let post;
let level;
let payment;
let basePayment

window.onload = () => {
    username = document.getElementById("username");
    name = document.getElementById("name");
    post = document.getElementById("post");
    level = document.getElementById("level");
    basePayment = document.getElementById("base-payment");
    payment = document.getElementById("payment");
}

function clearPopup() {
    data = {}
    username.value = "";
    name.value = "";
    post.value = "";
    level.value = "";
    payment.value = "";
    basePayment.innerText = "";
}

function showPopup() {
    document.getElementById("overlay").style.display = "block";
}

function hidePopup() {
    document.getElementById("overlay").style.display = "none";
    window.location = "/users/list";
}

async function submitAndHidePopup() {
    data.username = username.value;
    data.nickName = name.value;
    data.postName = post.value;
    data.postLevel = level.value;
    data.realPayment = parseInt(payment.value);
    if (data.id) {
        await axios.put(`/users/info/${data.id}`, data);
    } else {
        await axios.post(`/users/info`, data);
    }
    hidePopup();
}

/**
 * 获取用户信息并启动编辑框
 * @param userId {number} 用户id
 */
async function editUser(userId) {
    const response = await axios.get(`/users/info/${userId}`);
    data = response.data.data;
    username.value = data.username;
    name.value = data.nickName;
    post.value = data.postName;
    level.value = data.postLevel;
    basePayment.innerText = data.postPayment;
    payment.value = data.realPayment;
    showPopup();
}

/**
 * 删除某用户
 * @param userId {number} 用户id
 */
async function deleteUser(userId) {
    await axios.delete(`/users/${userId}`);
    window.location = "/users/list";
}

/**
 * 添加某用户
 */
async function addUser() {
    clearPopup();
    showPopup();
}
