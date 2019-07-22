package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private static String DB_DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD ;


    public static Connection getConnection() throws SQLException {
        Properties p = new Properties();
        try {
            p.load(DataBaseConnection.class.getClassLoader().getResourceAsStream("config-core.properties"));
            DB_DRIVER = p.getProperty("db_driver");
            URL = p.getProperty("db_url");
            USER = p.getProperty("db_user");
            PASSWORD = p.getProperty("db_password");

            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

//    public static boolean isClosed(Connection connection){
//        try {
//            connection.close();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}

