package Votes;

import Users.Candidates.Candidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteManager {

    private static VoteManager instance;
    private List<Vote> votes;
    public VoteManager() {
        votes = new ArrayList<>();
    }

    public static synchronized VoteManager getInstance() {
        if (instance == null) {
            instance = new VoteManager();
        }
        return instance;
    }

    // Enregistrer un vote
    public void recordVote(Vote vote) {
        votes.add(vote);
    }


    // Retourner la somme des votes par candidat
    public Map<Candidate, Integer> getVotesByCandidate() {
        Map<Candidate, Integer> voteSum = new HashMap<>();

        for (Vote vote : votes) {
            voteSum.put(vote.getCandidate(), voteSum.getOrDefault(vote.getCandidate(), 0) + vote.getScore());
        }

        return voteSum;
    }


}
