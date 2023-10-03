package commonInterfaces;

import Exceptions.BadCredentialsException;
import Exceptions.HasAlreadyVotedException;

import java.io.Serializable;

public interface IVoter extends Serializable {
    String getStudentNumber();

    void setStudentNumber(String studentNumber);

    String getPassword();

    void setPassword(String password);

    IVotingBallot castVotes() throws BadCredentialsException, HasAlreadyVotedException;

    IVote getVoteForCandidate(ICandidate candidate);

    String getName();

    Boolean getHasVoted();

    void setHasVoted();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    String getDateOfBirth();

    String getOtp();

    void setOtp(String otp);

    String regenerateOtp();

    String generateOTP();

    boolean validatePassword(String providedPassword);
}
