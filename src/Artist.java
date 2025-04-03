
class Artist extends Persoana
{
    protected String genMuzical;
    protected int tarif;
    protected int durataConcert;



    public Artist(String nume,String email,int varsta,boolean alcool,String genMuzical,int tarif,int durataConcert)
    {
        super(nume,varsta,email,alcool);
        this.genMuzical=genMuzical;
        this.tarif=tarif;
        this.durataConcert=durataConcert;
    }
    public String getGenMuzical() { return this.genMuzical;}
    public int getTarif() { return this.tarif;}
    public int getDurata() { return this.durataConcert;}


    public void setGenMuzical(String genMuzical) { this.genMuzical=genMuzical;}
    public void setTarif(int tarif) { this.tarif=tarif;}
    public void setDurata(int durata) { this.durataConcert=durata;}

}