import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;


public class FestivalService {

    private List<Concert> concerte;
    private List<Participant> participanti;
    private List<Merchandise> merch;
    private TreeSet<Merchandise> merchSortatDupaPret;
    private List<Artist> artisti = new ArrayList<>();
    private List<Dj> djs = new ArrayList<>();
    private List<FoodCourt> standuri = new ArrayList<>();


    public FestivalService()
    {
        concerte = new ArrayList<>();
        participanti = new ArrayList<>();
        merch = new ArrayList<>();
        merchSortatDupaPret = new TreeSet<>(Comparator.comparingDouble(Merchandise::getPret));
    }
    public void adaugaParticipant(Participant p)
    {
        participanti.add(p);
    }
    public void adaugaConcert(Concert c)
    {
        concerte.add(c);
    }
    public void adaugaMerchandise(Merchandise m)
    {
        merch.add(m);
        merchSortatDupaPret.add(m);
    }
    public void afiseazaMerchSortat()
    {
        for (Merchandise m : merchSortatDupaPret) {
            System.out.println(m.getProdus() + " - " + m.getPret() + " lei");
        }
    }


    //sortare
    public void sorteazaArtistiDupaTarif()
    {
        artisti.sort((a1,a2) -> Double.compare(a2.getTarif(), a1.getTarif()));
    }
    public Participant getParticipantCuCelMaiScumpBilet()
    {
        if (participanti.isEmpty()) return null;
        Participant max = participanti.get(0);
        for (Participant p : participanti) {
            if (p.getBilet().getPret() > max.getBilet().getPret()) {
                max = p;
            }
        }
        return max;
    }


    // 6
    public double getVenitTotal()
    {
        double suma = 0;
        for (Participant p : participanti)
        {
            suma += p.getBilet().getPret();

        }
        for (Merchandise m : merch) {
            if (m.getVandut()==true)
            {
                suma += m.getPret();
            }
        }
        return suma;
    }
    public void afiseazaConcerte() {for (Concert c : concerte) {System.out.println(c.getNume() + " - " + c.getData());}}
    public void afiseazaParticipanti()
    {
        for (Participant p : participanti)
        {
            System.out.println(p.getNume());
            System.out.println(p.getEmail());
            System.out.println(p.getVarsta());
        }
    }
    public int numarTotalParticipanti() {
        return participanti.size();
    }
    //13
    public void afiseazaMerchandisePestePret(double pretMinim)
    {
        for (Merchandise m : merch) {
            if (m.getPret() >= pretMinim) {
                System.out.println(m.getProdus() + " - " + m.getPret());
            }
        }
    }
    //12
    public void sorteazaParticipantiDupaPretBilet()
    {
        participanti.sort((p1, p2) -> Integer.compare(p2.getBilet().getPret(), p1.getBilet().getPret()));
        System.out.println("=== Participanți sortați după prețul biletului (descrescător): ===");
        for (Participant p : participanti) {
            System.out.println(p.getNume() + " - " + p.getBilet().getPret() + " lei");
        }
    }
 //11
    public void afiseazaParticipantiCuPreferinte() {
        System.out.println("=== Participanți cu preferințe merch/food ===");
        for (Participant p : participanti) {
            if (p.isPreferaMerch() || p.isPreferaFood()) {
                System.out.println(p.getNume() + " | Merch: " + p.isPreferaMerch() + " | Food: " + p.isPreferaFood());
            }
        }
    }

   //10
    public void cautaParticipant(String nume) {
        System.out.println("=== Căutare participant: " + nume + " ===");
        boolean gasit = false;
        for (Participant p : participanti) {
            if (p.getNume().toLowerCase().contains(nume.toLowerCase())) {
                System.out.println("Găsit: " + p.getNume() + " - " + p.getEmail());
                gasit = true;
            }
        }
        if (!gasit) {
            System.out.println("Niciun participant găsit cu acest nume.");
        }
    }

//9
    public void afiseazaRaportFinal() {
        System.out.println("🎉 Raport Festival:");
        System.out.println("- Participanți: " + participanti.size());
        System.out.println("- Venit total: " + getVenitTotal() + " lei");
        System.out.println("- Concerte: " + concerte.size());
        System.out.println("- Merchandise total: " + merch.size());
        System.out.println("- Merchandise vândut: " + merch.stream().filter(Merchandise::getVandut).count());
        System.out.println("- Participanți VIP: " +
                participanti.stream().filter(p -> p.getBilet().getTip().equalsIgnoreCase("VIP")).count());
    }

    public List<Concert> getConcerte() {
        return concerte;
    }
    public List<Merchandise> getMerchandise() {
        return merch;
    }
    //9
    public void statisticaFeedback() {
        System.out.println("\n=== Feedback participanți ===");
        Map<String, Integer> feedbackMap = new HashMap<>();

        for (Participant p : participanti) {
            String fb = p.getFeedback() != null ? p.getFeedback().toLowerCase() : "neprecizat";
            feedbackMap.put(fb, feedbackMap.getOrDefault(fb, 0) + 1);
        }

        for (String tip : feedbackMap.keySet()) {
            System.out.println("- " + tip + ": " + feedbackMap.get(tip) + " participanți");
        }
    }
    //8

    public void afiseazaGraficVenituri()
    {
        System.out.println("\n📊 Grafic venituri ");

        Map<String, Double> venituriPeZi = new TreeMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Concert c : concerte) {
            String data = c.getData(); // presupunem format "yyyy-MM-dd"
            venituriPeZi.putIfAbsent(data, 0.0);

            double venitZi = 0.0;

            for (Participant p : participanti) {
                venitZi += p.getBilet().getPret();
            }

            for (Merchandise m : merch) {
                if (m.getVandut()) {
                    venitZi += m.getPret();
                }
            }

            venituriPeZi.put(data, venituriPeZi.get(data) + venitZi);
        }

        double venitTotal = venituriPeZi.values().stream().mapToDouble(Double::doubleValue).sum();

        System.out.println("\n=== Grafic ===");
        for (Map.Entry<String, Double> entry : venituriPeZi.entrySet()) {
            String data = entry.getKey();
            double venit = entry.getValue();
            int bar = (int) (venit / venitTotal * 50);

            System.out.printf("%s: %s %.2f lei\n", data, "█".repeat(bar), venit);
        }
    }


    public void adaugaArtist(Artist artist) {
        artisti.add(artist);
    }
    public void adaugaDj(Dj dj) {
        djs.add(dj);
    }
    public void adaugaFoodCourt(FoodCourt fc) { standuri.add(fc);}
    public List<Artist> getArtisti() { return artisti;}


    //7
    public Artist getCelMaiSolicitatArtist()
    {
        Map<Artist, Integer> countMap = new HashMap<>();

        for (Concert c : concerte) {
            for (Artist a : c.getArtisti())
            {
                countMap.put(a, countMap.getOrDefault(a, 0) + 1);
            }
        }

        return countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

//6
    public void aplicaReducereGlobala(double procent)
    {
        for (Merchandise m : merch) {
            double pretNou = m.getPret() * (1 - procent / 100);
            m.setPret(pretNou);
        }
    }



    //5
    public List<Merchandise> recomandaMerchandise(Participant participant)
    {
        List<Merchandise> recomandari = new ArrayList<>();
        if (participant.isPreferaMerch()) {
            for (Merchandise m : merch) {
                if (!m.getVandut()) {
                    recomandari.add(m);
                }
            }
        }
        return recomandari;
    }







//4
    public void emiteFacturaDigitala(Participant p)
    {
        String fileName = "Factura_" + p.getNume().replaceAll("\\s+", "") + "_" + LocalDate.now() + ".txt";

        try (FileWriter writer=new FileWriter(fileName))
        {
            writer.write("=== FACTURA FESTIVAL ===\n");
            writer.write(" Data emiterii: " + LocalDate.now() + "\n\n");

            writer.write(" Client: " + p.getNume() + "\n");
            writer.write(" Email: " + p.getEmail() + "\n");
            writer.write(" Tip bilet: " + p.getBilet().getTip() + "\n");
            writer.write(" Pret bilet: " + p.getBilet().getPret() + " lei\n");
            writer.write(" Cod unic: BIL" + (p.getEmail().hashCode() & 0xfffffff) + "\n");

            writer.write("\n Mulțumim pentru achiziție și ne vedem la festival!\n");
            System.out.println(" Factura a fost generată: " + fileName);

        } catch (IOException e)
        {
            System.out.println("Eroare la generarea facturii pentru " + p.getNume());
        }
    }
//3
    public void replanificaConcerteDublate()
    {
        Set<String> combinatii = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Concert c : concerte) {
            String cheie = c.getData() + "_" + c.getLocatie();

            while (combinatii.contains(cheie)) {
                // Conflict -> mutăm cu o zi în plus
                LocalDate dataNoua = LocalDate.parse(c.getData(), formatter).plusDays(1);
                c.setData(dataNoua.format(formatter));
                cheie = c.getData() + "_" + c.getLocatie();
            }

            combinatii.add(cheie);
        }

        System.out.println(" Replanificare completă. Nu mai există suprapuneri în locații.");
    }


    public List<Participant> getParticipanti() {
        return participanti;
    }
//2
    public void cumparaProduse(Participant participant) {
        Scanner sc = new Scanner(System.in);
        List<Merchandise> produseDisponibile = merch.stream()
                .filter(m -> !m.getVandut())
                .toList();

        if (produseDisponibile.isEmpty() && standuri.isEmpty()) {
            System.out.println(" Nu sunt produse disponibile momentan.");
            return;
        }

        System.out.println("=== Produse Merchandise Disponibile ===");
        for (int i = 0; i < produseDisponibile.size(); i++) {
            Merchandise m = produseDisponibile.get(i);
            System.out.println((i + 1) + ". " + m.getProdus() + " - " + m.getPret() + " lei");
        }

        System.out.println("Alege produsul : ");
        int opt = Integer.parseInt(sc.nextLine());
        if (opt > 0 && opt <= produseDisponibile.size()) {
            Merchandise cumparat = produseDisponibile.get(opt - 1);
            cumparat.setVandut(true);
            System.out.println("Ai cumpărat: " + cumparat.getProdus());
        }

        System.out.println(" Mulțumim pentru cumpărături!");
    }



  //1
    public void cumparaMancare(Participant participant)
    {
        if (standuri.isEmpty()) {
            System.out.println(" Nu există standuri food court disponibile.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== ZONE FOOD COURT ===");
        for (int i = 0; i < standuri.size(); i++) {
            FoodCourt fc = standuri.get(i);
            System.out.println((i + 1) + ". " + fc.getNume() + " - Tipuri mancare: " + fc.getTipuriMancare());
        }

        System.out.print("Alege zona din care vrei să cumperi (0 pentru anulare): ");
        int zona = Integer.parseInt(sc.nextLine());

        if (zona < 1 || zona > standuri.size()) {
            System.out.println(" Anulat sau zonă invalida.");
            return;
        }

        FoodCourt selectat = standuri.get(zona - 1);
        List<String> meniuri = selectat.getTipuriMancare();

        if (meniuri.isEmpty()) {
            System.out.println("Nu sunt feluri de mancare disponibile in această zonă.");
            return;
        }

        System.out.println("\n Meniu disponibil:");
        for (int i = 0; i < meniuri.size(); i++) {
            System.out.println((i + 1) + ". " + meniuri.get(i));
        }

        System.out.print("Alege mâncarea (0 pentru anulare): ");
        int opt = Integer.parseInt(sc.nextLine());

        if (opt < 1 || opt > meniuri.size()) {
            System.out.println(" Anulat sau opțiune invalidă.");
            return;
        }

        String mancare = meniuri.get(opt - 1);
        System.out.println(" Ai cumpărat: " + mancare + " din zona " + selectat.getNume() + ". Poftaa bună!");
    }


}
