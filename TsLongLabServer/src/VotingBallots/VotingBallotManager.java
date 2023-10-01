package VotingBallots;

import Users.Voters.Voter;

import java.util.HashSet;
import java.util.Set;

public class VotingBallotManager {
    // Singleton instance
    private static VotingBallotManager instance;

    // Map pour stocker les bulletins de vote, utilisant le Voter comme clé
    private Set<VotingBallot> ballots;

    // Constructeur privé pour le pattern Singleton
    private VotingBallotManager() {
        ballots = new HashSet<>();
    }

    // Méthode pour obtenir l'instance unique (Singleton pattern)
    public static synchronized VotingBallotManager getInstance() {
        if (instance == null) {
            instance = new VotingBallotManager();
        }
        return instance;
    }

    // Ajouter un bulletin de vote
    public void submitBallot(VotingBallot ballot) {
        ballots.add(ballot);
    }

    // Obtenir le bulletin de vote d'un électeur
    public VotingBallot getBallotForVoter(Voter voter) {
        for (VotingBallot ballot : ballots) {
            if (ballot.getVoter().equals(voter)) {
                return ballot;
            }
        }
        return null; // Retourne null si aucun bulletin de vote n'est trouvé pour cet électeur
    }

    // Vérifier si un électeur a déjà voté
    public boolean hasVoted(Voter voter) {
        for (VotingBallot ballot : ballots) {
            if (ballot.getVoter().equals(voter)) {
                return true;
            }
        }
        return false;
    }

    // D'autres méthodes utiles, comme la suppression d'un bulletin, la récupération de tous les bulletins, etc., peuvent être ajoutées ici.
}
