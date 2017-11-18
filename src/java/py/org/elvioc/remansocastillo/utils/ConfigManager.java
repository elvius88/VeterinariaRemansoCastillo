package py.org.elvioc.remansocastillo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvio Contreras Martinez
 */
public class ConfigManager {
    
    public static final Logger LOGGER = Logger.getLogger(ConfigManager.class.getName());
    public static Properties properties;
    public static boolean configurado = false;
    public static String configPath = "";
    
    
    public static void init() {
        FileInputStream fis = null;
        try {
            if (configPath.equals("")) {
                URL resource = ConfigManager.class.getResource("/");
                configPath = resource.getPath().replace("build/web/WEB-INF/classes/", "web/WEB-INF/conf/")+"config.properties";
            }
            fis = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(fis);
            fis.close();
            configurado = true;
            LOGGER.log(Level.INFO, "ConfigPath: {0}", configPath);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Metodo: init() - ConfigPath: {0} . El archivo no fué encontrado.", configPath);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Metodo: init() - ConfigPath: {0} . Un ocurrió un error al intentar inicializar el ConfigManager.", configPath);
        }finally{
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Metodo: init() - ConfigPath: {0} . El archivo no fué encontrado.", configPath);
            }
        }
    }
    
    public static String getConfig(String config) {
        if (!configurado) {
            init();
        }
        return properties.getProperty(config);
    }
}
