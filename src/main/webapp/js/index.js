import {Cookie} from "./cookie.js";

window.addEventListener("LoginStateChange", (event) => {
    location.reload()
})

window.onload = (e) => {
    const iframe = document.getElementById("iframe");
    const iframeDocument = iframe.contentDocument;
    const form = iframeDocument.getElementById("form");
    if (Cookie.get("loginState") !== null) {
        form.submit();
    }
}
