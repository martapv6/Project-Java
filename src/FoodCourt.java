import java.util.ArrayList;
import java.util.List;

public class FoodCourt
{
    private final String nume;
    private int capacitate;
    private List<String> standuri;//inca nu
    private List<String> tipuriMancareDisponibile; //inca nu

    public FoodCourt(String nume, int capacitate,List<String> standuri,List<String> tipuriMancareDisponibile)
    {
        this.nume=nume;
        this.capacitate=capacitate;
        this.standuri=standuri;
        this.tipuriMancareDisponibile=tipuriMancareDisponibile;
    }

    public void adaugaStand(String stand) { standuri.add(stand);}
    public void adaugaTipMancare(String tip) { tipuriMancareDisponibile.add(tip);}
    public void afiseazaInfo() {
        System.out.println("Zona FoodCourt: " + nume);
        System.out.println("Capacitate maximă: " + capacitate + " persoane");
        System.out.println("Standuri de mâncare:");
        for (String s : standuri) {
            System.out.println("- " + s);
        }
        System.out.println("Tipuri de mâncare disponibile:");
        for (String tip : tipuriMancareDisponibile) {
            System.out.println("- " + tip);
        }
    }


    public String getNume() {return nume;}
    public int getCapacitate() {return capacitate;}

    public List<String> getTipuriMancare() {
        return tipuriMancareDisponibile;
    }
}
