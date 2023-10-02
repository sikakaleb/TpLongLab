package commonInterfaces;

import Users.Candidates.Candidate;
import Votes.InvalidVoteException;
import Votes.Vote;
import VotingBallots.VotingBallot;

import java.io.Serializable;

public interface IVoter extends Serializable {
    String getStudentNumber();

    void setStudentNumber(String studentNumber);

    String getPassword();

    void setPassword(String password);

    VotingBallot castVotes();

    Boolean getHasVoted();

    void setHasVoted();

    default Vote getVoteForCandidate(Candidate candidate) {
        // Cette méthode doit être implémentée.
        // Pour le moment, supposons qu'elle renvoie un vote aléatoire entre 0 et 3.
        int randomScore = (int) (Math.random() * 4); // un score aléatoire entre 0 et 3
        try {
            return new Vote(candidate, randomScore);
        } catch (InvalidVoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
