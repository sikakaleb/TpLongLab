package Votes;

import commonInterfaces.ICandidate;
import commonInterfaces.IVote;

import java.util.Objects;

public class Vote implements IVote {
    private ICandidate candidate;
    private int score;

    public Vote(ICandidate candidate, int score) throws InvalidVoteException {
        if (score < 0 || score > 3) {
            throw new InvalidVoteException("Score must be between 0 and 3");
        }
        this.candidate = candidate;
        this.score = score;
    }

    @Override
    public ICandidate getCandidate() {
        return candidate;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote vote)) return false;
        return Objects.equals(getCandidate(), vote.getCandidate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCandidate());
    }

}
