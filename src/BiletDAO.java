import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BiletDAO {

    private final Connection connection = Db.getConnection();

    // Insert bilet și returnează id-ul generat
    public int insertBilet(Bilet b) {
        String sql = "INSERT INTO bilet (tip, pret) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, b.getTip());
            stmt.setDouble(2, b.getPret());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                AuditService.getInstance().logAction("Insert Bilet");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Read bilet după id
    public Bilet readBilet(int id) {
        String sql = "SELECT * FROM bilet WHERE idbilet = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Bilet(
                        rs.getInt("pret"),
                        rs.getString("tip")

                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
