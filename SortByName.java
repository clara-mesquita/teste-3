import java.util.Comparator;

public class SortByName implements Comparator<Aluno> {
    
    public int compare(Aluno a, Aluno b) {
        if(a.getNome().compareTo(b.getNome()) > 0)
            return 1;
        else if(a.getNome().compareTo(b.getNome()) < 0)
            return -1;
        else
            return 0;
    }
}