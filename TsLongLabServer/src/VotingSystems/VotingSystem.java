package VotingSystems;

import AdminManagement.AdminApp;
import Exceptions.BadCredentialsException;
import Exceptions.HasAlreadyVotedException;
import Users.Candidates.CandidateManager;
import Users.Voters.Voter;
import Users.Voters.VoterManager;
import Votes.VoteManager;
import commonInterfaces.IVoter;
import commonInterfaces.IVotingBallot;
import VotingBallots.VotingBallotManager;
import commonInterfaces.ICandidate;


import java.util.*;

public class VotingSystem {
    private VoterManager voterManager;
    private VotingBallotManager ballotManager;
    private VoteManager votesManager;
    private boolean votingEnded;

    public VotingSystem() {
        voterManager = VoterManager.getInstance();
        ballotManager = VotingBallotManager.getInstance();
        votesManager = VoteManager.getInstance();
        votingEnded = false;
    }

    // Faire voter tous les électeurs
    public void conductVoting() throws HasAlreadyVotedException, BadCredentialsException {
        if(votingEnded) {
            throw new IllegalStateException("Voting has already ended!");
        }

        Set<IVoter> allVoters = voterManager.getAllVoters();

        for (IVoter voter : allVoters) {
            if (!ballotManager.hasVoted(voter)) {
                IVotingBallot ballot = voter.castVotes();
                ballotManager.submitBallot(ballot);
            }
        }
    }

    public void endVoting() {
        votingEnded = true;
        displayResults();
    }

    // Récupérer le total des votes par candidat
    public Map<ICandidate, Integer> getTotalVotesByCandidate() {
        return votesManager.getVotesByCandidate();
    }

    // Afficher les candidats par ordre de votes décroissants
    public void displayResults() {
        if(!votingEnded) {
            throw new IllegalStateException("Voting has not ended yet!");
        }

        Map<ICandidate, Integer> results = getTotalVotesByCandidate();

        // Trier les résultats
        List<Map.Entry<ICandidate, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort(Map.Entry.<ICandidate, Integer>comparingByValue().reversed());

        // Afficher les résultats
        for (Map.Entry<ICandidate, Integer> entry : sortedResults) {
            System.out.println(entry.getKey().getFirstNameLastName() + ": " + entry.getValue() + " votes");
        }
    }

    public VotingMaterials getVotingMaterialsForVoter(IVoter voter) {
        // Récupérer la liste des candidats et d'autres informations si nécessaire
        List<ICandidate> candidates = CandidateManager.getInstance().getCandidates().stream().toList();
        return new VotingMaterials(candidates,voter.getOtp());
    }

    public void setOtpForVoter(IVoter voter, String otp) {
        voterManager.removeVoter((Voter) voter);
        if(voter.getOtp()==null){
            voter.regenerateOtp();
        }
        voterManager.registerVoter(voter);
        voterManager.saveDataToFile();
    }
}


