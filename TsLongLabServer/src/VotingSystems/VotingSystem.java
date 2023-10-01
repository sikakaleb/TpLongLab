package VotingSystems;

import Users.Candidates.Candidate;
import Users.Voters.Voter;
import Users.Voters.VoterManager;
import Votes.VoteManager;
import VotingBallots.VotingBallot;
import VotingBallots.VotingBallotManager;
import commonInterfaces.IVotingSystem;

import java.util.*;

public class VotingSystem implements IVotingSystem {
    private VoterManager voterManager;
    private VotingBallotManager ballotManager;
    private VoteManager votesManager;

    public VotingSystem() {
        voterManager = VoterManager.getInstance();
        ballotManager = VotingBallotManager.getInstance();
        votesManager = VoteManager.getInstance();
    }

    // Faire voter tous les électeurs
    public void conductVoting() {
        Set<Voter> allVoters = voterManager.getAllVoters();

        for (Voter voter : allVoters) {
            if (!ballotManager.hasVoted(voter)) {
                VotingBallot ballot = voter.castVotes();
                ballotManager.submitBallot(ballot);
            }
        }
    }

    // Récupérer le total des votes par candidat
    public Map<Candidate, Integer> getTotalVotesByCandidate() {
        return votesManager.getVotesByCandidate();
    }

    // Afficher les candidats par ordre de votes décroissants
    public void displayResults() {
        Map<Candidate, Integer> results = getTotalVotesByCandidate();

        // Trier les résultats
        List<Map.Entry<Candidate, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort(Map.Entry.<Candidate, Integer>comparingByValue().reversed());

        // Afficher les résultats
        for (Map.Entry<Candidate, Integer> entry : sortedResults) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " votes");
        }
    }
}

// Note : J'ai supposé que vous avez une classe VotesManager qui a la méthode getVotesByCandidate.
// Assurez-vous d'intégrer tous les éléments nécessaires pour que cette classe fonctionne.

