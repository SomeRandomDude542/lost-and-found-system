package laf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnection {
    private static final String DB_PATH = "db/lostandfound.db";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + DB_PATH;
            System.out.println("USING DB FILE: " + url);
            conn = DriverManager.getConnection(url);
            
            
            
            // Create users table if missing
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT NOT NULL, "
                    + "email TEXT NOT NULL, "
                    + "password TEXT NOT NULL, "
                    + "role TEXT DEFAULT 'user'"
                    + ");";
            
         // Update the lost_items table creation:
            String createLostItemsTable = "CREATE TABLE IF NOT EXISTS lost_items ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "item_name TEXT NOT NULL, "
                    + "location_lost TEXT NOT NULL, "
                    + "date_lost TEXT NOT NULL, "
                    + "description TEXT, "
                    + "image_path TEXT, "
                    + "reported_by TEXT NOT NULL, "
                    + "status TEXT DEFAULT 'Pending', "  // Add this line
                    + "date_reported DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ");";

            // Do the same for found_items:
            String createFoundItemsTable = "CREATE TABLE IF NOT EXISTS found_items ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "item_name TEXT NOT NULL, "
                    + "location_found TEXT NOT NULL, "
                    + "date_found TEXT NOT NULL, "
                    + "description TEXT, "
                    + "image_path TEXT, "
                    + "reported_by TEXT NOT NULL, "
                    + "status TEXT DEFAULT 'Pending', "  // Add this line
                    + "date_reported DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ");";
            
            
            Statement stmt = conn.createStatement();
            stmt.execute(createUsersTable);
            stmt.execute(createLostItemsTable);
            stmt.execute(createFoundItemsTable);
                         
            updateSchemaIfNeeded(conn);

          
            System.out.println("Database ready.");
        } catch (SQLException e) {
            System.out.println("SQLite ERROR: " + e.getMessage());
        }
        return conn;
    }
    
    public static void main(String[] args) {
        connect();
    }
    
    private static void updateSchemaIfNeeded(Connection conn) {
        try {
            Statement stmt = conn.createStatement();

            // Try to add status column to lost_items
            try {
                stmt.execute("ALTER TABLE lost_items ADD COLUMN status TEXT DEFAULT 'Pending'");
                System.out.println("Added status column to lost_items");
            } catch (SQLException e) {
                if (!e.getMessage().contains("duplicate column")) {
                    System.out.println("Note: " + e.getMessage());
                }
            }

            // Try to add status column to found_items
            try {
                stmt.execute("ALTER TABLE found_items ADD COLUMN status TEXT DEFAULT 'Pending'");
                System.out.println("Added status column to found_items");
            } catch (SQLException e) {
                if (!e.getMessage().contains("duplicate column")) {
                    System.out.println("Note: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println("Schema update error: " + e.getMessage());
        }
    }
}