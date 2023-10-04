package VotingSystems;

import commonInterfaces.ICandidate;
import commonInterfaces.IVoter;

import java.io.Serializable;
import java.util.List;

public class VotingMaterials implements Serializable {
    private List<ICandidate> candidates;

    private IVoter voter; // OTP field added for OTP authentication
    // Ajoutez d'autres champs si nécessaire

    public VotingMaterials(List<ICandidate> candidates, IVoter voter) {
        this.candidates = candidates;
        this.voter = voter;
    }

    public List<ICandidate> getCandidates() {
        return candidates;
    }

    public String getOTP() {
        return voter.getOtp();
    }

    public IVoter getVoter() {
        return voter;
    }
    // D'autres méthodes getters et setters
}
