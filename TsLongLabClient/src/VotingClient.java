import AdminManagement.Referee;
import Votes.Vote;
import VotingBallots.VotingBallot;
import VotingSystems.VotingMaterials;
import VotingSystems.VotingSystem;
import commonInterfaces.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class VotingClient implements ClientCallback {

    @Override
    public void receiveResults(Referee results) {
        System.out.println("Résultats du vote reçus!");
        for (IVote entry : results.getResultMap()) {
            System.out.println(entry.getCandidate().getFirstNameLastName() + ": " + entry.getScore() + " voix");
        }
    }

    public void connectToServer(VotingService service) throws RemoteException {
        // Supposons que vous ayez un stub pour le VotingService appelé `votingService`
        service.registerForResults(this);
    }
    public static void main(String[] args) {
        try {
            // Essai de communication avec le serveur RMI
            System.out.println("Tentative de communication avec le serveur RMI...");

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            VotingService service = (VotingService) registry.lookup("voting");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez entrer votre numéro d'étudiant:");
            String studentNumber = scanner.nextLine();
            System.out.println("Veuillez entrer votre mot de passe:");
            String password = scanner.nextLine();
            VotingMaterials votingMat = service.authentificate(studentNumber, password);
            System.out.println("Votre OTP est: " + votingMat.getOTP());

            List<ICandidate> candidates = service.getCandidates();
            // Afficher un message de succès si la communication est réussie
            System.out.println("Communication réussie avec le serveur RMI!");
            System.out.println("Liste des candidats récupérés : ");

            for (ICandidate candidate : candidates) {
               System.out.println(candidate.toString()); // Assurez-vous que la méthode toString() est bien définie dans l'interface ICandidate ou la classe qui l'implémente
            }

            if (votingMat != null) {
                // Demander à l'utilisateur de fournir un vote pour chaque candidat
                IVotingBallot ballot = new VotingBallot(votingMat.getVoter());
                for (ICandidate candidate : votingMat.getCandidates()) {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Le Pitch de " + candidate.getFirstNameLastName() + " (:)"+ candidate.getPitch());
                    System.out.println("Veuillez entrer votre vote pour " + candidate.getFirstNameLastName() + " (0-3):");

                    int voteValue = scanner.nextInt();


                    while (voteValue < 0 || voteValue > 3) {

                        System.out.println("Entrée invalide. Veuillez entrer une valeur entre 0 et 3.");
                        voteValue = scanner.nextInt();
                    }
                    ballot.addVote(new Vote(candidate,voteValue));
                }

                Set<IVote> listVotes = new HashSet<>();
                listVotes = ballot.getVotes();
                service.castVotes(ballot,votingMat.getVoter());
                System.out.println("Voulez vous voir le cumul de votes a ce stades? (y/N).");
                String voteValue = scanner.nextLine();
                if(voteValue.equals("y")){
                    service.getVotingSystem().displayResults();
                }


                // Terminer la session de vote
                service.endVotingSession();
                VotingSystem votingService =  service.getVotingSystem();
                // Demander les résultats
                Referee results = service.getResults();
                if (results != null) {
                }
            } else {
                System.out.println("Le vote est terminé. Merci de votre participation.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la communication avec le serveur RMI.");
            e.printStackTrace();
        }
    }
}
