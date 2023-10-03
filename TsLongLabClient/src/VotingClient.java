
import Users.commonInterfaces.ICandidate;
import Users.commonInterfaces.VotingService;

import java.rmi.Naming;
import java.util.List;

public class VotingClient {
    public static void main(String[] args) {
        try {
            VotingService service = (VotingService) Naming.lookup("rmi://localhost:1099/voting");
            List<ICandidate> candidates = service.getCandidates();
            // Affichez la liste des candidats ou effectuez d'autres actions...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
