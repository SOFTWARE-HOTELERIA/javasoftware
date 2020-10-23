/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa1_presentacion.util;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 *
 * @author josel
 */
public class Mensaje {
     private static final String errorDeConsulta = "No se pudo realizar la consulta, intente de nuevo o consulte con el Administrador.";
    private static final String filaNoSeleccionada = "Seleccione una fila.";
    private static final String filaNoExiste = "La fila seleccionada ya no existe.";
    private static final String preguntaDeEliminacion = "¿Está seguro de eliminar?";
    private static final String afirmacionDeEliminacion = "El registro fue eliminado.";
    private static final String advertenciaDeEliminacion = "El registro no puede ser eliminado, verifique.";
    private static final String errorDeEliminacion = "No se pudo eliminar, intente de nuevo o consulte con el Administrador.";
    private static final String afirmacionDeCreacion = "Los datos fueron guardados.";
    private static final String advertenciaDeCreacion = "Los datos no fueron guardados, verifique.";
    private static final String errorDeCreacion = "No se pudieron guardar los datos.\n"
            + "Verifique los datos obligatorios y únicos.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String afirmacionDeActualizacion = "Los datos fueron actualizados.";
    private static final String advertenciaDeActualizacion = "Los datos no fueron actualizados, verifique.";
    private static final String errorDeActualizacion = "No se pudieron actualizar los datos.\n"
            + "Verifique los datos obligatorios y únicos.\n"
            + "intente de nuevo o consulte con el Administrador.";
    
    /**
     * No se pudo realizar la consulta, intente de nuevo o consulte con el Administrador.
     * @param dialogo 
     */
    public static void mostrarErrorDeConsulta(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,errorDeConsulta,"Hoteleria Pepito: Error",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Seleccione una fila.
     * @param dialogo 
     */
    public static void mostrarFilaNoSeleccionada(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,filaNoSeleccionada,"Hoteleria Pepito: Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * La fila seleccionada ya no existe.
     * @param dialogo 
     */
    public static void mostrarFilaNoExiste(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,filaNoExiste,"Hoteleria Pepito: Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * ¿Está seguro de eliminar?
     * @param dialogo
     * @return 
     */
    public static boolean mostrarPreguntaDeEliminacion(JDialog dialogo){
        int respuesta = JOptionPane.showConfirmDialog(dialogo,preguntaDeEliminacion,"Hoteleria Pepito: Pregunta",JOptionPane.YES_NO_OPTION);
        return ( respuesta == JOptionPane.YES_OPTION );
    }
    
    /**
     * El registro fue eliminado.
     * @param dialogo 
     */
    public static void mostrarAfirmacionDeEliminacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,afirmacionDeEliminacion,"Hoteleria Pepito: Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * El registro no puede ser eliminado, verifique.
     * @param dialogo 
     */
    public static void mostrarAdvertenciaDeEliminacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,advertenciaDeEliminacion,"Hoteleria Pepito: Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * No se pudo eliminar, intente de nuevo o consulte con el Administrador.
     * @param dialogo 
     */
    public static void mostrarErrorDeEliminacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,errorDeEliminacion,"Hoteleria Pepito: Error",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Los datos fueron guardados.
     * @param dialogo 
     */
    public static void mostrarAfirmacionDeCreacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,afirmacionDeCreacion,"Hoteleria Pepito: Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Los datos no fueron guardados, verifique.
     * @param dialogo 
     */
    public static void mostrarAdvertenciaDeCreacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,advertenciaDeCreacion,"Hoteleria Pepito: Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * No se pudieron guardar los datos, intente de nuevo o consulte con el Administrador.
     * @param dialogo 
     */
    public static void mostrarErrorDeCreacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,errorDeCreacion,"Hoteleria Pepito: Error",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Los datos fueron actualizados.
     * @param dialogo 
     */
    public static void mostrarAfirmacionDeActualizacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,afirmacionDeActualizacion,"Hoteleria Pepito: Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Los datos no fueron actualizados, verifique.
     * @param dialogo 
     */
    public static void mostrarAdvertenciaDeActualizacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,advertenciaDeActualizacion,"Hoteleria Pepito: Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * No se pudieron actualizar los datos, intente de nuevo o consulte con el Administrador.
     * @param dialogo 
     */
    public static void mostrarErrorDeActualizacion(JDialog dialogo){
        JOptionPane.showMessageDialog(dialogo,errorDeActualizacion,"Hoteleria Pepito: Error",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de afirmación
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     */
    public static void mostrarAfirmacion(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Hoteleria Pepito: Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de advertencia
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     */
    public static void mostrarAdvertencia(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Hoteleria Pepito: Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de error
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     */
    public static void mostrarError(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Hoteleria Pepito: Error",JOptionPane.ERROR_MESSAGE);
    }
      /**
     * Muestra un mensaje para ingresar doucumento de identidad
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     * @return 
     */
      public static String ingresarDatos(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
}
