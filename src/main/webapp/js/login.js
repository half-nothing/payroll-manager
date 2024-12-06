import {Cookie} from "./cookie.js";

const usernameInput = document.getElementById("username");
const usernameSpan = document.getElementById("username-span");

const passwordInput = document.getElementById("password");
const passwordSpan = document.getElementById("password-span");

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

window.onload = (e) => {
    const form = document.getElementById("form");
    if (Cookie.get("loginState") !== null) {
        form.submit();
    }
}
