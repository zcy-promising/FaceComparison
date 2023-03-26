package utils;

import config.path;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static Connection connection=null;
    private static Statement statement = null;

    /**
     * 获取连接数据库
     * @return
     */
        public static Statement getStatement(){


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(path.url,path.userName,path.password);
            statement=connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    /**
     * 关闭数据库连接
     */
    public static void close(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
