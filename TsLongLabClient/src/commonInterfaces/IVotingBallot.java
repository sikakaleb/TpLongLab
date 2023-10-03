package commonInterfaces;

import java.time.LocalDate;

public interface IVotingBallot extends Comparable<IVotingBallot> {


    // Ajoute un vote pour un candidat
    void addVote(ICandidate candidate, IVote vote);

    // Obtenir le vote pour un candidat spécifique
    IVote getVoteForCandidate(ICandidate candidate);

    // Obtenir le votant associé à ce bulletin de vote
    IVoter getVoter();

    // Obtenir la date d'enregistrement du bulletin de vote
    LocalDate getRegistrationDate();

    void setRegistrationDate();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    public int compareTo(IVotingBallot other);
}
