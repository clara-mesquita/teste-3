import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        
        
        int loop = -1; //entrar no loop no comeÃ§o do programa
        
        System.out.println("Bem-vindo ao seu programa de notas escolares. Para ultiliza-lo nsira o numero de acordo com a opÃ§Ã£o que deseja.");
        
        while (loop != 0){
            Scanner lerDisciplina = new Scanner (System.in); //mais de um scanner para tentar limpar buffer
            Scanner ler =  new Scanner(System.in);
            Scanner lerResp = new Scanner (System.in);
            Disciplina d = new Disciplina("New");
        
            
            System.out.println("\nMenu"
                    + "\n1: Criar ou ver se ja existe a disciplina"
                    + "\n2: Inserir aluno;"
                    + "\n3: Gerar notas;"
                    + "\n0: Fechar programa.");
            loop = ler.nextInt();
            if (loop == 0)
                break;
            
            else if (loop == 1){
                System.out.println("Insira o nome da disciplina a ser consultada: ");
                String nomeDisciplina = lerDisciplina.nextLine();
                
                if (d.arquivoExistente(nomeDisciplina))
                    System.out.println("Essa disciplina ja existe. Para reescrever seu gabarito, insira seu nome novamente na nova criaÃ§Ã£o de uma nova disciplina");
                else 
                    System.out.println("Essa disciplina nao existe!");
                
                System.out.println("Se deseja voltar ao menu insira 0, se deseja criar uma nova disciplina insira 1.");
                    int escolhaDisciplina = lerResp.nextInt();
                    if (escolhaDisciplina == 1){
                        System.out.println("Insira o nome da nova disciplina: ");
                        nomeDisciplina = lerDisciplina.nextLine();
                        d.setNome(nomeDisciplina);
                        d.criarDiretorio();
                        System.out.println("Agora, insira o gabarito da disciplina, sem espaÃ§os e em caixa alta: ");
                        String gabarito = lerResp.next();
                        d.gerarGabarito(gabarito);
                    }
                    else if (escolhaDisciplina != 0)
                        System.out.println("Resposta invalida!");
                }
            
            else if (loop == 2){
                System.out.println("Insira o nome da disciplina na qual voce deseja adicionar alunos:");
                String nomeDisciplina =  lerDisciplina.nextLine();

                if (d.arquivoExistente(nomeDisciplina)){                
                    int loopAluno = 1;
                    d.setNome(nomeDisciplina);
                    d.criarDiretorio();
                    while (loopAluno != 0){
                        Scanner lerAux = new Scanner(System.in);
                        d.setNome(nomeDisciplina);
                        System.out.println("\nInsira o nome do aluno:");
                        String nomeAluno = lerAux.nextLine();
                        System.out.println("\nInsira as respostas do aluno, sem espacos e em caixa alta:");
                        String respostasAluno = lerAux.next();
                        d.adicionarAluno(respostasAluno + "\t" + nomeAluno);

                        System.out.println("Se deseja voltar ao menu inisira 0, se deseja adicionar outro aluno nesse disciplina, insira 1.");
                        loopAluno = ler.nextInt();
                    }
                }

                else
                    System.out.println ("Essa disciplina nao existe!");

            }
             
            else if (loop == 3){
                    Scanner lerAux2 = new Scanner(System.in);
                    System.out.println("Deseja montar notas de qual disciplina? ");
                    String escolhaNotas = lerAux2.nextLine();
                    d.setNome(escolhaNotas);
                    
                    if (d.arquivoExistente2(escolhaNotas)){
                        

                        d.montarNotas1();

                        FileReader fr = new FileReader(d.getNotas1());
                        BufferedReader br = new BufferedReader(fr);

                        try {
                            String linha = br.readLine();

                            while(linha != null) {
                                System.out.println(linha);
                                linha = br.readLine();
                            }

                            fr.close();
                            br.close();
                        }
                        catch(FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        catch(IOException e) {
                            e.printStackTrace();
                        }



                        d.gerarNotas2();

                        FileReader fr2 = new FileReader(d.getNotas2());
                        BufferedReader br2 = new BufferedReader(fr2);

                        try {
                            String linha = br2.readLine();

                            while(linha != null) {
                                System.out.println(linha);
                                linha = br2.readLine();
                            }

                            fr.close();
                            br.close();
                        }
                        catch(FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch(IOException e) {
                            e.printStackTrace();
                        }
                }

                else 
                    System.out.println("Esse arquivo nao existe");
            }   
        
            
           
            else 
                System.out.println("Opcao invalida, tente novamente");
        
        }
            
        
       
        
        
        
    }
}