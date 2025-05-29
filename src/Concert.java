import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concert {
    private final String nume;
    private String data;
    private String locatie;
    private List<Artist> artisti; //da inca nu
    private List<Scena> scene; //daa nu
    private int numarSpectatori;
    private double pretBilet;


    public Concert(String nume,String data,String locatie,double pretBilet,List<Artist> artisti,List<Scena> scene,int numarSpectatori)
    {
        this.nume=nume;
        this.data=data;
        this.locatie=locatie;
        this.pretBilet=pretBilet;
        this.artisti=artisti;
        this.scene=scene;
        this.numarSpectatori=numarSpectatori;


    }


    public Concert(String nume, String data, String locatie, double pretBilet,int numarSpectatori)
    {
        this.nume = nume;
        this.data = data;
        this.locatie = locatie;
        this.pretBilet = pretBilet;
        this.artisti = new ArrayList<>();
        this.scene = new ArrayList<>();
        this.numarSpectatori = numarSpectatori;
    }

    public void adaugaArtist(Artist artist) { artisti.add(artist);}
    public void adaugaScena(Scena scena) {  scene.add(scena); }

    public void afiseazaDetalii()
    {
        System.out.println("Concert: " + nume);
        System.out.println("Data: " + data);
        System.out.println("Locatie: " + locatie);
        System.out.println("Pret bilet: " + pretBilet + " lei");
        System.out.println("Artisti: ");
        for (Artist a : artisti) {
            System.out.println("- " + a.getNume());
        }
    }

    public String getNume() { return this.nume;}

    public List<Scena> getScene() { return scene;}
    public void setData(String data) { this.data=data;}

    public String getData() { return this.data;}
    public String getLocatie() {return this.locatie;}

    public List<Artist> getArtisti() { return this.artisti;
    }

    public double getPret() { return this.pretBilet;
    }

    public int getNumarSpectatori() {
        return numarSpectatori;
    }

    public void setNumarSpectatori(int numarSpectatori) {
        this.numarSpectatori = numarSpectatori;
    }
}
