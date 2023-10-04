package Users.Voters;

import Exceptions.BadCredentialsException;
import Exceptions.HasAlreadyVotedException;
import Users.Candidates.CandidateManager;
import Users.Persons.Person;
import commonInterfaces.IVote;
import commonInterfaces.IVotingBallot;
import VotingBallots.VotingBallot;
import VotingBallots.VotingBallotManager;
import Votes.InvalidVoteException;
import Votes.Vote;
import Votes.VoteManager;
import commonInterfaces.ICandidate;
import commonInterfaces.IVoter;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Voter extends Person implements IVoter {

    private static final long serialVersionUID = 1L;

    private String otp; // OTP field added for OTP authentication
    protected String studentNumber; // numéro d'étudiant comme identifiant unique
    protected String password;

    private Boolean hasVoted = false;

    public Voter(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
    public Voter(String name, String dateOfBirth, String studentNumber, String password) {
        super(name, dateOfBirth);
        this.studentNumber = studentNumber;
        this.password = password;
    }

    @Override
    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public IVotingBallot castVotes() throws BadCredentialsException, HasAlreadyVotedException {
        if (hasVoted) {
            throw new HasAlreadyVotedException("The voter has already cast their votes.");
        }

        if (otp == null || !otp.equals(this.otp)) {
            throw new BadCredentialsException("Invalid OTP provided.");
        }
        setHasVoted();
        IVotingBallot ballot = new VotingBallot((IVoter) this);
        CandidateManager candidatesManager = CandidateManager.getInstance();

        for (ICandidate candidate : candidatesManager.getCandidates()) {
            // Supposons qu'il y ait une méthode pour obtenir un vote pour un candidat.
            // Cela pourrait être une interaction utilisateur ou une logique spécifique.
            IVote vote = getVoteForCandidate(candidate);
            //sauvegarder le vote dans le VoteManager
            VoteManager.getInstance().recordVote(vote);
            ballot.addVote(candidate, vote);
        }

        //sauvegarder le bulletin de vote dans le VotingBallotManager
        ballot.setRegistrationDate();
        VotingBallotManager.getInstance().submitBallot(ballot);
        return ballot;
    }



    @Override
    public IVote getVoteForCandidate(ICandidate candidate) {
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
    public String getName() {
        return super.getName();
    }

    @Override
    public Boolean getHasVoted() {
        return hasVoted;
    }

    @Override
    public void setHasVoted() {
        this.hasVoted = true;
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


    @Override
    public String getDateOfBirth() {
        return super.getDateOfBirth();
    }

    @Override
    public String getOtp() {
        return otp;
    }

    @Override
    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String regenerateOtp() {
        String newOtp = generateOTP();
        this.setOtp(newOtp);
        return newOtp;
    }

    @Override
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // génère un nombre à 6 chiffres
        return String.valueOf(otp);
    }
    @Override
    public boolean validatePassword(String providedPassword) {
        return this.password.equals(providedPassword);
    }
    @Override
    public IVote voteForCandidate(ICandidate candidate) {
        System.out.println("Veuillez entrer votre vote pour " + candidate.getFirstNameLastName() + " (0-3):");
        Scanner scanner = new Scanner(System.in);
        int voteValue = scanner.nextInt();

        while (voteValue < 0 || voteValue > 3) {
            System.out.println("Entrée invalide. Veuillez entrer une valeur entre 0 et 3.");
            voteValue = scanner.nextInt();
        }

        try {
            return new Vote(candidate, voteValue);
        } catch (InvalidVoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
