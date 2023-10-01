package Votes;

import Users.Candidates.Candidate;

import java.util.HashMap;
import java.util.Map;

public class VoteManager {
    private static VoteManager instance;

    // Au lieu d'une liste de votes, on garde un Map pour enregistrer le score total pour chaque candidat
    private Map<Candidate, Integer> voteSum;

    private VoteManager() {
        voteSum = new HashMap<>();
    }

    public static synchronized VoteManager getInstance() {
        if (instance == null) {
            instance = new VoteManager();
        }
        return instance;
    }

    // Enregistrer un vote
    public void recordVote(Vote vote) {
        voteSum.put(vote.getCandidate(), voteSum.getOrDefault(vote.getCandidate(), 0) + vote.getScore());
    }

    // Retourner la somme des votes par candidat
    public Map<Candidate, Integer> getVotesByCandidate() {
        // Retourne une copie pour éviter des modifications externes
        return new HashMap<>(voteSum);
    }
}
