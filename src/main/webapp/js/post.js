let data;
let name;
let level;
let payment;

window.onload = () => {
    name = document.getElementById("name");
    level = document.getElementById("level");
    payment = document.getElementById("payment");
}

function clearPopup() {
    data = {};
    name.value = "";
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
    if (data.id) {
        data.postName = name.value;
        data.postLevel.name = level.value;
        await axios.put(`/posts/info/${data.id}`, data);
    } else {
        data.postName = name.value;
        data.postLevel = {name: level.value};
        await axios.post(`/posts/info`, data);
    }
    window.location = "/posts/list";
}


async function addPost() {
    clearPopup();
    showPopup();
}

/**
 * 编辑基础薪资
 * @param paymentId {number} 基础薪资id
 */
async function editPost(paymentId) {
    clearPopup();
    const response = await axios.get(`/posts/info/${paymentId}`);
    data = response.data.data;
    name.value = data.postName;
    level.value = data.postLevel.name;
    payment.innerText = data.postLevel.payment;
    showPopup();
}

/**
 * 删除基础薪资
 * @param paymentId {number} 基础薪资id
 */
async function deletePost(paymentId) {
    await axios.delete(`/posts/${paymentId}`);
    window.location = "/posts/list";
}
