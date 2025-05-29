import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeniuFestival implements Meniu {
    private FestivalService service;
    private Scanner scanner;

    public MeniuFestival(FestivalService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void afiseazaOptiuni() {
        System.out.println("\n==== MENIU FESTIVAL ====");
        System.out.println("1. Adaugă entitate");
        System.out.println("2. Afișează informații");
        System.out.println("3. Afișează RAPORT FINAL festival ");
        System.out.println("4. Analize și funcții inteligente");
        System.out.println("0. Ieșire");
        System.out.print("Alege opțiunea: ");
    }

    @Override
    public void executaOptiune(int optiune) {
        switch (optiune) {
            case 1 -> meniuAdaugare();
            case 2 -> meniuAfisare();
            case 3 -> afiseazaRaportFinal();
            case 0 -> System.out.println("Program încheiat.");
            case 4 -> meniuAnalizeSiActiuni();
            default -> System.out.println("Opțiune invalidă.");
        }
    }

    private void meniuAdaugare() {
        int optiune;
        do {
            System.out.println("\n=== ADAUGĂ ===");
            System.out.println("1. Participant");
            System.out.println("2. Artist");
            System.out.println("3. DJ");
            System.out.println("4. Concert");
            System.out.println("5. Stand FoodCourt");
            System.out.println("6. Merchandise");
            System.out.println("0. Înapoi");
            System.out.print("Alege opțiunea: ");
            optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune) {
                case 1 -> adaugaParticipant();
                case 2 -> adaugaArtist();
                case 3 -> adaugaDj();
                case 4 -> adaugaConcert();
                case 5 -> adaugaStandFoodCourt();
                case 6 -> adaugaMerchandise();
                case 0 -> System.out.println("Înapoi la meniul principal.");
                default -> System.out.println("Opțiune invalidă.");
            }
        } while (optiune != 0);
    }
    private void meniuAnalizeSiActiuni() {
        int optiune;
        do {
            System.out.println("\n=== ANALIZE & FUNCȚII INTELIGENTE ===");
            System.out.println("1. Recomandă merchandise pentru participant");
            System.out.println("2. Replanifică concerte cu conflicte");
            System.out.println("3. Cumpara merchandise ");
            System.out.println("4. Cumpara  mâncare");
            System.out.println("0. Inapoi");
            System.out.print("Alege opțiunea: ");
            optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune)
            {
                case 1 -> {
                    System.out.print("Nume participant pentru recomandare: ");
                    String nume = scanner.nextLine();
                    for (Participant p : service.getParticipanti()) {
                        if (p.getNume().equalsIgnoreCase(nume)) {
                            List<Merchandise> recomandari = service.recomandaMerchandise(p);
                            System.out.println("Recomandări pentru " + p.getNume() + ":");
                            for (Merchandise m : recomandari) {
                                System.out.println("- " + m.getProdus() + " - " + m.getPret() + " lei");
                            }
                            break;
                        }
                    }
                }
                case 2 -> service.replanificaConcerteDublate();
                case 3 -> {
                    System.out.print("Nume participant: ");
                    String nume = scanner.nextLine();
                    boolean gasit = false;
                    for (Participant p : service.getParticipanti()) {
                        if (p.getNume().equalsIgnoreCase(nume)) {
                            service.cumparaProduse(p);
                            gasit = true;
                            break;
                        }
                    }
                    if (!gasit) {
                        System.out.println(" Participantul nu a fost găsit.");
                    }
                }
                case 4 -> {
                    System.out.print("Nume participant: ");
                    String nume = scanner.nextLine();
                    boolean gasit = false;
                    for (Participant p : service.getParticipanti()) {
                        if (p.getNume().equalsIgnoreCase(nume)) {
                            service.cumparaMancare(p);
                            gasit = true;
                            break;
                        }
                    }
                    if (!gasit) {
                        System.out.println(" Participantul nu a fost găsit.");
                    }
                }


                case 0 -> System.out.println("Înapoi la meniul principal.");
                default -> System.out.println("Opțiune invalidă.");
            }
        } while (optiune != 0);
    }


    private void meniuAfisare()
    {
        int optiune;
        do {
            System.out.println("\n=== AFIȘARE ===");
            System.out.println("1. Participanți");
            System.out.println("2. Concerte");
            System.out.println("3. Merchandise sortat");
            System.out.println("4. Feedback participanți");
            System.out.println("0. Înapoi");
            System.out.print("Alege opțiunea: ");
            optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune) {
                case 1 -> service.afiseazaParticipanti();
                case 2 -> service.afiseazaConcerte();
                case 3 -> service.afiseazaMerchSortat();
                case 4 -> service.statisticaFeedback();
                case 0 -> System.out.println("Înapoi la meniul principal.");
                default -> System.out.println("Opțiune invalidă.");
            }
        } while (optiune != 0);
    }


    private void afiseazaRaportFinal()
    {

        System.out.println("\n===== 🧾 RAPORT FINAL FESTIVAL =====");

        int nrParticipanti = service.numarTotalParticipanti();
        double venitTotal = service.getVenitTotal();
        long nrConcerte = service.getConcerte().size();
        long merchVandut = service.getMerchandise().stream().filter(Merchandise::getVandut).count();


        System.out.println("\n PRO:");
        if (nrParticipanti >= 100) System.out.println("- Participare ridicată (" + nrParticipanti + " persoane)");
        if (venitTotal >= 50000) System.out.println("- Venit considerabil: " + venitTotal + " lei");
        if (merchVandut > 0) System.out.println("- Merchandise vândut: " + merchVandut + " articole");
        if (nrConcerte >= 3) System.out.println("- Diversitate muzicală: " + nrConcerte + " concerte");


        System.out.println("\n Zone de imbunatațit:");
        if (nrParticipanti < 100) System.out.println("- Participare scăzută. Poate fi promovat mai bine.");
        if (merchVandut == 0) System.out.println("- Merchandise nu a fost vândut deloc.");
        if (nrConcerte < 3) System.out.println("- Puține concerte organizate.");

        System.out.println("\n STATISTICI:");
        System.out.println("- Participanți: " + nrParticipanti);
        System.out.println("- Concerte: " + nrConcerte);
        System.out.println("- Merchandise total: " + service.getMerchandise().size());
        System.out.println("- Merchandise vândut: " + merchVandut);
        System.out.println("- Venit total: " + venitTotal + " lei");

        System.out.println("\n SUGESTII pentru ediția viitoare:");
        System.out.println("- Mai multe tipuri de bilete (VIP, GOLD, ARTIST AREA)");
        System.out.println("- Activitați extra: zone relaxare, competiții, întâlniri cu artiștii");
        System.out.println("- Extindere food court & opțiuni vegane");
        System.out.println("- Merch ediție limitată, vanzare online pre-event si post-event");
        service.afiseazaGraficVenituri();


    }


    private void adaugaParticipant() {
        System.out.print("Nume: ");
        String nume = scanner.nextLine();

        System.out.print("Vârstă: ");
        int varsta = Integer.parseInt(scanner.nextLine());

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Consuma alcool (true/false): ");
        boolean alcool = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Preț bilet: ");
        int pret = Integer.parseInt(scanner.nextLine());

        System.out.print("Tip bilet: ");
        String tip = scanner.nextLine();

        System.out.print("Preferă merchandise (true/false): ");
        boolean merch = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Preferă food court (true/false): ");
        boolean food = Boolean.parseBoolean(scanner.nextLine());

        Bilet bilet = new Bilet(pret, tip);
        System.out.print("Feedback despre festival: ");

        String feedback = scanner.nextLine();

        Participant p = new Participant(nume, varsta, email, alcool, bilet, merch, food, feedback);
        service.adaugaParticipant(p);


        service.emiteFacturaDigitala(p);

    }
    private void adaugaMerchandise() {
        System.out.print("Nume produs: ");
        String produs = scanner.nextLine();

        System.out.print("Preț (lei): ");
        double pret = Double.parseDouble(scanner.nextLine());

        System.out.print("Este vândut deja? (true/false): ");
        boolean vandut = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Este editie limitata ? (true/false): ");
        boolean editieLimitata = Boolean.parseBoolean(scanner.nextLine());


        Merchandise m=new Merchandise(produs, pret,editieLimitata, vandut);
        service.adaugaMerchandise(m);
        System.out.println("✅ Produs adăugat cu succes!");
    }


    private void adaugaArtist() {
        System.out.print("Nume artist: ");
        String nume = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Vârstă: ");
        int varsta = Integer.parseInt(scanner.nextLine());

        System.out.print("Consuma alcool? (true/false): ");
        boolean alcool = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Gen muzical: ");
        String genMuzical = scanner.nextLine();

        System.out.print("Tarif concert (lei): ");
        int tarif = Integer.parseInt(scanner.nextLine());

        System.out.print("Durata concert (minute): ");
        int durataConcert = Integer.parseInt(scanner.nextLine());

        System.out.print("Număr melodii: ");
        int nrMelodii = Integer.parseInt(scanner.nextLine());

        ArrayList<String> melodii = new ArrayList<>();
        for (int i = 0; i < nrMelodii; i++) {
            System.out.print("Melodie #" + (i + 1) + ": ");
            melodii.add(scanner.nextLine());
        }

        Artist artist = new Artist(nume, email, varsta, alcool, genMuzical, tarif, durataConcert, melodii);
        service.adaugaArtist(artist);
        System.out.println("✅ Artist adăugat cu succes!");
    }


    private void adaugaDj() {
        System.out.print("Nume DJ: ");
        String nume = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Vârstă: ");
        int varsta = Integer.parseInt(scanner.nextLine());

        System.out.print("Consuma alcool? (true/false): ");
        boolean alcool = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Gen muzical: ");
        String genMuzical = scanner.nextLine();

        System.out.print("Tarif (lei): ");
        int tarif = Integer.parseInt(scanner.nextLine());

        System.out.print("Stil de mixaj (ex: house, techno): ");
        String stilMix = scanner.nextLine();

        System.out.print("Durata concertului (minute): ");
        int durata = Integer.parseInt(scanner.nextLine());

        System.out.print("Număr melodii: ");
        int nrMelodii = Integer.parseInt(scanner.nextLine());
        List<String> melodii = new ArrayList<>();
        for (int i = 0; i < nrMelodii; i++) {
            System.out.print("Melodie #" + (i + 1) + ": ");
            melodii.add(scanner.nextLine());
        }

        System.out.print("Nivel experiență (ani): ");
        int nivel = Integer.parseInt(scanner.nextLine());

        System.out.print("Are visuals? (true/false): ");
        boolean visuals = Boolean.parseBoolean(scanner.nextLine());

        Dj dj = new Dj(nume, email, varsta, alcool, genMuzical, tarif, stilMix, durata, melodii, nivel, visuals);
        service.adaugaDj(dj);
        System.out.println("✅ DJ adăugat cu succes!");
    }

    private void adaugaConcert() {
        System.out.print("Nume concert: ");
        String nume = scanner.nextLine();

        System.out.print("Data (yyyy-MM-dd): ");
        String data = scanner.nextLine();

        System.out.print("Locație: ");
        String locatie = scanner.nextLine();

        System.out.print("Preț bilet (lei): ");
        double pretBilet = Double.parseDouble(scanner.nextLine());

        System.out.print("Numar spectatori: ");

        int numarSpectatori= Integer.parseInt(scanner.nextLine());
        Concert concert = new Concert(nume, data, locatie, pretBilet,numarSpectatori);

        // Adăugare scene
        System.out.print("Număr scene: ");
        int nrScene = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < nrScene; i++) {
            System.out.print("Nume scenă #" + (i + 1) + ": ");
            String numeScena = scanner.nextLine();

            System.out.print("Capacitate scenă: ");
            int capacitate = Integer.parseInt(scanner.nextLine());

            Scena scena = new Scena(numeScena, capacitate);
            concert.adaugaScena(scena);
        }

        // Adăugare artiști din listă existentă
        if (service.getArtisti().isEmpty()) {
            System.out.println("⚠️ Nu există artiști disponibili în sistem.");
        } else {
            System.out.println("Vrei să adaugi artiști în line-up? (da/nu)");
            String rasp = scanner.nextLine();
            if (rasp.equalsIgnoreCase("da")) {
                for (Artist artist : service.getArtisti()) {
                    System.out.print("Adaugi artistul " + artist.getNume() + "? (da/nu): ");
                    String opt = scanner.nextLine();
                    if (opt.equalsIgnoreCase("da")) {
                        concert.adaugaArtist(artist);
                    }
                }
            }
        }

        service.adaugaConcert(concert);
        System.out.println("✅ Concert adăugat cu succes!");
    }

    private void adaugaStandFoodCourt() {
        System.out.print("Nume zonă food court: ");
        String numeZona = scanner.nextLine();

        System.out.print("Capacitate persoane: ");
        int capacitate = Integer.parseInt(scanner.nextLine());

        System.out.print("Număr standuri: ");
        int nrStanduri = Integer.parseInt(scanner.nextLine());
        List<String> standuri = new ArrayList<>();
        for (int i = 0; i < nrStanduri; i++) {
            System.out.print("Nume stand #" + (i + 1) + ": ");
            standuri.add(scanner.nextLine());
        }

        System.out.print("Număr tipuri mâncare: ");
        int nrTipuri = Integer.parseInt(scanner.nextLine());
        List<String> tipuriMancare = new ArrayList<>();
        for (int i = 0; i < nrTipuri; i++) {
            System.out.print("Tip mâncare #" + (i + 1) + ": ");
            tipuriMancare.add(scanner.nextLine());
        }

        FoodCourt fc = new FoodCourt(numeZona, capacitate, standuri, tipuriMancare);
        service.adaugaFoodCourt(fc);
    }

}
