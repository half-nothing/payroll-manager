import {Cookie} from "./cookie.js";

const usernameInput = document.getElementById("username");
const usernameSpan = document.getElementById("username-span");
usernameInput.addEventListener("focus", e => {
    if (e.target.value === e.target.defaultValue) {
        e.target.style.backgroundColor = "#ffffff";
        e.target.value = "";
    }
})

usernameInput.addEventListener("blur", e => {
    if (e.target.value === "") {
        e.target.style.backgroundColor = "#e57373";
        e.target.value = e.target.defaultValue;
        usernameSpan.innerText = e.target.defaultValue;
    } else {
        e.target.style.backgroundColor = "#ffffff";
        usernameSpan.innerText = "";
    }
})

const passwordInput = document.getElementById("password");
const passwordSpan = document.getElementById("password-span");

passwordInput.addEventListener("blur", e => {
    if (e.target.value === "") {
        e.target.style.backgroundColor = "#e57373";
        passwordSpan.innerText = "密码不能为空";
    } else {
        e.target.style.backgroundColor = "#ffffff";
        passwordSpan.innerText = "";
    }
})

const submitButton = document.getElementById("login");
submitButton.addEventListener("click", (e) => {
    if (Cookie.get("loginState") !== null) {
        return
    }
    if (usernameInput.value === usernameInput.defaultValue) {
        usernameInput.style.backgroundColor = "#e57373";
        usernameSpan.innerText = usernameInput.defaultValue;
        e.preventDefault();
    }
    if (passwordInput.value === "") {
        passwordInput.style.backgroundColor = "#e57373";
        passwordSpan.innerText = "密码不能为空";
        e.preventDefault();
    }
})

window.onunload = (e) => {
    e.preventDefault();
    const event = new CustomEvent("LoginStateChange", {})
    window.parent.dispatchEvent(event);
}
