package Votes;

import Users.Candidates.Candidate;

import java.util.Objects;

public class Vote {
    private Candidate candidate;
    private int score;

    public Vote(Candidate candidate, int score) throws InvalidVoteException {
        if (score < 0 || score > 3) {
            throw new InvalidVoteException("Score must be between 0 and 3");
        }
        this.candidate = candidate;
        this.score = score;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote vote)) return false;
        return getScore() == vote.getScore() && Objects.equals(getCandidate(), vote.getCandidate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCandidate(), getScore());
    }


}
