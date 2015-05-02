<<<<<<< HEAD
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
        int contP = 3;
        Processo p;
        quantum = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do Quantum"));
        //Pacote de inicialiazção direta
        novoProcesso(new Processo (8, 2, 0));
        ArrayList<Io> x = new ArrayList<Io>(), y = new ArrayList<Io>();
        x.add(new Io(1));
        x.add(new Io(9));
        novoProcesso(new Processo (14, 6, x, 1));
        y.add(new Io(4));
        novoProcesso(new Processo (22, 4, y, 2));
        //Fim de inserção direta
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
                                processo.processado(quantum);                                
                                /*if(processo.getDuracao() <= 0){
                                    ready.remove(processo);
                                }*/
                                registros.add(new Registro(startAt, processo.getpId(), tempo, io));
                            }
                            resetandoLista(ready); // A função principal é excluir qualquer processo cuja duração seja 0, removendo da lista
                        }
                        /*
                        final JFrame f = new JFrame("Teste");
                        f.setSize(600,400);
                        f.setVisible(true);
                        final JPanel pane = new JPanel();
                        
                        JButton btn = new JButton("Clicar");
                        pane.add(btn);
                        btn.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                JPanel cc = new JPanel();
                                cc.add(new JLabel("Olha, funciona"));
                                f.add(cc);
                                pane.setVisible(false);
                            }
                        });
                        
                        f.add(pane);
                        JOptionPane.showMessageDialog(f, "Objeto");
                        Reformular toda a primeira parte do menu?*/
                        
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
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
=======
package Main;

import java.util.ArrayList;
import java.util.Iterator;
import workStation.*;
import javax.swing.JOptionPane;
public class Main {
    static ArrayList<Processo> fila = new ArrayList<Processo>();
    static int quantum;
    static ArrayList<Processo> ready = new ArrayList<Processo>();
    public static void main(String[] args) {
        int menu = 0;
        int contP = 3;
        Processo p;
        quantum = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do Quantum"));
        //Pacote de inicialiazção direta
        novoProcesso(new Processo (8, 2, 0));
        ArrayList<Io> x = new ArrayList<Io>(), y = new ArrayList<Io>();
        x.add(new Io(1));
        x.add(new Io(9));
        novoProcesso(new Processo (14, 6, x, 1));
        y.add(new Io(4));
        novoProcesso(new Processo (22, 4, y, 2));
        //Fim de inserção direta
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
                                   alterarProcesso(p);
                                   break;
                               case 5:
                                   //Excluir processo, criar método para evitar alongar demais o código e repetições
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
                                + "I/O's: " + retornarIOs(processo.getIo());
                    }
                    JOptionPane.showMessageDialog(null, processos);
                    break;
                case 3:
                    int chave1 = Integer.parseInt(JOptionPane.showInputDialog("Entre com a ID do processo que você deseja alterar."));
                    alterarProcesso(fila, chave1);
                    break;
                case 4:
                    int chave2 = Integer.parseInt(JOptionPane.showInputDialog("Entre com a ID do processo que você deseja remover."));
                    removerProcesso(fila, chave2);
                    break;
                case 5:
                    //Alterar quantum
                    //quantum = Integer.parseInt(JOptionPane.showInputDialog("Entre com o novo valor do Quantum"));
                    break;
                case 6:
                    ready = clonar(fila);
                    if(ready.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Não há processos para serem simulados!\nAdicione processos e tente novamente.");
                    } else {
                        gnomeSort(ready, 0, 0, true);
                        int ponteiro = 0, tempo = 0;
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
                                    tempo += holder.getMomento();
                                } else {
                                    tempo += maxTempo;
                                }
                                processo.processado(quantum);                                
                                System.out.println("Tempo inicial: " + startAt + "\n Tempo final: " + tempo);
                                /*if(processo.getDuracao() <= 0){
                                    ready.remove(processo);
                                }*/
                            }
                            resetandoLista(ready); // A função principal é excluir qualquer processo cuja duração seja 0, removendo da lista
                        }
                        
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
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
    
    public static boolean alterarProcesso(Processo p) {
        p.alterarProcesso(Integer.parseInt(JOptionPane.showInputDialog("\nA sua duração atual é: " + p.getDuracao() + "\nEntre com a nova duração.")),
                Integer.parseInt(JOptionPane.showInputDialog("\nA atual chegada é: " + p.getChegada() + "\nEntre com a nova chegada.")));
        return true;
    }
    
    public static boolean alterarProcesso(ArrayList <Processo> p, int chave) {
        if (p.isEmpty()) {
            return false;
        } else {
            for (Processo o : p) {
                if (o.getpId() == chave) {
                    alterarProcesso(o);
                }
            }
        }
        return false;
    }
    
    public static boolean removerProcesso(Processo p) {
        file.remove(p);
        String r = "Processo apagado com sucesso!";
        JOptionPane.showMessageDialog(null, r);
        return true;
    }
    
    public static boolean removerProcesso(ArrayList <Processo> p, int chave) {
        if (p.isEmpty()) {
            return false;
        } else {
            for (Processo o : p) {
                if (o.getpId() == chave) {
                    removerProcesso(o);
                }
            }
            return false;
        }
    }
}
>>>>>>> origin/Colaboradores
