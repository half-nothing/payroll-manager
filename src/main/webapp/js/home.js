window.onload = () => {
    const timeSpan = document.getElementById("time");
    const updateTime = () => {
        const time = new Date();
        timeSpan.innerText = time.toLocaleString()
    }
    updateTime();
    setInterval(updateTime, 1000)
}
