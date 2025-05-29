import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        FestivalService festival = new FestivalService();
         // I de meniu
        MeniuFestival meniu = new MeniuFestival(festival);
        Scanner scanner = new Scanner(System.in);
        int optiune;

        do {
            meniu.afiseazaOptiuni();
            try {
                optiune = Integer.parseInt(scanner.nextLine());
                meniu.executaOptiune(optiune);
            } catch (NumberFormatException e) {
                System.out.println("Te rog introdu un numar valid.");
                optiune = -1;
            }
        } while (optiune != 0);

        scanner.close();
    }
}
