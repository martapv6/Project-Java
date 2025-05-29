import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcertDAO {

    private final Connection connection = Db.getConnection();

    // CREATE
    public void insertConcert(Concert c) {
        String sql = "INSERT INTO concert (nume, data, locatie, pretBilet,numarSpectatori) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNume());
            stmt.setString(2, c.getData());
            stmt.setString(3, c.getLocatie());
            stmt.setDouble(4, c.getPret());
            stmt.setInt(5, c.getNumarSpectatori());

            stmt.executeUpdate();
            AuditService.getInstance().logAction("Insert Concert");
            System.out.println("Concert adăugat cu succes.");
            AuditService.getInstance().logAction("Insert Concert");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public Concert readConcert(int id) {
        String sql = "SELECT * FROM concert WHERE idconcert = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Concert c = new Concert(
                        rs.getString("nume"),
                        rs.getString("data"),
                        rs.getString("locatie"),
                        rs.getDouble("pretBilet"),
                        rs.getInt("numarSpectatori")


                        );
                AuditService.getInstance().logAction("Read Concert");
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateConcert(int id, Concert c) {
        String sql = "UPDATE concert SET nume = ?, data = ?, locatie = ?, pretBilet = ? WHERE idconcert = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNume());
            stmt.setString(2, c.getData());
            stmt.setString(3, c.getLocatie());
            stmt.setDouble(4, c.getPret());
            stmt.setInt(5, c.getNumarSpectatori());

            stmt.setInt(6, id);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Update Concert");
            System.out.println("Concert actualizat.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteConcert(int id) {
        String sql = "DELETE FROM concert WHERE idconcert = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Delete Concert");
            System.out.println("Concert șters.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL (optional)
    public List<Concert> readAllConcerts() {
        String sql = "SELECT * FROM concert";
        List<Concert> concerts = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                concerts.add(new Concert(
                        rs.getString("nume"),
                        rs.getString("data"),
                        rs.getString("locatie"),
                        rs.getDouble("pretBilet"),
                        rs.getInt("numarspectatori")

                ));
            }
            AuditService.getInstance().logAction("Read All Concerts");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return concerts;
    }
    public void adaugaArtistLaConcert(int idConcert, int idArtist) {
        String sql = "UPDATE concert SET fk_idartist = ? WHERE idconcert = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idArtist);
            stmt.setInt(2, idConcert);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Update fk_idartist for Concert ID " + idConcert);
            System.out.println("Artistul a fost asociat cu concertul.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Eroare la actualizarea artistului pentru concert.");
        }
    }
    public void adaugaScenaLaConcert(int idConcert, int idScena) {
        String sql = "UPDATE concert SET fk_idscena = ? WHERE idconcert = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idScena);
            stmt.setInt(2, idConcert);
            stmt.executeUpdate();
            AuditService.getInstance().logAction("Update fk_idscena for Concert ID " + idConcert);
            System.out.println("Scena a fost asociată cu concertul.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Eroare la asocierea scenei cu concertul.");
        }
    }


}
