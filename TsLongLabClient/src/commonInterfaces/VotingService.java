package commonInterfaces;
import Exceptions.BadCredentialsException;
import VotingSystems.VotingMaterials;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface VotingService extends Remote {
    List<ICandidate> getCandidates() throws RemoteException;

    /**
     * Demande les matériaux de vote après validation du OTP fourni.
     * @param otp Le mot de passe à usage unique fourni par l'électeur.
     * @return Matériaux de vote si le OTP est valide.
     * @throws RemoteException Si une erreur liée à RMI se produit.
     * @throws BadCredentialsException Si le OTP fourni est invalide.
     */
    VotingMaterials requestVotingMaterials(String otp) throws RemoteException, BadCredentialsException;

}
