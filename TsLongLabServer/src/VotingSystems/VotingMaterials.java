package VotingSystems;

import commonInterfaces.ICandidate;

import java.io.Serializable;
import java.util.List;

public class VotingMaterials implements Serializable {
    private List<ICandidate> candidates;
    private String OTP; // OTP field added for OTP authentication
    // Ajoutez d'autres champs si nécessaire

    public VotingMaterials(List<ICandidate> candidates, String OTP) {
        this.candidates = candidates;
        this.OTP = OTP;
    }

    public List<ICandidate> getCandidates() {
        return candidates;
    }

    public String getOTP() {
        return OTP;
    }
    // D'autres méthodes getters et setters
}
