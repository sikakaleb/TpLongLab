package AdminManagement;

import Exceptions.BadCredentialsException;
import Exceptions.HasAlreadyVotedException;
import Users.Candidates.CandidateManager;
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
import java.util.Scanner;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    // Charger des données sérialisées lors de l'initialisation
    private AdminApp adminApp;

    private Scanner scanner = new Scanner(System.in);
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
        return CandidateManager.getInstance().getCandidates().stream().toList();
    }

    @Override
    public VotingMaterials requestVotingMaterials(IVoter voter) throws RemoteException, BadCredentialsException, HasAlreadyVotedException {
        // Vérifiez l', trouvez l'électeur correspondant, et retournez les matériaux de vote// Trouvez l'électeur basé sur l'OTP
        return votingSystem.getVotingMaterialsForVoter(voter);
    }
    @Override
    public VotingMaterials authentificate(String username, String password) throws RemoteException, BadCredentialsException, HasAlreadyVotedException {
        String Otp=adminVoterApp.authentification(username, password);
        System.out.println("****************************************************************************");
        System.out.println("*************************"+Otp+"**************************************");

        if(Otp==null){
            throw new BadCredentialsException("Bad Credentials");
        }
        else{
            IVoter voter = VoterManager.getInstance().findVoterByStudentNumber(username);
            votingSystem.setOtpForVoter(voter, Otp);
            voter.setOtp(Otp);
            return requestVotingMaterials(voter);

        }

    }


}
