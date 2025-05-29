import java.util.ArrayList;
import java.util.Scanner;

public class MeniuGeneralDAO {

    private static final Scanner scanner = new Scanner(System.in);

    // Apelăm doar serviciile Singleton, nu DAO-uri direct
    private static final ParticipantService participantService = ParticipantService.getInstance();
    private static final ConcertService concertService = ConcertService.getInstance();
    private static final FoodCourtService foodCourtService = FoodCourtService.getInstance();
    private static final MerchandiseService merchandiseService = MerchandiseService.getInstance();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENIU PRINCIPAL =====");
            System.out.println("1. Test conexiune BD");
            System.out.println("2. CRUD Participant");
            System.out.println("3. CRUD Concert");
            System.out.println("4. CRUD FoodCourt");
            System.out.println("5. CRUD Merchandise");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");

            String opt = scanner.nextLine();

            switch (opt) {
                case "1" -> testConexiune();
                case "2" -> meniuParticipant();
                case "3" -> meniuConcert();
                case "4" -> meniuFoodCourt();
                case "5" -> meniuMerchandise();
                case "0" -> {
                    System.out.println("La revedere!");
                    return;
                }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    private static void testConexiune() {
        if (Db.getConnection() != null) {
            System.out.println("Conexiune realizată cu succes!");
        } else {
            System.out.println("Eroare la conectare!");
        }
    }

    private static void meniuParticipant() {
        System.out.println("\n-- CRUD Participant --");
        System.out.println("1. Adaugă");
        System.out.println("2. Citește");
        System.out.println("3. Update");
        System.out.println("4. Șterge");
        System.out.print("Alege: ");
        String opt = scanner.nextLine();

        switch (opt) {
            case "1" -> {
                System.out.print("Nume: ");
                String nume = scanner.nextLine();
                System.out.print("Varsta: ");
                int varsta = Integer.parseInt(scanner.nextLine());
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Alcool (true/false): ");
                boolean alcool = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Prefera merch: ");
                boolean merch = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Prefera food: ");
                boolean food = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Feedback: ");
                String feedback = scanner.nextLine();

                // Bilet
                System.out.print("Tip bilet: ");
                String tipBilet = scanner.nextLine();
                System.out.print("Preț bilet: ");
                int pretBilet = Integer.parseInt(scanner.nextLine());

                Bilet bilet = new Bilet( pretBilet,tipBilet);
                Participant p = new Participant(nume, varsta, email, alcool, bilet, merch, food, feedback);

                participantService.adaugaParticipant(p);
            }
            case "2" -> {
                System.out.print("ID Participant: ");
                int id = Integer.parseInt(scanner.nextLine());
                Participant p = participantService.getParticipant(id);
                if (p != null) {
                    System.out.println("Nume: " + p.getNume());
                    System.out.println("Email: " + p.getEmail());
                    System.out.println("Feedback: " + p.getFeedback());
                } else {
                    System.out.println("Nu a fost găsit.");
                }
            }
            case "3" -> {
                System.out.print("ID Participant: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Nume nou: ");
                String nume = scanner.nextLine();
                System.out.print("Varsta: ");
                int varsta = Integer.parseInt(scanner.nextLine());
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Alcool: ");
                boolean alcool = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Merch: ");
                boolean merch = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Food: ");
                boolean food = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Feedback: ");
                String feedback = scanner.nextLine();

                Participant p = new Participant(nume, varsta, email, alcool, null, merch, food, feedback);
                participantService.updateParticipant(id, p);
            }
            case "4" -> {
                System.out.print("ID Participant: ");
                int id = Integer.parseInt(scanner.nextLine());
                participantService.deleteParticipant(id);
            }
            default -> System.out.println("Opțiune invalidă!");
        }
    }
    private static void meniuConcert() {
        System.out.println("\n-- CRUD Concert --");
        System.out.println("1. Adaugă");
        System.out.println("2. Afișează");
        System.out.println("3. Update");
        System.out.println("4. Șterge");
        System.out.println("5. Asociază Artist cu Concert");
        System.out.println("6. Asociază Scenă la Concert");

        System.out.print("Alege: ");
        String opt = scanner.nextLine();

        switch (opt) {
            case "1" -> {
                System.out.print("Nume: ");
                String nume = scanner.nextLine();
                System.out.print("Data: ");
                String data = scanner.nextLine();
                System.out.print("Locație: ");
                String locatie = scanner.nextLine();
                System.out.print("Preț bilet: ");
                double pret = Double.parseDouble(scanner.nextLine());
                System.out.print("Număr spectatori: ");
                int numarSpectatori = Integer.parseInt(scanner.nextLine());

                Concert c = new Concert(nume, data, locatie, pret, numarSpectatori);
                concertService.adaugaConcert(c);
            }
            case "2" -> {
                System.out.print("ID Concert: ");
                int id = Integer.parseInt(scanner.nextLine());
                Concert c = concertService.getConcert(id);
                if (c != null) {
                    c.afiseazaDetalii();
                } else {
                    System.out.println("Concertul nu a fost găsit.");
                }
            }
            case "3" -> {
                System.out.print("ID Concert de actualizat: ");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.print("Nume nou: ");
                String nume = scanner.nextLine();
                System.out.print("Dată nouă: ");
                String data = scanner.nextLine();
                System.out.print("Locație nouă: ");
                String locatie = scanner.nextLine();
                System.out.print("Preț bilet nou: ");
                double pret = Double.parseDouble(scanner.nextLine());
                System.out.print("Număr spectatori nou: ");
                int nrSpectatori = Integer.parseInt(scanner.nextLine());

                Concert c = new Concert(nume, data, locatie, pret, nrSpectatori);
                concertService.actualizeazaConcert(id, c);
            }
            case "4" -> {
                System.out.print("ID Concert de șters: ");
                int id = Integer.parseInt(scanner.nextLine());
                concertService.stergeConcert(id);
            }
            case "5" -> {
                System.out.print("ID Concert: ");
                int idConcert = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Artist: ");
                int idArtist = Integer.parseInt(scanner.nextLine());

                concertService.asociazaArtistCuConcert(idConcert, idArtist);
            }
            case "6" -> {
                System.out.print("ID Concert: ");
                int idConcert = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Scenă: ");
                int idScena = Integer.parseInt(scanner.nextLine());

                concertService.asociazaScenaCuConcert(idConcert, idScena);
            }
            default -> System.out.println("Opțiune invalidă!");
        }
    }
    private static void meniuFoodCourt() {
        System.out.println("\n-- CRUD FoodCourt --");
        System.out.println("1. Adaugă");
        System.out.println("2. Citește");
        System.out.println("3. Update");
        System.out.println("4. Șterge");
        System.out.print("Alege: ");
        String opt = scanner.nextLine();

        switch (opt) {
            case "1" -> {
                System.out.print("Nume: ");
                String nume = scanner.nextLine();
                System.out.print("Capacitate: ");
                int capacitate = Integer.parseInt(scanner.nextLine());

                FoodCourt fc = new FoodCourt(nume, capacitate, new ArrayList<>(), new ArrayList<>());
                foodCourtService.adaugaFoodCourt(fc);
            }
            case "2" -> {
                System.out.print("ID FoodCourt: ");
                int id = Integer.parseInt(scanner.nextLine());
                FoodCourt fc = foodCourtService.getFoodCourt(id);
                if (fc != null) {
                    fc.afiseazaInfo();
                } else {
                    System.out.println("FoodCourt-ul nu a fost găsit.");
                }
            }
            case "3" -> {
                System.out.print("ID FoodCourt de actualizat: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Nume nou: ");
                String nume = scanner.nextLine();
                System.out.print("Capacitate nouă: ");
                int capacitate = Integer.parseInt(scanner.nextLine());

                FoodCourt fc = new FoodCourt(nume, capacitate, new ArrayList<>(), new ArrayList<>());
                foodCourtService.actualizeazaFoodCourt(id, fc);
            }
            case "4" -> {
                System.out.print("ID FoodCourt de șters: ");
                int id = Integer.parseInt(scanner.nextLine());
                foodCourtService.stergeFoodCourt(id);
            }
            default -> System.out.println("Opțiune invalidă!");
        }
    }

    private static void meniuMerchandise() {
        System.out.println("\n-- CRUD Merchandise --");
        System.out.println("1. Adaugă");
        System.out.println("2. Citește");
        System.out.print("Alege: ");
        String opt = scanner.nextLine();

        if (opt.equals("1")) {
            System.out.print("Produs: ");
            String produs = scanner.nextLine();
            System.out.print("Preț: ");
            double pret = Double.parseDouble(scanner.nextLine());
            System.out.print("Ediție limitată (true/false): ");
            boolean editie = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Vândut (true/false): ");
            boolean vandut = Boolean.parseBoolean(scanner.nextLine());

            Merchandise m = new Merchandise(produs, pret, editie, vandut);
            merchandiseService.adaugaMerchandise(m);

        } else if (opt.equals("2")) {
            System.out.print("ID Merchandise: ");
            int id = Integer.parseInt(scanner.nextLine());
            Merchandise m = merchandiseService.getMerchandise(id);
            if (m != null) {
                System.out.println("Produs: " + m.getProdus());
                System.out.println("Preț: " + m.getPret());
                System.out.println("Ediție limitată: " + m.getEditie());
                System.out.println("Vândut: " + m.getVandut());
            } else {
                System.out.println("Nu a fost găsit.");
            }
        }
    }
}
