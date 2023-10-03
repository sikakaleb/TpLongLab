package commonInterfaces;

import Users.Candidates.Candidate;
import commonInterfaces.ICandidate;

public interface IVote {
    ICandidate getCandidate();

    int getScore();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
