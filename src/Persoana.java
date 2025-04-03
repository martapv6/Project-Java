public class Persoana {
    protected String nume;
    protected int varsta;
    protected String email;
    protected boolean alcool;


    public Persoana(String nume, int varsta,String email,boolean alcool) {
        this.nume=nume;
        this.varsta=varsta;
        this.email=email;
        this.alcool=alcool;
    }

    public int getVarsta(){return this.varsta;}
    public boolean getBani(){return this.alcool;}
    public String getEmail(){return this.email;}
    public String getNume(){return this.nume;}


    public void setNume(String nume) { this.nume=nume;}
    public void setEmail(String email) { this.email=email;}
    public void setAlcool(int bani) { this.alcool=alcool;}
    public void setVarsta(int varsta) { this.varsta=varsta;}





}
