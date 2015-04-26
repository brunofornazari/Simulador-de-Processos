package Main;

import java.util.ArrayList;
import workStation.*;
import javax.swing.JOptionPane;
public class Main {
    static ArrayList<Processo> fila = new ArrayList<Processo>();
    static int quantum;
    //ArrayList<int> gant = new ArrayList<int>();
    public static void main(String[] args) {
        int menu = 0;
        int contP = 0;
        Processo p;
        quantum = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do Quantum"));
        while(menu != 7){
            menu = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha a opção: \n"
                    + "1 - Inserir novo Processo\n"
                    + "2 - Mostrar processos\n"
                    + "3 - Alterar processo\n"
                    + "4 - Remover Processos\n"
                    + "5 - Alterar Quantum\n"
                    + "6 - Executar Simulação\n"
                    + "7 - Encerrar Programa"));
            switch(menu){
                case 1:
                       p = new Processo(Integer.parseInt(JOptionPane.showInputDialog("Insira a duração do processo")),
                               Integer.parseInt(JOptionPane.showInputDialog("Insira ponto de chegada ou inicio do processo")),
                               contP);
                       contP++;
                       int subMenu = 0;
                       while(subMenu != 6){
                           subMenu = Integer.parseInt(JOptionPane.showInputDialog("Processo: " + p.getpId() + "\nEntre com a opção:\n"
                                   + "1 - Inserir I/O\n"
                                   + "2 - Mostrar I/O's\n"
                                   + "3 - Remover I/O\n"
                                   + "4 - Alterar Processo\n"
                                   + "5 - Excluir Processo\n"
                                   + "6 - Voltar ao menu principal"));
                           switch(subMenu){
                               case 1:
                                   if(p.addIO(Integer.parseInt(JOptionPane.showInputDialog("Entre com o momento de IO")))){
                                       JOptionPane.showMessageDialog(null, "Momento de I/O inserido com sucesso!");
                                   } else {
                                       JOptionPane.showMessageDialog(null, "Não foi possível inserir o momento, verifique se o processo ainda existe e tente novamente mais tarde ou contate o suporte.");
                                   }
                                   break;
                               case 2:
                                   JOptionPane.showMessageDialog(null, retornarIOs(p));
                                   break;
                               case 3:
                                   int chave = Integer.parseInt(JOptionPane.showInputDialog("Entre com o número da chave do I/O que deseja eliminar"));
                                   Io o = p.getIo().remove(chave);
                                   if(o != null){
                                       JOptionPane.showMessageDialog(null, "Chave " + chave + " - (Momento" + o.getMomento() + ") eliminada com sucesso!");
                                   } else {
                                       JOptionPane.showMessageDialog(null, "Não foi possível remover o momento indicado, ou a chave é falsa ou o momento já foi eliminado!");
                                   }
                                   break;
                               case 4:
                                   //Alterar processo, criar método para evitar alongar demais o código
                                   break;
                               case 5:
                                   //Excluir processo, criar método para evitar alongar demais o código e repetições
                                   break;
                               case 6:
                                   fila.add(p);
                                   break;
                           }
                       }
                        break;
                case 2:
                    String processos = "";
                    for(Processo processo:fila){
                        processos += "Processo: P" + processo.getpId() + "\n"
                                + "Duração: [" + processo.getDuracao() + "] - Chegada: [" + processo.getChegada() + "]\n"
                                + "I/O's: " + retornarIOs(processo.getIo());
                    }
                    JOptionPane.showMessageDialog(null, processos);
                    break;
                case 3:
                    //Alterar processo, criar método para evitar alongar demais o código
                    break;
                case 4:
                    //Excluir processo, criar método para evitar alongar demais o código e repetições
                    break;
                case 5:
                    //Alterar quantum
                    break;
                case 6:
                    //Simulação
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }
    
    public static String retornarIOs(Processo p){
        String ios = "Processo P" + p.getpId() + "\n";
        for(Io o:p.getIo()){
           ios += "Momento " + p.getIo().indexOf(o) +": " + o.getMomento() + "\n";
        }
        return ios;
    }
    public static String retornarIOs(ArrayList<Io> io){
        String ios = "";
        for(Io o:io){
           ios += o.getMomento();
           if(io.indexOf(o) != (io.size()-1)){
               ios += ", ";
           }
        }
        return ios;
    }
    
    
    public static void novoProcesso(Processo p){
        fila.add(p);
    }
}
