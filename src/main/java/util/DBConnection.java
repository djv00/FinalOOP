package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Utility class for establishing database connection using MySQL.
 * Loads credentials from db.properties file.
 * 
 * @author Kai Lu
 */
public class DBConnection {

    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
        props.load(input);

        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.username");
        String pass = props.getProperty("jdbc.password");

        Class.forName(props.getProperty("jdbc.driver"));
        return DriverManager.getConnection(url, user, pass);
    }
}
