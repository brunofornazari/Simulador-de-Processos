package workStation;

import java.util.ArrayList;

public class Processo {
    private int pId;
    private int duracao;
    private int chegada;
    private ArrayList<Io> io;
    
    public Processo(int _duracao, int _chegada, ArrayList<Io> _io, int _pId){
        pId = _pId;
        duracao = _duracao;
        chegada = _chegada;
        io = _io;
    }
    
    public Processo(int _duracao, int _chegada, int _pId){
        pId = _pId;
        duracao = _duracao;
        chegada = _chegada;
        io = new ArrayList<>();
    }
    
    public boolean addIO(Io quando){
        if(quando.getMomento() <= duracao && quando.getMomento() >= chegada){
            io.add(quando);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addIO(int quando){
        if(quando <= duracao && quando >= chegada){
            Io _io = new Io(quando);
            io.add(_io);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @return the pId
     */
    public int getpId() {
        return pId;
    }

    /**
     * @param pId the pId to set
     */
    public void setpId(int pId) {
        this.pId = pId;
    }

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * @return the chegada
     */
    public int getChegada() {
        return chegada;
    }

    /**
     * @param chegada the chegada to set
     */
    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    /**
     * @return the io
     */
    public ArrayList<Io> getIo() {
        return io;
    }

    /**
     * @param io the io to set
     */
    public void setIo(ArrayList<Io> io) {
        this.io = io;
    }
    
    public void setIo(){
        this.io = new ArrayList<Io>();
    }
    
}
