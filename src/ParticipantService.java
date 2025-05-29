public class ParticipantService {
    private static ParticipantService instance;
    private final ParticipantDAO participantDAO = new ParticipantDAO();
    private final BiletDAO biletDAO = new BiletDAO();

    private ParticipantService() {}

    public static ParticipantService getInstance() {
        if (instance == null) {
            instance = new ParticipantService();
        }
        return instance;
    }

    public void adaugaParticipant(Participant p) {
        int idBilet = biletDAO.insertBilet(p.getBilet());
        if (idBilet != -1) {
            participantDAO.insertParticipant(p);
            AuditService.getInstance().logAction("Insert Participant");
        } else {
            System.out.println("Eroare: biletul nu a fost creat!");
        }
    }

    public Participant getParticipant(int id) {
        return participantDAO.readParticipant(id);
    }

    public void updateParticipant(int id, Participant p) {
        participantDAO.updateParticipant(id, p);
        AuditService.getInstance().logAction("Update Participant");
    }

    public void deleteParticipant(int id) {
        participantDAO.deleteParticipant(id);
        AuditService.getInstance().logAction("Delete Participant");
    }
}
