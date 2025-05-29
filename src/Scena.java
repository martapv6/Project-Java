import java.util.ArrayList;
import java.util.List;

public class Scena {
    private String nume;
    private int capacitate;
    private String tipScena;
    private List<Artist> artistiProgramati;//da
    private List<String> echipamente; //inca nu

    public Scena(String nume,int capacitate,String tipScena,List<Artist> artistiProgramati,List<String> echipamente)
    {
        this.nume=nume;
        this.capacitate=capacitate;
        this.tipScena=tipScena;
        this.artistiProgramati=artistiProgramati;
        this.echipamente=echipamente;
    }
    public Scena(String nume,int capacitate)
    {
        this.nume=nume;
        this.capacitate=capacitate;
        this.tipScena=tipScena;
        this.artistiProgramati=new ArrayList<>();
        this.echipamente = new ArrayList<>();
        this.echipamente=echipamente;
    }

    public void adaugaArtist(Artist artist) { artistiProgramati.add(artist); }
    public void adaugaEchipament(String echipament) { echipamente.add(echipament);}
    public void afiseazaScena()
    {
        System.out.println("Scena: " + nume + " (" + tipScena + "), capacitate: " + capacitate);
        System.out.println("Echipamente: " + echipamente);
        System.out.println("Artisti programati:");
        for (Artist a : artistiProgramati)
        {
            System.out.println("- " + a.getNume());
        }
    }

    public String getNume() { return nume;}
    public int getCapacitate() { return capacitate;}
    public String getTipScena() { return tipScena; }
}
