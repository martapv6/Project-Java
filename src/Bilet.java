public class Bilet
{
    protected int pret;
    protected String tip;

    Bilet(int pret,String tip)
    {
        this.pret=pret;
        this.tip=tip;
    }
    public int getPret(){return this.pret;}
    public String getTip(){return this.tip;}


    public void setPret(int pret) { this.pret=pret;}
    public void setTip(String tip) { this.tip=tip;}
}
