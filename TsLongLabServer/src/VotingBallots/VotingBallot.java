package VotingBallots;

import Users.Candidates.Candidate;
import Users.Voters.Voter;
import Votes.Vote;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VotingBallot {
    private Voter voter;
    private Map<Candidate, Vote> votes; // Une carte associant chaque candidat à un vote

    public VotingBallot(Voter voter) {
        this.voter = voter;
        this.votes = new HashMap<>();
    }

    // Ajoute un vote pour un candidat
    public void addVote(Candidate candidate, Vote vote) {
        votes.put(candidate, vote);
    }

    // Obtenir le vote pour un candidat spécifique
    public Vote getVoteForCandidate(Candidate candidate) {
        return votes.get(candidate);
    }

    // Obtenir le votant associé à ce bulletin de vote
    public Voter getVoter() {
        return voter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VotingBallot that)) return false;
        return Objects.equals(getVoter(), that.getVoter()) && Objects.equals(votes, that.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter(), votes);
    }
}
