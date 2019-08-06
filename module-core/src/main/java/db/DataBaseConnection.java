package db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class DataBaseConnection {
    private static BasicDataSource source = new BasicDataSource();
    private static DataBaseConnection dataBaseConnection;
    private final static Logger LOGGER = LogManager.getLogger(DataBaseConnection.class);

    public static DataBaseConnection getInstance() {
        if (dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }

    private DataBaseConnection() {
        Properties p = new Properties();
        try {
            p.load(DataBaseConnection.class.getClassLoader().getResourceAsStream("config-core.properties"));
            source.setDriverClassName(p.getProperty("db_driver"));
            source.setUrl(p.getProperty("db_url"));
            source.setUsername(p.getProperty("db_user"));
            source.setPassword(p.getProperty("db_password"));

            source.setInitialSize(20);
            source.setMaxActive(50);
        } catch (IOException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    public BasicDataSource getSource() {
        return source;
    }
}

