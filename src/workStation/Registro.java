/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workStation;


/**
 *
 * @author Bruno
 */
public class Registro {
    private int inicio;
    private int fim;
    private int processo;
    private boolean io;
    
    public Registro(int _inicio, int _processo, int _fim, boolean _io){
        inicio = _inicio;
        processo = _processo;
        fim = _fim;
        io = _io;
    }
    
    
    
    /**
     * @return the inicio
     */
    public int getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fim
     */
    public int getFim() {
        return fim;
    }

    /**
     * @param fim the fim to set
     */
    public void setFim(int fim) {
        this.fim = fim;
    }

    /**
     * @return the processo
     */
    public int getProcesso() {
        return processo;
    }

    /**
     * @param processo the processo to set
     */
    public void setProcesso(int processo) {
        this.processo = processo;
    }

    /**
     * @return the io
     */
    public boolean isIo() {
        return io;
    }

    /**
     * @param io the io to set
     */
    public void setIo(boolean io) {
        this.io = io;
    }   
}
