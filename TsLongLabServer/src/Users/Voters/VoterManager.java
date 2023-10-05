package Users.Voters;

import Exceptions.BadCredentialsException;
import commonInterfaces.IVoter;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class VoterManager implements Serializable {
    private static final String VOTERS_FILE = "votersData.csv";
    // Singleton instance
    private static VoterManager instance;

    // Set pour stocker les électeurs
    private Set<IVoter> voters;

    // Constructeur privé pour le pattern Singleton
    private VoterManager() {
        voters = new HashSet<>();
    }

    // Méthode pour obtenir l'instance unique (Singleton pattern)
    public static synchronized VoterManager getInstance() {
        if (instance == null) {
            instance = new VoterManager();
        }
        return instance;
    }

    // Ajouter un électeur
    public void registerVoter(IVoter voter) {
        voters.add(voter);
    }

    // Vérifier si un électeur est déjà enregistré
    public boolean isRegistered(Voter voter) {
        return voters.contains(voter);
    }

    // Supprimer un électeur (par exemple, pour des raisons d'administration)
    public void removeVoter(Voter voter) {
        voters.remove(voter);
    }

    // Obtenir tous les électeurs (selon les besoins, pour des opérations administratives ou des statistiques)
    public Set<IVoter> getAllVoters() {
        return new HashSet<>(voters);  // Retourne une copie pour prévenir les modifications externes
    }

    public String requestVotingMaterial(String studentNumber, String providedPassword) throws BadCredentialsException {
        IVoter voter = findVoterByStudentNumber(studentNumber);
        if (voter.validatePassword(providedPassword) && !voter.getHasVoted()) {
            voter.regenerateOtp();
            System.out.println("OTP: " + voter.getHasVoted());
            voter.setHasVoted();
            return voter.getOtp();
        } else if(voter.getHasVoted()){
            throw new BadCredentialsException("You have already voted.");
        }else {
            throw new BadCredentialsException("Invalid credentials provided.");
        }
    }

    public IVoter findVoterByStudentNumber(String studentNumber) {
        for (IVoter voter : voters) {
            if (voter.getStudentNumber().equals(studentNumber)) {
                return voter; // retourne l'électeur trouvé
            }
        }
        return null; // retourne null si aucun électeur n'a été trouvé
    }

    public void saveDataToFile() {
        // Utilisez VoterManager pour obtenir la liste des votants
        Set<IVoter> allVoters = VoterManager.getInstance().getAllVoters();

        // Supprimez le fichier existant pour éviter les doublons
        new File(VOTERS_FILE).delete();

        // Sauvegarder les votants
        try (BufferedWriter bwVoters = new BufferedWriter(new FileWriter(VOTERS_FILE))) {
            for (IVoter voter : allVoters) {
                String otp = voter.getOtp();
                if (otp == null) {
                    otp = "NA"; // "NA" pour "Not Available" ou vous pouvez choisir une autre chaîne de remplacement
                }
                bwVoters.write(String.format("%s,%s,%s,%s,%s%n",
                        voter.getName(), voter.getDateOfBirth(), voter.getStudentNumber(), voter.getPassword(), otp));
            }
            System.out.println("Votants sauvegardés avec succès!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean allVotersVoted() {
        for (IVoter voter : voters) {
            if (!voter.getHasVoted()) { // Si un des votants n'a pas voté, retournez false
                return false;
            }
        }
        return true; // Tous les votants ont voté
    }

}
