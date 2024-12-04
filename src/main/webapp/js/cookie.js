export class Cookie {
    /**
     * 设置cookie值
     * @param key {string} cookie键
     * @param value {string} cookie值
     * @param path {string, undefined} cookie路径
     * @param expires {number, undefined} cookie过期时间, 单位是秒
     */
    static set(key, value, path = undefined, expires = undefined) {
        path = path || "/";
        let cookie = `${key}=${value};path=${path}`;
        if (expires) {
            const date = new Date();
            date.setTime(date.getTime() + expires * 1000);
            cookie += `;expires=${date.toUTCString()}`;
        }
        document.cookie = cookie;
    }

    /**
     * 删除cookie值
     * @param key {string} cookie键
     */
    static remove(key) {
        document.cookie = `${key}=; expires=Thu, 01 Jan 1970 00:00:00 GMT`;
    }

    /**
     * 获取cookie值,不存在返回null
     * @param key {string} cookie键
     * @returns {string|null} cookie值,不存在返回null
     */
    static get(key) {
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            const cookie = cookies[i].trim().split('=');
            if (cookie[0] === key) {
                return cookie[1];
            }
        }
        return null;
    }
}
