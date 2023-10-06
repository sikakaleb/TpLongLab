package AdminManagement;

import Exceptions.BadCredentialsException;
import Exceptions.HasAlreadyVotedException;
import Users.Candidates.CandidateManager;
import Users.Voters.VoterManager;
import Votes.InvalidVoteException;
import Votes.VoteManager;
import VotingBallots.VotingBallotManager;
import VotingSystems.VotingMaterials;
import VotingSystems.VotingSystem;
import commonInterfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    // Charger des données sérialisées lors de l'initialisation

    private List<ClientCallback> clientCallbacks = new ArrayList<>();
    private AdminApp adminApp;

    private Scanner scanner = new Scanner(System.in);
    private AdminVoterApp adminVoterApp;
    public VotingSystem votingSystem;

    public VotingServiceImpl() throws RemoteException {
        super();
        votingSystem = VotingSystem.getInstance();
        adminApp = new AdminApp(); // Cette ligne chargera vos données lors de l'initialisation des candidats
        adminVoterApp = new AdminVoterApp(); // Cette ligne chargera vos données lors de l'initialisation des électeurs


        // Initialisez vos managers
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
    @Override
    public void castVotes(IVotingBallot ballot, IVoter Voter) throws BadCredentialsException, HasAlreadyVotedException {
        /*if (voter.getHasVoted()) {
            throw new HasAlreadyVotedException("The voter has already cast their votes.");
        }

        if (voter.getOtp() == null) {
            throw new BadCredentialsException("Invalid OTP provided.");
        }
        voter.setHasVoted();*/

        for (IVote vote : ballot.getVotes()) {

            VoteManager.getInstance().recordVote(vote);
            //ballot.addVote(candidate, vote);
        }
        //sauvegarder le bulletin de vote dans le VotingBallotManager
        ballot.setRegistrationDate();
        VotingBallotManager.getInstance().submitBallot(ballot);
    }



    @Override
    public Referee getResults() throws InvalidVoteException {
        if(votingSystem.isVotingOpen()){
            System.out.println("Voting is closed");
        }
        else{
            System.out.println("Voting is "+votingSystem.isVotingOpen());
        }
        return new Referee(votingSystem.getResults());
    }

    @Override
    public VotingSystem getVotingSystem() throws RemoteException {
        return votingSystem;
    }

    @Override
    public void endVotingSession() {
        // Sauvegardez les données sérialisées
        votingSystem.endVoting();
    }

    @Override
    public void registerForResults(ClientCallback callback) throws RemoteException {
        clientCallbacks.add(callback);
    }

    @Override
    public void sendResultsToAllClients() throws InvalidVoteException, RemoteException {
        Referee results = this.getResults();
        for (ClientCallback callback : clientCallbacks) {
            try {
                callback.receiveResults(results);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}
