import java.util.List;

public class FoodCourtService {
    private static FoodCourtService instance;
    private final FoodCourtDAO foodCourtDAO;

    private FoodCourtService() {
        this.foodCourtDAO = new FoodCourtDAO();
    }

    public static FoodCourtService getInstance() {
        if (instance == null) {
            instance = new FoodCourtService();
        }
        return instance;
    }

    public void adaugaFoodCourt(FoodCourt fc) {
        foodCourtDAO.insertFoodCourt(fc);
    }

    public FoodCourt getFoodCourt(int id) {
        return foodCourtDAO.readFoodCourt(id);
    }

    public void actualizeazaFoodCourt(int id, FoodCourt fc) {
        foodCourtDAO.updateFoodCourt(id, fc);
    }

    public void stergeFoodCourt(int id) {
        foodCourtDAO.deleteFoodCourt(id);
    }
}
