/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workStation;

import java.util.ArrayList;
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
    
    public void imprimirGant(ArrayList<Processo> processos){
        String impressao = "";
        for(Processo p:processos){
            impressao += "P" + p.getpId() + " " + p.getGraphic() + "\n";
        }
        impressao += "\n\n\n";
        for(Registro r:registros){
            int i = 0;
            Processo p = processos.get(r.getProcesso());
            while(i < r.getFim() - r.getInicio()){
                impressao += p.getGraphic();
                i++;
            }
        }
        JOptionPane.showMessageDialog(null, impressao);
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
