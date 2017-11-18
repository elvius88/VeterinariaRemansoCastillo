package py.una.pol.cart.utils;

import java.text.DecimalFormat;

/**
 *
 * @author Elvio Contreras Martinez
 */
public class NumberFormatUtils {
    public static String getSeparadoresMiles(Integer numero){
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(numero);
    }
}
