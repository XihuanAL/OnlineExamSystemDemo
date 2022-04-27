import axios from "axios";
import router from "./router";
import Element from "@/plugins/element";

axios.defaults.baseURL = "http://localhost" // 基础路径
const request = axios.create({
    timeout: 5000,
    headers: {
        "Content-Type": "application/json;charset=UTF-8"
    }
})
request.interceptors.request.use(config => {
    console.log("请求拦截器", config)
    config.headers["Authorization"] = localStorage.getItem("token")
    return config
})


export default request