import java.sql.Connection;
import java.sql.DriverManager;



public class Participant extends Persoana {
    private Bilet bilet; //de adaugat in baza de date
    private boolean preferaMerch;
    private boolean preferaFood;
    private String feedback; // poate fi "bun", "rau", "neutru"


    public Participant(String nume, int varsta, String email, boolean alcool,
                       Bilet bilet, boolean preferaMerch, boolean preferaFood,String feedback) {
        super(nume, varsta, email, alcool);
        this.bilet = bilet;
        this.preferaMerch = preferaMerch;
        this.preferaFood = preferaFood;
        this.feedback=feedback;
    }
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public Bilet getBilet() {
        return bilet;
    }

    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }

    public boolean isPreferaMerch() {
        return preferaMerch;
    }

    public void setPreferaMerch(boolean preferaMerch) {
        this.preferaMerch = preferaMerch;
    }

    public boolean isPreferaFood() {
        return preferaFood;
    }

    public void setPreferaFood(boolean preferaFood) {
        this.preferaFood = preferaFood;
    }

    public void afiseazaPreferinte() {
        System.out.println(getNume() + " preferă:");
        System.out.println("- Merch: " + (preferaMerch ? "Da" : "Nu"));
        System.out.println("- Food Court: " + (preferaFood ? "Da" : "Nu"));
    }

    public boolean getAlcool() { return this.alcool;}

    public boolean isAlcool() { return this.alcool;
    }
}
