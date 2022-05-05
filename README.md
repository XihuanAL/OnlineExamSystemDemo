# OnlineExamSystemDemo
在线考试系统
## 运行环境
- nodejs 16
- jdk 1.8
- mysql 8.0
## 技术栈
### 后端
- SpringBoot 2.6.6
- SpringSecurity 认证与授权
- mybatis-plus 持久层框架
- mysql
### 前端
- vue
- element ui
- axios

## 项目结构

- source 源码
  - OnlineExamSystem 后端代码
  - vue 前端代码
- sql 数据库脚本

## 运行流程

1. 打开数据库管理软件，新建数据库并运行sql目录下的sql脚本文件
2. 用idea打开OnlineExamSystem目录，等待idea自动加载maven依赖，修改resource中的application.yml文件与本地数据库对应

![evtzC7ENb8](https://raw.githubusercontent.com/XihuanAL/OnlineExamSystemDemo/main/static/evtzC7ENb8.png)

3. 运行OnlineExamSystemApplication启动后端代码，后端端口80
4. 用webstorm或vscode打开vue目录，在Terminal命令行中依次输入npm install命令和npm audit fix --force命令
5. 完毕后执行npm run serve启动前端代码，前端端口8080
6. 访问http://localhost:8080 ，管理员密码账号为admin，学生密码账号为student
