import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAO {

    private final Connection connection = Db.getConnection();
    public void insertParticipant(Participant p) {
        BiletDAO biletDAO = new BiletDAO();
        int idBilet = biletDAO.insertBilet(p.getBilet());

        if (idBilet == -1) {
            System.out.println("Eroare la cumpărarea biletului. Participantul nu a fost adăugat.");
            return;
        }

        String sqlPersoana = "INSERT INTO persoana (nume, varsta, email, alcool) VALUES (?, ?, ?, ?)";
        String sqlParticipant = "INSERT INTO participant (fk_persoana, fk_idbilet, preferaMerch, preferaFood, feedback) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmtPersoana = connection.prepareStatement(sqlPersoana, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtPersoana.setString(1, p.getNume());
            stmtPersoana.setInt(2, p.getVarsta());
            stmtPersoana.setString(3, p.getEmail());
            stmtPersoana.setBoolean(4, p.isAlcool());
            stmtPersoana.executeUpdate();

            ResultSet rs = stmtPersoana.getGeneratedKeys();
            if (rs.next()) {
                int idPersoana = rs.getInt(1);

                try (PreparedStatement stmtParticipant = connection.prepareStatement(sqlParticipant)) {
                    stmtParticipant.setInt(1, idPersoana);
                    stmtParticipant.setInt(2, idBilet);
                    stmtParticipant.setBoolean(3, p.isPreferaMerch());
                    stmtParticipant.setBoolean(4, p.isPreferaFood());
                    stmtParticipant.setString(5, p.getFeedback());
                    stmtParticipant.executeUpdate();
                }
            }

            AuditService.getInstance().logAction("Insert Participant cu bilet");
            System.out.println("Participant și bilet adăugați corect.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public Participant readParticipant(int idParticipant) {
        String sql = "SELECT p.idparticipant, p.preferaMerch, p.preferaFood, p.feedback, " +
                "per.idpersoana, per.nume, per.varsta, per.email, per.alcool " +
                "FROM participant p " +
                "JOIN persoana per ON p.fk_persoana = per.idpersoana " +
                "WHERE p.idparticipant = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idParticipant);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Participant participant = new Participant(
                        rs.getString("nume"),
                        rs.getInt("varsta"),
                        rs.getString("email"),
                        rs.getBoolean("alcool"),
                        null, // Bilet încă neluat
                        rs.getBoolean("preferaMerch"),
                        rs.getBoolean("preferaFood"),
                        rs.getString("feedback")
                );
                AuditService.getInstance().logAction("Read Participant");
                return participant;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE (participant + persoana)
    public void updateParticipant(int idParticipant, Participant p) {
        // UPDATE participant
        String sqlParticipant = "UPDATE participant SET preferaMerch = ?, preferaFood = ?, feedback = ? WHERE idparticipant = ?";

        // UPDATE persoana (obținem fk_persoana din participant)
        String sqlPersoanaUpdate = "UPDATE persoana SET nume = ?, varsta = ?, email = ?, alcool = ? " +
                "WHERE idpersoana = (SELECT fk_persoana FROM participant WHERE idparticipant = ?)";

        try (
                PreparedStatement stmtParticipant = connection.prepareStatement(sqlParticipant);
                PreparedStatement stmtPersoana = connection.prepareStatement(sqlPersoanaUpdate)
        ) {
            // Update participant
            stmtParticipant.setBoolean(1, p.isPreferaMerch());
            stmtParticipant.setBoolean(2, p.isPreferaFood());
            stmtParticipant.setString(3, p.getFeedback());
            stmtParticipant.setInt(4, idParticipant);
            stmtParticipant.executeUpdate();

            // Update persoana
            stmtPersoana.setString(1, p.getNume());
            stmtPersoana.setInt(2, p.getVarsta());
            stmtPersoana.setString(3, p.getEmail());
            stmtPersoana.setBoolean(4, p.isAlcool());
            stmtPersoana.setInt(5, idParticipant);
            stmtPersoana.executeUpdate();

            AuditService.getInstance().logAction("Update Participant");
            System.out.println("Participant și Persoana actualizate.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE (se șterge doar din participant, persoana rămâne sau se poate face CASCADE dacă vrei)
    public void deleteParticipant(int idParticipant) {
        String sql = "DELETE FROM participant WHERE idparticipant = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idParticipant);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Delete Participant");
            System.out.println("Participant șters.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
