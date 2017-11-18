package py.org.elvioc.remansocastillo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Elvio Contreras Martinez
 */
public class ConnectionUtils {
    
    public static final Logger LOGGER = Logger.getLogger(ConnectionUtils.class.getName());
    public static Connection conn = null;
    
    public static Connection getConnectionFromDatasource(){
        String datasource = /*"java:/comp/env/" + */ConfigManager.getConfig("dataSource");
        String DATASOURCE_CONTEXT = datasource;
        try {
            if (conn != null && !conn.isClosed()) {
                LOGGER.log(Level.INFO, "Conexión Pool cerrada.");
                return conn;
            }
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            LOGGER.log(Level.INFO, "Conectando a datasource: {0}.",ds);
            if (ds != null) {
                conn = ds.getConnection();
            }else{
                LOGGER.log(Level.SEVERE, "Método getConnectionFromDatasource(): Error en datasource Context {0}.",DATASOURCE_CONTEXT);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Método getConnectionFromDatasource(): Conexión Fallida.");
            return null;
        } catch (NamingException ex) {
            LOGGER.log(Level.SEVERE, "Método getConnectionFromDatasource(): NamingException > {0}",ex);
            return null;
        }
        LOGGER.log(Level.INFO, "Conexión establecida a datasource: {0}.",datasource);
        return conn;
    }
    
    public static Connection getConnectionSingle(){
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(ConfigManager.getConfig("driverJDBC"));
                conn = DriverManager.getConnection(ConfigManager.getConfig("databaseURL"),
                        ConfigManager.getConfig("username"),
                        ConfigManager.getConfig("password"));
            }
            LOGGER.log(Level.INFO, "Conexión establecida.");
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Método getConnectionSingle(): Conexión Fallida.");
            return null;
        }
    }
    
    public static Connection getConnection(){
        try {
            boolean usaDatasource = Boolean.parseBoolean(ConfigManager.getConfig("usaDatasource"));
            LOGGER.log(Level.INFO, "Conexión a Datasource: {0}",usaDatasource);
            if (usaDatasource) {
                return getConnectionFromDatasource();
            } else {
                return getConnectionSingle();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Conexión fallida.", e);
            return null;
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
                LOGGER.log(Level.INFO,"INFO | Conexión cerrada.");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al cerrar la conexión", ex);
        }
    }
    
    public static void main(String[] args) {
        ConnectionUtils.getConnection();
    }
}
