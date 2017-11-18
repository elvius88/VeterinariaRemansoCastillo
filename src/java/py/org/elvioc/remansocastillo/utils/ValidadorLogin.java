//package py.org.elvioc.remansocastillo.utils;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.commons.codec.digest.DigestUtils;
//import py.una.pol.cart.dao.UsuarioManager;
//import py.org.elvioc.remansocastillo.entities.Usuario;
//
///**
// *
// * @author Elvio Contreras Martinez
// */
//public class ValidadorLogin {
//    static UsuarioManager usuarioManager = new UsuarioManager();
//    
//    public static boolean Autenticacion(String user, String password) throws Exception{
//        try {
//            Usuario usuario = usuarioManager.findByUsername(user);
//            if(usuario != null){
//                return usuario.getUsername().equals(user) && usuario.getPassword().equals(encriptarPassword(password));
//            }
//            return false;
//        } catch (Exception ex) {
//            Logger.getLogger(ValidadorLogin.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    
//    private static String encriptarPassword(String password){
//        if (!password.equals("")){
//            return DigestUtils.md5Hex(password);
//        }
//        return password;
//    }
//}
