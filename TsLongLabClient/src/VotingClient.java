

import commonInterfaces.ICandidate;
import commonInterfaces.VotingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class VotingClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            VotingService service = (VotingService) registry.lookup("voting");

            List<ICandidate> candidates = service.getCandidates();
            // Affichez la liste des candidats ou effectuez d'autres actions...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
