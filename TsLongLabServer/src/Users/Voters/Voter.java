package Users.Voters;

import Users.Candidates.Candidate;
import Users.Candidates.CandidateManager;
import Users.Persons.Person;
import VotingBallots.VotingBallot;
import VotingBallots.VotingBallotManager;
import Votes.InvalidVoteException;
import Votes.Vote;
import Votes.VoteManager;

import java.util.Objects;

public class Voter extends Person {
    protected String studentNumber; // numéro d'étudiant comme identifiant unique
    protected String password;

    public Voter(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
    public Voter(String name, String dateOfBirth, String studentNumber, String password) {
        super(name, dateOfBirth);
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public VotingBallot castVotes() {
        VotingBallot ballot = new VotingBallot(this);
        CandidateManager candidatesManager = CandidateManager.getInstance();

        for (Candidate candidate : candidatesManager.getCandidates()) {
            // Supposons qu'il y ait une méthode pour obtenir un vote pour un candidat.
            // Cela pourrait être une interaction utilisateur ou une logique spécifique.
            Vote vote = getVoteForCandidate(candidate);
            //sauvegarder le vote dans le VoteManager
            VoteManager.getInstance().recordVote(vote);
            ballot.addVote(candidate, vote);
        }

        //sauvegarder le bulletin de vote dans le VotingBallotManager
        VotingBallotManager.getInstance().submitBallot(ballot);
        return ballot;
    }

    private Vote getVoteForCandidate(Candidate candidate) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter voter)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getStudentNumber(), voter.getStudentNumber()) && Objects.equals(getPassword(), voter.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentNumber(), getPassword());
    }
}
