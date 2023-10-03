package VotingBallots;

import commonInterfaces.ICandidate;
import commonInterfaces.IVote;
import commonInterfaces.IVoter;
import commonInterfaces.IVotingBallot;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VotingBallot implements IVotingBallot {
    private IVoter voter;
    private Map<ICandidate, IVote> votes; // Une carte associant chaque candidat à un vote

    private LocalDate registrationDate = LocalDate.now(); // La date d'enregistrement du bulletin de vote


    public VotingBallot(IVoter voter) {
        this.voter = voter;
        this.votes = new HashMap<>();
    }

    // Ajoute un vote pour un candidat
    @Override
    public void addVote(ICandidate candidate, IVote vote) {
        votes.put(candidate, vote);
    }

    // Obtenir le vote pour un candidat spécifique
    @Override
    public IVote getVoteForCandidate(ICandidate candidate) {
        return votes.get(candidate);
    }

    // Obtenir le votant associé à ce bulletin de vote
    @Override
    public IVoter getVoter() {
        return voter;
    }

    // Obtenir la date d'enregistrement du bulletin de vote
    @Override
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public void setRegistrationDate() {
        this.registrationDate = LocalDate.now(); // initialise la date d'enregistrement à la date actuelle
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VotingBallot that)) return false;
        return Objects.equals(getVoter(), that.getVoter()) && Objects.equals(votes, that.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter(), votes, registrationDate);
    }

    @Override
    public int compareTo(IVotingBallot other) {
        return this.registrationDate.compareTo(other.getRegistrationDate());
    }
}
