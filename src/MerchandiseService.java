import java.util.List;

public class MerchandiseService {
    private static MerchandiseService instance;
    private final MerchandiseDAO merchandiseDAO;

    private MerchandiseService() {
        this.merchandiseDAO = new MerchandiseDAO();
    }

    public static MerchandiseService getInstance() {
        if (instance == null) {
            instance = new MerchandiseService();
        }
        return instance;
    }

    public void adaugaMerchandise(Merchandise m) {
        merchandiseDAO.insertMerchandise(m);
    }

    public Merchandise getMerchandise(int id) {
        return merchandiseDAO.readMerchandise(id);
    }

    public void updateMerchandise(int id, Merchandise m) {
        merchandiseDAO.updateMerchandise(id, m);
    }

    public void deleteMerchandise(int id) {
        merchandiseDAO.deleteMerchandise(id);
    }

    public List<Merchandise> getToateMerchandise() {
        return merchandiseDAO.readAllMerchandise();
    }
}
