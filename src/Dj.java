public class Dj extends Artist
{
    private String stilMix;



    public Dj(String nume,String email,int varsta,boolean alcool,String genMuzical,int tarif,
              String stilMix,int durataConcert)
    {
        super(nume,email,varsta,alcool,genMuzical,tarif,durataConcert);
        this.stilMix = stilMix;
    }
}
