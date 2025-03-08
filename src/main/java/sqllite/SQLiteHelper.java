package sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHelper {
    private static Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    // Metodo per connettersi al database
    public static void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Connessione a SQLite stabilita.");
            }
        } catch (SQLException e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }

    // Metodo per eseguire una query generica
    public static void executeQuery(String sql) {
        try {
            connect(); // Assicura che la connessione sia attiva
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            System.out.println("Query eseguita con successo.");
        } catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query: " + e.getMessage());
        }
    }

    // Metodo per chiudere la connessione
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connessione a SQLite chiusa.");
            }
        } catch (SQLException e) {
            System.out.println("Errore nella chiusura della connessione: " + e.getMessage());
        }
    }
}
