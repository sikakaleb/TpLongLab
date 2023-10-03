package AdminManagement;

import Exceptions.BadCredentialsException;
import Users.Candidates.Candidate;
import Users.Candidates.CandidateManager;
import Users.Voters.Voter;
import Users.Voters.VoterManager;
import Votes.VoteManager;
import VotingBallots.VotingBallotManager;
import VotingSystems.VotingMaterials;
import VotingSystems.VotingSystem;
import commonInterfaces.ICandidate;
import commonInterfaces.IVoter;
import commonInterfaces.VotingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    // Charger des données sérialisées lors de l'initialisation
    private AdminApp adminApp;

    private AdminVoterApp adminVoterApp;
    private VotingSystem votingSystem;

    public VotingServiceImpl() throws RemoteException {
        super();
        adminApp = new AdminApp(); // Cette ligne chargera vos données lors de l'initialisation des candidats
        adminVoterApp = new AdminVoterApp(); // Cette ligne chargera vos données lors de l'initialisation des électeurs
        votingSystem = new VotingSystem();

        // Initialisez vos managers
        CandidateManager.getInstance();
        VoterManager.getInstance();
        VotingBallotManager.getInstance();
        VoteManager.getInstance();
    }


    @Override
    public List<ICandidate> getCandidates() throws RemoteException {
        return adminApp.getCandidates();
    }

    @Override
    public VotingMaterials requestVotingMaterials(String otp) throws RemoteException, BadCredentialsException {
        // Vérifiez l', trouvez l'électeur correspondant, et retournez les matériaux de vote
        IVoter voter = VoterManager.getInstance().findVoterByStudentNumber(otp); // Trouvez l'électeur basé sur l'OTP
        return votingSystem.getVotingMaterialsForVoter(voter);
    }

}
