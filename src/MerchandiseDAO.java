import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseDAO {

    private final Connection connection = Db.getConnection();

    // CREATE
    public void insertMerchandise(Merchandise m) {
        String sql = "INSERT INTO merchandise (produs, pret, editieLimitata, vandut) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, m.getProdus());
            stmt.setDouble(2, m.getPret());
            stmt.setBoolean(3, m.getEditie());
            stmt.setBoolean(4, m.getVandut());
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Insert Merchandise");
            System.out.println("Merchandise adăugat cu succes.");
            AuditService.getInstance().logAction("Insert Merchandise");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public Merchandise readMerchandise(int id) {
        String sql = "SELECT * FROM merchandise WHERE idmerchandise = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Merchandise m = new Merchandise(
                        rs.getString("produs"),
                        rs.getDouble("pret"),
                        rs.getBoolean("editieLimitata"),
                        rs.getBoolean("vandut")
                );
                AuditService.getInstance().logAction("Read Merchandise");
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateMerchandise(int id, Merchandise m) {
        String sql = "UPDATE merchandise SET produs = ?, pret = ?, editieLimitata = ?, vandut = ? WHERE idmerchandise = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, m.getProdus());
            stmt.setDouble(2, m.getPret());
            stmt.setBoolean(3, m.getEditie());
            stmt.setBoolean(4, m.getVandut());
            stmt.setInt(5, id);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Update Merchandise");
            System.out.println("Merchandise actualizat.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteMerchandise(int id) {
        String sql = "DELETE FROM merchandise WHERE idmerchandise = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Delete Merchandise");
            System.out.println("Merchandise șters.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL (opțional)
    public List<Merchandise> readAllMerchandise() {
        String sql = "SELECT * FROM merchandise";
        List<Merchandise> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Merchandise(
                        rs.getString("produs"),
                        rs.getDouble("pret"),
                        rs.getBoolean("editieLimitata"),
                        rs.getBoolean("vandut")
                ));
            }
            AuditService.getInstance().logAction("Read All Merchandise");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
