public class Merchandise {
    private String produs;
    private double pret;
    private boolean editieLimitata;
    private boolean vandut;


    Merchandise (String produs,double pret,boolean editieLimitata,boolean vandut)
    {
        this.produs=produs;
        this.pret=pret;
        this.editieLimitata=editieLimitata;
        this.vandut=vandut;
    }
    public String getProdus() { return produs;}
    public double getPret() { return pret;}
    public boolean getEditie() { return editieLimitata; }
    public boolean getVandut() { return this.vandut; }


    public void setProdus(String produs) { this.produs=produs;}
    public void setPret(Double pret) { this.pret=pret;}
    public void setEditieLimitata(boolean editieLimitata) { this.editieLimitata=editieLimitata;}

    public void setVandut(boolean vandut) {
        this.vandut = vandut;
    }
}
