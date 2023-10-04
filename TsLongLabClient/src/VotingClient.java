import VotingSystems.VotingMaterials;
import commonInterfaces.ICandidate;
import commonInterfaces.VotingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class VotingClient {
    public static void main(String[] args) {
        try {
            // Essai de communication avec le serveur RMI
            System.out.println("Tentative de communication avec le serveur RMI...");

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            VotingService service = (VotingService) registry.lookup("voting");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez entrer votre numéro d'étudiant:");
            String studentNumber = "AlicePass";
            System.out.println("Veuillez entrer votre mot de passe:");
            String password = "password1";
            VotingMaterials otp = service.authentificate(studentNumber, password);
            System.out.println("Votre OTP est: " + otp.getOTP());

            List<ICandidate> candidates = service.getCandidates();
            // Afficher un message de succès si la communication est réussie
            System.out.println("Communication réussie avec le serveur RMI!");
            System.out.println("Liste des candidats récupérés : ");

            for (ICandidate candidate : candidates) {
               System.out.println(candidate.toString()); // Assurez-vous que la méthode toString() est bien définie dans l'interface ICandidate ou la classe qui l'implémente
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la communication avec le serveur RMI.");
            e.printStackTrace();
        }
    }
}
