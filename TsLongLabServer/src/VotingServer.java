import AdminManagement.VotingServiceImpl;
import commonInterfaces.VotingService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class VotingServer {
    public static void main(String[] args) {

        // Définir un gestionnaire de sécurité
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            VotingService service = new VotingServiceImpl();
            LocateRegistry.createRegistry(1099); // Créez un registre RMI sur le port 1099
            Naming.bind("rmi://localhost:1099/voting", service); // Associez votre service à une URL
            System.out.println("Serveur RMI lancé et prêt !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
