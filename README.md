[前言]: https://zhuanlan.zhihu.com/p/348214235
[SpringSecurity技术介绍]: https://zhuanlan.zhihu.com/p/348243780

### 项目结构介绍

### 后台项目结构 vstore-project

> vstore-project （`后台服务端项目`）
> |-- vstore-boot
> |   |-- vstore-boot-autoconfigure （`springboot的通用配置，如权限，mybaits 等在这个抱配置`）
> |   |-- vstore-boot-starter（`springboot的通用配置，如权限，mybaits 等在这个抱配置`）
> |-- vstore-dependencies (`框架外部依赖`)
> |-- vstore-framework
> |   |-- vstore-framework-base (`框架通用代码`)
>
> |-- vstore-parent (`本项目的依赖包`)
>
> |-- vstore-web  (`WEB项目`)

> |-- main
> |   |-- java
> |   |   -- com
> |   |       -- vstore
> |   |           |-- Application.java `启动文件`
> |   |           |-- controller `控制层`
> |   |           |-- entity `实体层`
> |   |           |-- mapper `数据访问层`
> |   |           |-- service `服务层`
> |   |           -- vo `vo层`
> |   -- resources
> |       |-- application-jdbc.yml `jdbc配置`
> |       |-- application.yml `基础配置`
> |       |-- com
> |       |   -- vstore
> |       |       -- mapper	`mybaits xml 文件`
> |       |-- log4j2.xml

### 前台项目结构 vstore-ui

|-- src
|-- static	`静态资源`

|-- components `通用组件`
|   |-- From
|   |-- Head
|   |-- Left
|   |-- Table
|   |-- Toolbar
|   -- comm.jsx
|-- layouts `基础布局文件`
|   -- BasicLayout.jsx
|   -- BasicLayout.less
| -- pages `项目页面`
|     -- actions
|     -- index
|     -- login

|-- webpack.config.js `webpack 打包配置`