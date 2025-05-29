import java.util.List;
public class Dj extends Artist
{
    private String stilMix;
    private int nivelExperienta;
    private boolean areVisuals;


    public Dj(String nume,String email,int varsta,boolean alcool,String genMuzical,int tarif,
              String stilMix,int durataConcert,List<String> melodii,int nivelExperienta,boolean areVisuals)
    {
        super(nume,email,varsta,alcool,genMuzical,tarif,durataConcert,melodii);
        this.stilMix=stilMix;
        this.nivelExperienta=nivelExperienta;
        this.areVisuals=areVisuals;
    }

    public String getStilMix() { return this.stilMix;}
    public int getNivelExperienta() { return this.nivelExperienta;}
    public boolean getVisuals() { return this.areVisuals;}


    public void setStilMix(String stilMix) { this.stilMix=stilMix;}
    public void setNivelExperienta(int  nivelExperienta) { this.nivelExperienta=nivelExperienta;}
    public void setAreVisuals(boolean areVisuals) { this.areVisuals=areVisuals;}
}
