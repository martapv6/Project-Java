import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FoodCourtDAO {

    private final Connection connection = Db.getConnection();

    // CREATE
    public void insertFoodCourt(FoodCourt fc) {
        String sql = "INSERT INTO foodcourt (nume, capacitate) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fc.getNume());
            stmt.setInt(2, fc.getCapacitate());
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Insert FoodCourt");
            System.out.println("FoodCourt adăugat cu succes.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (după id)
    public FoodCourt readFoodCourt(int id) {
        String sql = "SELECT * FROM foodcourt WHERE idfoodCourt = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FoodCourt fc = new FoodCourt(
                        rs.getString("nume"),
                        rs.getInt("capacitate"),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
                AuditService.getInstance().logAction("Read FoodCourt");
                return fc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE (după id)
    public void updateFoodCourt(int id, FoodCourt fc) {
        String sql = "UPDATE foodcourt SET nume = ?, capacitate = ? WHERE idfoodCourt = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fc.getNume());
            stmt.setInt(2, fc.getCapacitate());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Update FoodCourt");
            System.out.println("FoodCourt actualizat.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteFoodCourt(int id) {
        String sql = "DELETE FROM foodcourt WHERE idfoodCourt = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Delete FoodCourt");
            System.out.println("FoodCourt șters.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
