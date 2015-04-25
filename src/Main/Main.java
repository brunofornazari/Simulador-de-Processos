package Main;

import java.util.ArrayList;
import workStation.*;
import javax.swing.JOptionPane;
public class Main {
    static ArrayList<Processo> fila = new ArrayList<Processo>();
    //ArrayList<int> gant = new ArrayList<int>();
    public static void main(String[] args) {
        
    }

    public static void novoProcesso(Processo p){
        fila.add(p);
    }
}
