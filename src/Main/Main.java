
package Main;


import java.util.ArrayList;
import java.util.Iterator;

import workStation.*;
import javax.swing.JOptionPane;

public class Main {
    static ArrayList<Processo> fila = new ArrayList<Processo>();
    static ArrayList<Processo> ready = new ArrayList<Processo>();
    static Gerenciador_Registros registros = new Gerenciador_Registros();
    static int quantum;
    public static void main(String[] args) {
        int menu = 0;
        int contP = 0;
        Processo p;
        quantum = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do Quantum"));
        //Pacote de inicialização direta
        ArrayList<Io> x = new ArrayList<Io>(), y = new ArrayList<Io>(), z = new ArrayList<Io>(), w = new ArrayList<Io>();
        x.add(new Io(2));
        x.add(new Io(4));
        x.add(new Io(6));
        x.add(new Io(8));
        novoProcesso(new Processo (9, 10, x, contP, '■'));
        contP++;
        y.add(new Io(5));
        
        novoProcesso(new Processo (10, 4, y, contP, '▓'));
        contP++;
        z.add(new Io(2));
        novoProcesso(new Processo (5, 0, z, contP, '□'));
        contP++;
        w.add(new Io(3));
        w.add(new Io(6));
        novoProcesso(new Processo (7, 1, w, contP, '☰'));
        contP++;
        novoProcesso(new Processo (2, 17, contP, '░'));
        contP++;
        //Fim de inserção direta
        int chave;
        
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
                       char symbol = JOptionPane.showInputDialog("\nEscolha o símbolo do processo"
                               + "\n1 - □"
                               + "\n2 - ■"
                               + "\n3 - ☰"
                               + "\n4 - ☲"
                               + "\n5 - ☱"
                               + "\n6 - ░"
                               + "\n7 - ▓"
                               + "\n8 - ▒"
                               + "\n9 - ❐").charAt(0);
                       switch(symbol) {
                           case '1':
                               p.setGraphic('□');
                               break;
                           case '2':
                               p.setGraphic('■');
                               break;
                           case '3':
                               p.setGraphic('☰');
                               break;
                           case '4':
                               p.setGraphic('☲');
                               break;
                           case '5':
                               p.setGraphic('☱');
                               break;
                           case '6':
                               p.setGraphic('░');
                               break;
                           case '7':
                               p.setGraphic('▓');
                               break;
                           case '8':
                               p.setGraphic('▒');
                               break;
                           case '9':
                               p.setGraphic('❐');
                               break;
                           default:
                               p.setGraphic(symbol); 
                               break;
                       }
                       while(subMenu != 6 && subMenu != 5){
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
                                       chave = Integer.parseInt(JOptionPane.showInputDialog("Entre com o número da chave do I/O que deseja eliminar"));
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
                                   alterarProcesso(p);
                                   break;
                               case 5:
                                   removerProcesso(p);
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
                                + "I/O's: " + retornarIOs(processo.getIo())
                                + "Símbolo: " + processo.getGraphic() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, processos);
                    break;
               case 3:
                    chave = Integer.parseInt(JOptionPane.showInputDialog("Entre com a ID do processo que você deseja alterar."));
                    alterarProcesso(fila, chave);
                    break;
                case 4:
                    chave = Integer.parseInt(JOptionPane.showInputDialog("Entre com a ID do processo que você deseja remover."));
                    removerProcesso(fila, chave);
                    break;
                case 5:
                    quantum = Integer.parseInt(JOptionPane.showInputDialog("Entre com o novo quantum."));
                    break;
                case 6:
                    ready = clonar(fila);
                    if(ready.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Não há processos para serem simulados!\nAdicione processos e tente novamente.");
                    } else {
                        gnomeSort(ready, 0, 0, true);
                        int tempo = 0;
                        while(tempo < ready.get(0).getChegada()){
                            tempo++;
                        }



                        while(!ready.isEmpty()){
                            for(Processo processo:ready){
                                int maxTempo = (processo.getDuracao() > (quantum)?quantum:processo.getDuracao());
                                boolean io = false;
                                int startAt = tempo;
                                Io holder = verificaIO(processo, maxTempo);
                                if(holder != null){
                                    io = true;
                                    tempo += holder.getMomento();
                                } else {
                                    tempo += maxTempo;
                                }

                                processo.processado(tempo-startAt);                                

                                addRegistro(new Registro(startAt, processo.getpId(), tempo, io), processo);
                                System.out.println("P" + processo.getpId() + " - " + processo.getDuracao() + "\nTempo Inicio: " + startAt + " - Tempo Fim: " + tempo);
                            }
                            resetandoLista(ready); // A função principal é excluir qualquer processo cuja duração seja 0, removendo da lista
                        }

                        registros.imprimirGant(fila);
                   
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }
    
    public static void addRegistro(Registro r, Processo p){
        int tme = 0;
        if(registros.isEmpty()){
            registros.add(r, (r.getInicio()-p.getChegada()));
        } else {
            Registro aux = registros.getLast(r.getProcesso());
            if(aux != null){
                tme += r.getInicio() - aux.getFim();
            } else {
                tme = r.getInicio() - p.getChegada();
            }
            registros.add(r, tme);
        }
    }
    
    public static ArrayList<Processo> clonar(ArrayList<Processo> list) {
    ArrayList<Processo> clone = new ArrayList<Processo>(list.size());
    for(Processo item: list){
        Processo p = item.clone();
        p.setIo(item.cloneIO());
        clone.add(p);
        
    }
    return clone;
}
    
    public static void resetandoLista(ArrayList<Processo> p){
        Iterator<Processo> i = p.iterator();
        while(i.hasNext()){
            if(i.next().getDuracao() <= 0){
                i.remove();
            }
        }
    }
    
    public static Io verificaIO(Processo p, int maxTempo){
        int i = 0;
            Io holder = p.buscaIO(maxTempo);
            if(holder != null){
                p.getIo().remove(holder);
                return holder;
            }
        return null;
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
            }
            ios += "\n";
        } else {
           ios += "Nenhum processo de IO encontrado no processo.\n";
        }
        return ios;
    }
    
    public static ArrayList<Processo> gnomeSort(ArrayList<Processo> v, int ponteiro, int var, boolean progresso) {
        if(ponteiro >= v.size()-1){
            for(Processo p:v){
                System.out.println(p.getChegada() + ", ");
            }
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
                        Processo x = v.get(var);
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
    
    public static boolean alterarProcesso(Processo p) {
        if(p != null){
            p.alterarProcesso(Integer.parseInt(JOptionPane.showInputDialog("\nA sua duração atual é: " + p.getDuracao() + "\nEntre com a nova duração.")),
            Integer.parseInt(JOptionPane.showInputDialog("\nA atual chegada é: " + p.getChegada() + "\nEntre com a nova chegada.")));
            JOptionPane.showMessageDialog(null, "Processo P" + p.getpId() + " alterado com sucesso.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível completar operação: Processo não encontrado!");
            return false;
        }
        
    }
    
    public static boolean alterarProcesso(ArrayList <Processo> p, int chave) {
        if (p.isEmpty()) {
            return false;
        } else {
            Iterator<Processo> i = p.iterator();
            while (i.hasNext()) {
                if (i.next().getpId() == chave) {
                    alterarProcesso(i.next());
                }
            }
        }
        return false;
    }
    
    public static void removerProcesso(Processo p) {
        fila.remove(p);
        String r = "Processo apagado com sucesso!";
        JOptionPane.showMessageDialog(null, r);
    }
    
    public static void removerProcesso(Iterator<Processo> p){
        p.remove();
        String r = "Processo apagado com sucesso!";
        JOptionPane.showMessageDialog(null, r);
    }
    
    public static boolean removerProcesso(ArrayList <Processo> p, int chave) {
        if (p.isEmpty()) {
            return false;
        } else {
            Iterator<Processo> i = p.iterator();
            while (i.hasNext()) {
                if (i.next().getpId() == chave) {
                    removerProcesso(i);
                    return true;
                }
            }
            return false;
        }
    }
}