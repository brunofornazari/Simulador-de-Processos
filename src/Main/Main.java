package Main;

import java.util.ArrayList;
import workStation.*;
import javax.swing.JOptionPane;
public class Main {
    static ArrayList<Processo> fila = new ArrayList<Processo>();
    static int quantum;
    static ArrayList<Processo> ready = new ArrayList<Processo>();
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
                                   if(p.getIo().isEmpty()){
                                       JOptionPane.showMessageDialog(null, "Não é possível remover, pois há nenhum processo de IO registrado no processo P" + p.getpId());
                                   } else {
                                       int chave = Integer.parseInt(JOptionPane.showInputDialog("Entre com o número da chave do I/O que deseja eliminar"));
                                       Io o;
                                       if(chave < p.getIo().size() && chave >= 0){
                                           o = p.getIo().remove(chave);
                                       } else {
                                           o = null;
                                       }
                                        if(o != null){
                                            JOptionPane.showMessageDialog(null, "Chave " + chave + " - (Momento" + o.getMomento() + ") eliminada com sucesso!");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Não foi possível remover o momento indicado, ou a chave é falsa ou o momento já foi eliminado!");
                                        }
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
                    ready = fila;
                    if(ready.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Não há processos para serem simulados!\nAdicione processos e tente novamente.");
                    } else {
                        gnomeSort(ready, 0, 0, true);
                        int ponteiro = 0, tempo = 0;
                        while(ready != null){
                            if(ponteiro > ready.size()-1){
                                ponteiro = 0;
                            }
                            p = ready.get(ponteiro);
                            if(p.getChegada() >= tempo && p.getChegada() <= (tempo+quantum)){
                                System.out.println("Iniciando em: " + (tempo+p.getChegada()));
                                int momento = getMomentoIos(p.getIo(), tempo, quantum);
                                if(momento >= 0){
                                    tempo = tempo + momento/8;
                                }
                            }
                        }
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }
    
    public static String retornarIOs(Processo p){
        String ios = "Processo P" + p.getpId() + "\n";
        if(!p.getIo().isEmpty()){
            for(Io o:p.getIo()){
                ios += "Momento " + p.getIo().indexOf(o) +": " + o.getMomento() + "\n";
             }
        } else {
            ios += "Nenhum processo de IO encontrado no processo P" + p.getpId();
        }
        
        return ios;
    }
    
    public static int getMomentoIos(ArrayList<Io> lista, int tempo, int quantum){
        for(Io io:lista){
            if(io.getMomento() >= tempo && io.getMomento() <= tempo+quantum){
                return io.getMomento();
            }
        }
        return -1;
    }
    
    public static String retornarIOs(ArrayList<Io> io){
        String ios = "";
        if(!io.isEmpty()){
            for(Io o:io){
               ios += o.getMomento();
               if(io.indexOf(o) != (io.size()-1)){
                   ios += ", ";
               }
               ios += "\n";
            }
        } else {
           ios += "Nenhum processo de IO encontrado no processo.";
        }
        return ios;
    }
    
    public static ArrayList<Processo> gnomeSort(ArrayList<Processo> v, int ponteiro, int var, boolean progresso) {
        if(ponteiro >= v.size()-1){
            return v;
        } else {
            if(progresso){
                if(v.get(ponteiro).getChegada() > v.get(ponteiro+1).getChegada() || ((v.get(ponteiro).getChegada() == v.get(ponteiro+1).getChegada()) && v.get(ponteiro).getDuracao() > v.get(ponteiro+1).getDuracao())){
                    Processo x = v.get(ponteiro);
                    v.set(ponteiro, v.get(ponteiro+1));
                    v.set(ponteiro+1, x);
                    return gnomeSort(v, ponteiro, ponteiro, false);
                } 
            } else {
                if(var > 0){
                    if(v.get(var).getChegada() < v.get(var-1).getChegada()){
                        Processo x = v.get(var-1);
                        v.set(var, v.get(var-1));
                        v.set(var-1, x);
                        return gnomeSort(v, ponteiro, var-1, false);
                    }
                }
            }
            return gnomeSort(v, ponteiro+1, ponteiro+1, true);
        }
    }
    
    public static void novoProcesso(Processo p){
        fila.add(p);
    }
}
