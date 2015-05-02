/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workStation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class Gerenciador_Registros {
    private float tme;
    private ArrayList<Registro> registros;
    
    public Gerenciador_Registros(){
        registros = new ArrayList<Registro>();
    }
    
    public void add(Registro r){
        registros.add(r);
    }
    
    public JFrame desenharGant(){
        JFrame frame = new JFrame(); 
        frame.setSize(600, 400);

        frame.setBackground(Color.yellow);
        frame.setVisible(true);
        return frame;
    }
    
    /**
     * @return the tme
     */
    public float getTme() {
        return tme;
    }

    /**
     * @param tme the tme to set
     */
    public void setTme(float tme) {
        this.tme = tme;
    }

    /**
     * @return the registros
     */
    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    /**
     * @param registros the registros to set
     */
    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }
    
    
}
