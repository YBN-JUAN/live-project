# live-project
简单的口罩预约购买应用


## 运行

- 所需环境：

    - java 1.8.x
    - mysql 8.0

- 从本仓库下载最新的源代码

    项目结构如下：

    ```
    |-- live-project
        |-- database 
        |   |-- maskOrder.sql	// 数据库脚本
        |   |-- mysql-connector-java-8.0.19.jar	// 所需依赖
        |-- src 								
        |   |-- dao
        |   |   |-- OrderDAO.java
        |   |   |-- OrderDAOInterface.java
        |   |   |-- RecordDAO.java
        |   |   |-- RecordDAOInterface.java
        |   |-- frame
        |   |   |-- InformationFrame.java
        |   |   |-- MainFrame.java	// 运行入口
        |   |-- pojo
        |   |   |-- Order.java
        |   |   |-- Record.java
        |   |-- service
        |   |   |-- FindService.java
        |   |   |-- LotteryService.java
        |   |   |-- RegisterService.java
        |   |   |-- impl
        |   |       |-- FindServiceImpl.java
        |   |       |-- LotteryServiceImpl.java
        |   |       |-- RegisterServiceImpl.java
        |   |-- util
        |       |-- DBUtil.java	 // 数据库工具类
    ```

- 在IDE中导入该项目

- 将`database`文件夹下的 `mysql-connector-java-8.0.19.jar` 作为依赖jar包导入工程

- 在mysql执行`database`文件夹下的 `maskOrder.sql`脚本，建立数据库

- 修改`src\util\DBUtil.java`，更改数据库配置为本地的配置：

    ```
    ...
    public class DBUtil {
    
        static String ip = "127.0.0.1";
        static int port = 3306;
        static String database = "maskorder";
        static String encoding = "UTF-8";
        static String loginName = 数据库用户名;
        static String password = 数据库密码;
        
        ....
    }
    ```

- 运行`src\frame\MainFrame.java`

