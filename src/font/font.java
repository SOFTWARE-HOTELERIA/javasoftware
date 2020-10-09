/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package font;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author USER
 */
public class font {
    private Font font = null;
    public String MONTSERRAT = "Montserrat-Bold.ttf";

    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2*/
    public Font fuente( String fontName, int estilo, float tamanio)
    {
         try {
            InputStream is =  getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 18);            
        }
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }
}

