package Users.commonInterfaces;

import java.io.Serializable;

public interface IVoter extends Serializable {
    String getStudentNumber();

    void setStudentNumber(String studentNumber);

    String getPassword();

    void setPassword(String password);

    IVotingBallot castVotes();

    Boolean getHasVoted();

    void setHasVoted();

    IVote getVoteForCandidate(ICandidate candidate);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
