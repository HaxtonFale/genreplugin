package plugin;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Plugin {
    public static void main(String[] args) {
        String configPath = Paths.get(System.getProperty("user.home"), ".config").toString();
        File configDir = new File(configPath);
        if (!configDir.exists()) {
            System.out.println("Creating .config directory in user's home directory...");
            configDir.mkdir();
            System.out.println(".config successfully created.");
        }

        String dbPath = Paths.get(configPath, "genreplugin.db").toString();
        System.out.printf("Looking for sqlite database at %s...\n", dbPath);
        String url = "jdbc:sqlite:" + dbPath;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
