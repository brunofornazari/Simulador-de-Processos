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
    
    public void add(Registro r, float tme){
       registros.add(r);
       this.tme += tme;
        
    }
    
    public boolean isEmpty(){
        if(registros.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    public float gerarTME(){
        ArrayList<Registro> aux = new ArrayList<Registro>();
        if(registros.isEmpty()){
            return 0;
        } else {
            for(Registro r:registros){
                if(!aux.contains(r)){
                    aux.add(r);
                }
            }
            System.out.println("TME SIZE: " + aux.size());
            return tme/aux.size();
        }
    }
    
    public void imprimirGant(ArrayList<Processo> processos){
        String impressao = "";
        for(Processo p:processos){
            impressao += "P" + p.getpId() + " " + p.getGraphic() + "\n";
        }
        impressao += "\n\n\n#### TME: " + tme/processos.size() + " ####\n\n\n";
        for(Registro r:registros){
            int i = 0;
            Processo p = procurarProcesso(processos, r.getProcesso());
            while(i < r.getFim() - r.getInicio()){
                if(p != null){
                    impressao += p.getGraphic();
                }
                
                i++;
            }
        }
        JOptionPane.showMessageDialog(null, impressao);
    }
    
    public Processo procurarProcesso(ArrayList<Processo> lista, int chave){
        for(Processo p:lista){
            if(p.getpId() == chave){
                return p;
            }
        }
        return null;
    }
    
    public Registro getLast(int pId){
        for(int i = registros.size()-1;i >= 0; i--){
            Registro r = registros.get(i);
            if(r.getProcesso() == pId){
                return r;
            }
        }
        return null;
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
