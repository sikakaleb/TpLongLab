package commonInterfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface IVotingBallot extends Comparable<IVotingBallot> , Serializable {


    // Ajoute un vote pour un candidat
    void addVote(IVote vote);

    // Obtenir le vote pour un candidat spécifique
    IVote getVoteForCandidate(ICandidate candidate);

    // Obtenir le votant associé à ce bulletin de vote
    IVoter getVoter();

    // Obtenir la date d'enregistrement du bulletin de vote
    LocalDate getRegistrationDate();

    void setRegistrationDate();

    Set<IVote> getVotes();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    public int compareTo(IVotingBallot other);

}
