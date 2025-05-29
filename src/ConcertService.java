import java.util.List;

public class ConcertService {
    private static ConcertService instance;
    private final ConcertDAO concertDAO;
    public void asociazaScenaCuConcert(int idConcert, int idScena) {
        concertDAO.adaugaScenaLaConcert(idConcert, idScena);
    }

    private ConcertService() {
        this.concertDAO = new ConcertDAO();
    }

    public static ConcertService getInstance() {
        if (instance == null) {
            instance = new ConcertService();
        }
        return instance;
    }
    public void asociazaArtistCuConcert(int idConcert, int idArtist) {
        concertDAO.adaugaArtistLaConcert(idConcert, idArtist);
    }


    public void adaugaConcert(Concert concert) {
        concertDAO.insertConcert(concert);
    }

    public Concert getConcert(int id) {
        return concertDAO.readConcert(id);
    }

    public void actualizeazaConcert(int id, Concert concert) {
        concertDAO.updateConcert(id, concert);
    }

    public void stergeConcert(int id) {
        concertDAO.deleteConcert(id);
    }

    public List<Concert> getToateConcertele() {
        return concertDAO.readAllConcerts();
    }
}
