/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workStation;

/**
 *
 * @author Bruno
 */
public class Io implements Cloneable {
    private int momento;
    
    public Io(int _momento){
        setMomento(_momento);
    }
    
    @Override
    public Io clone(){
        try{
            return (Io) super.clone();
        } catch(CloneNotSupportedException e){
            System.out.println("Erro ao clonar objeto");
            return this;
        }
    }
    
    /**
     * @return the momento
     */
    public int getMomento() {
        return momento;
    }

    /**
     * @param momento the momento to set
     */
    public void setMomento(int momento) {
        this.momento = momento;
    }
    
    
}
