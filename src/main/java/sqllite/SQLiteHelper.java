package sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    
    public static List<Map<String, Object>> executeGetQuery(String sql) {
    	List<Map<String, Object>> results = new LinkedList<>();  // Lista per raccogliere i risultati
        
        try {
            connect();  // Assicura che la connessione sia attiva
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);  // Esegui la query e ottieni il ResultSet
            
            // Ottieni i nomi delle colonne per mappare i valori nelle righe
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Itera sulle righe del ResultSet
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();  // Crea una mappa per la riga
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);  // Ottieni il nome della colonna
                    Object columnValue = rs.getObject(i);  // Ottieni il valore della colonna
                    row.put(columnName, columnValue);  // Aggiungi il nome della colonna e il valore alla mappa
                }
                results.add(row);  // Aggiungi la mappa della riga alla lista
            }
            
            rs.close();
            stmt.close();
            System.out.println("Query eseguita con successo.");
        } catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query: " + e.getMessage());
        }
        
        return results;  // Ritorna la lista delle righe come mappe
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
