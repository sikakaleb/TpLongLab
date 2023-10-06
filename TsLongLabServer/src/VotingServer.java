import AdminManagement.AdminApp;
import AdminManagement.VotingServiceImpl;
import commonInterfaces.ICandidate;
import commonInterfaces.VotingService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.List;

public class VotingServer {
    public static void main(String[] args) {
        try {
            VotingService service = new VotingServiceImpl();
            LocateRegistry.createRegistry(1099); // Créez un registre RMI sur le port 1099
            Naming.bind("rmi://localhost:1099/voting", service); // Associez votre service à une URL
            System.out.println("Serveur RMI lancé et prêt !");
            while (true) {
                if (!service.getVotingSystem().isVotingOpen()) {
                    service.sendResultsToAllClients();
                    break;
                }
                // Vérifiez toutes les minutes.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
