package workStation;

import java.util.ArrayList;
import java.util.Iterator;

public class Processo implements Cloneable {
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
    public void alterarProcesso(int _d, int _c) {
        duracao = _d;
        chegada = _c;
    }
    
    @Override
    public Processo clone(){
        try{
            Processo p = (Processo) super.clone();
            return (Processo) p;
        } catch(CloneNotSupportedException e){
            System.out.println("Erro ao clonar objeto");
            return this;
        }
    }
    
    public ArrayList<Io> cloneIO(){
        ArrayList<Io> _io = new ArrayList<Io>();
        Iterator<Io> i = io.iterator();
        while(i.hasNext()){
            _io.add(i.next().clone());
        }
            return _io;
    }
    
    public void processado(int quantum){
        duracao -= quantum;
        for(Io i: io){
            i.setMomento(i.getMomento()-quantum);
        }
    }
    
    public Io buscaIO(int quantum){
        int momento = 0;
        for(Io i: io){
            if(i.getMomento() >= momento && i.getMomento() < quantum){
                return i;
            }
        }
        return null;
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
