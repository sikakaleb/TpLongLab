package VotingBallots;

import commonInterfaces.ICandidate;
import commonInterfaces.IVote;
import commonInterfaces.IVoter;
import commonInterfaces.IVotingBallot;

import java.time.LocalDate;
import java.util.*;

public class VotingBallot implements IVotingBallot {
    private IVoter voter;
    private Set<IVote> votes; // Une carte associant chaque candidat à un vote

    private LocalDate registrationDate = LocalDate.now(); // La date d'enregistrement du bulletin de vote


    public VotingBallot(IVoter voter) {
        this.voter = voter;
        this.votes = new HashSet<>();
    }

    // Ajoute un vote pour un candidat
    @Override
    public void addVote(IVote vote) {
        votes.add(vote);
    }

    // Obtenir le vote pour un candidat spécifique
    @Override
    public IVote getVoteForCandidate(ICandidate candidate) {
        for (IVote vote : votes) {
            if (vote.getCandidate().equals(candidate)) {
                return vote;
            }
        }
        return null;
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
