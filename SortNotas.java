import java.util.Comparator;

public class SortNotas implements Comparator<Aluno> {
    
    public int compare(Aluno a, Aluno b) {
        if(a.getNota() < b.getNota())
            return 1;
        else if(a.getNota() > b.getNota())
            return -1;
        else
            return 0;
    }
}
