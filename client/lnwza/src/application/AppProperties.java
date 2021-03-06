package application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author SE-lnwza
 */
public class AppProperties {

    private static String dbIP;
    private static String dbPort;
    private static String dbName;
    private static String dbUser;
    private static String dbPass;
    private static String sqlIP;
    private static String sqlPort;
    private static String sqlName;
    private static String sqlUser;
    private static String sqlPass;
    private static Integer shopId;

    public static void load() {
        // load from config.properties
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = AppProperties.class.getResourceAsStream("/application/resources/config.properties");
            prop.load(input);

            dbIP = prop.getProperty("dbIP");
            dbPort = prop.getProperty("dbPort");
            dbName = prop.getProperty("dbName");
            dbUser = prop.getProperty("dbUser");
            dbPass = prop.getProperty("dbPass");
            sqlIP = prop.getProperty("sqlIP");
            sqlPort = prop.getProperty("sqlPort");
            sqlName = prop.getProperty("sqlName");
            sqlUser = prop.getProperty("sqlUser");
            sqlPass = prop.getProperty("sqlPass");
            shopId = Integer.valueOf(prop.getProperty("shopId"));

        } catch (IOException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                } finally {
                    System.out.println("Application's properties loaded!");
                }
            }
        }
    }

    public static String getDBIP() {
        return dbIP;
    }

    public static String getDBPort() {
        return dbPort;
    }

    public static String getDBName() {
        return dbName;
    }

    public static String getDBUser() {
        return dbUser;
    }

    public static String getDBPass() {
        return dbPass;
    }

    public static String getSqlIP() {
        return sqlIP;
    }

    public static String getSqlPort() {
        return sqlPort;
    }

    public static String getSqlName() {
        return sqlName;
    }

    public static String getSqlUser() {
        return sqlUser;
    }

    public static String getSqlPass() {
        return sqlPass;
    }

    public static Integer getShopId() {
        return shopId;
    }

}
