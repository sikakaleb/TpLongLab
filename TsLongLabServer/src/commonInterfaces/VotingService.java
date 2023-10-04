package commonInterfaces;
import AdminManagement.Referee;
import Exceptions.BadCredentialsException;
import Exceptions.HasAlreadyVotedException;
import Votes.InvalidVoteException;
import VotingSystems.VotingMaterials;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface VotingService extends Remote {
    List<ICandidate> getCandidates() throws RemoteException;

    /**
     * Demande les matériaux de vote après validation du OTP fourni.
     * @param voter Le mot de passe à usage unique fourni par l'électeur.
     * @return Matériaux de vote si le OTP est valide.
     * @throws RemoteException Si une erreur liée à RMI se produit.
     * @throws BadCredentialsException Si le OTP fourni est invalide.
     */

    VotingMaterials requestVotingMaterials(IVoter voter) throws RemoteException, BadCredentialsException, HasAlreadyVotedException;

    VotingMaterials authentificate(String username, String password) throws RemoteException, BadCredentialsException, HasAlreadyVotedException;

    void castVotes(IVotingBallot ballot, List<IVote> listVotes, IVoter Voter) throws BadCredentialsException, HasAlreadyVotedException,RemoteException;

    Referee getResults() throws RemoteException,InvalidVoteException;
}
