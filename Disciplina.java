import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Disciplina {

    private String nome;
    private File diretorio;
    private File arquivo;
    private File alunos;
    private File notas1; 
    private File notas2; 
    

    public Disciplina(String nome) {
        this.nome = nome;
    }
    
    public void setNome (String nome){
        this.nome = nome;
    }

    public File getNotas1() {
        return this.notas1;
    }

    public File getNotas2() {
        return this.notas2;
    }

    public void criarDiretorio() {
        String nomeDiretorio = "c:\\" + this.nome;
        diretorio = new File(nomeDiretorio);
        this.diretorio.mkdir();
    }
    
    public boolean arquivoExistente (String nomeDisciplina){
        String nomeArquivo = "c:\\" + nomeDisciplina + "\\gabarito.txt"; 
        return new File(nomeArquivo).exists();
    }
    
    public boolean arquivoExistente2 (String nomeDisciplina){
        String nomeArquivo = "c:\\" + nomeDisciplina + "\\alunos.txt"; 
        return new File(nomeArquivo).exists();
    }
    
    public void adicionarAluno(String resposta) throws IOException { 
        this.alunos = new File(diretorio, "alunos.txt");

        FileWriter fw = new FileWriter(this.alunos, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(resposta);
        bw.newLine();

        bw.close();
        fw.close();
    }

    public void gerarGabarito(String gabarito) throws IOException { 
        this.arquivo = new File(diretorio, "gabarito.txt"); 

        FileWriter fw = new FileWriter(this.arquivo, false);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(gabarito);
        bw.close();
        fw.close();
    }

    public String caminhoGabarito() {
        return this.arquivo.getAbsolutePath();
    }

    public int gerarNota(String resposta) throws IOException {

        int nota = 0, qtdV = 0, qtdF = 0;

        FileReader fr = new FileReader(this.arquivo);
        BufferedReader br = new BufferedReader(fr);

        String gabarito = br.readLine();

        for(int i=0; i<resposta.length(); i++) {
            if(resposta.charAt(i) == gabarito.charAt(i)) nota++;

            if(resposta.charAt(i) == 'V') qtdV++;
            else qtdF++;
        }

        br.close();
        fr.close();

        if(qtdV == 10 || qtdF == 10)
            nota = 0;

        return nota;
    }

    public void montarNotas1() throws IOException { 

        this.notas1 = new File(diretorio, "notas1.txt");

        FileReader fr = new FileReader(this.alunos);
        BufferedReader br = new BufferedReader(fr);

        FileWriter fw = new FileWriter(this.notas1, true);
        BufferedWriter bw = new BufferedWriter(fw);

        ArrayList <Aluno> listaAuxiliar = new ArrayList<>(); 
        double media = 0; 
        while(true) {
            String[] dados = linha.split("\t"); 
            
            int nota = gerarNota(dados[0]); 
            Aluno a = new Aluno(dados[1], nota);
            listaAuxiliar.add(a);
            media += nota;

           String linha = br.readLine();
           if (linha == null)
               break;
        }

        Collections.sort(listaAuxiliar, new SortByName()); 
        media /= listaAuxiliar.size();

        for(Aluno al : listaAuxiliar) {
            bw.write(al.getNome() + "\t" + al.getNota()); 
            bw.newLine();
        }

        bw.write("A media final da turma eh:" + "\t" + media);

        bw.close();
        fw.close();
        br.close();
        fr.close();
    }

    public void gerarNotas2() throws IOException { 

        this.notas2 = new File(diretorio, "notas2.txt");

        FileReader fr = new FileReader(this.alunos);
        BufferedReader br = new BufferedReader(fr);

        FileWriter fw = new FileWriter(this.notas2, true);
        BufferedWriter bw = new BufferedWriter(fw);

        ArrayList <Aluno> listaAuxiliar = new ArrayList<>();
        String linha = br.readLine(); 
        double media = 0;
        while(linha != null) {
            String[] dados = linha.split("\t"); 
            
            int nota = gerarNota(dados[0]); 
            Aluno a = new Aluno(dados[1], nota);
            listaAuxiliar.add(a);
            media += nota;

            linha = br.readLine();
        }

        Collections.sort(listaAuxiliar, new SortNotas()); 
        media /= listaAuxiliar.size();

        for(Aluno al : listaAuxiliar) {
            bw.write(al.getNome() + "\t" + al.getNota()); 
            bw.newLine();
        }

        bw.write("A media final da turma eh:" + "\t" + media);

        bw.close();
        fw.close();
        br.close();
        fr.close();
    }
}
