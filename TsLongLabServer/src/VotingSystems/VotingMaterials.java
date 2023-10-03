package VotingSystems;

import commonInterfaces.ICandidate;

import java.io.Serializable;
import java.util.List;

public class VotingMaterials implements Serializable {
    private List<ICandidate> candidates;
    // Ajoutez d'autres champs si nécessaire

    public VotingMaterials(List<ICandidate> candidates) {
        this.candidates = candidates;
    }

    public List<ICandidate> getCandidates() {
        return candidates;
    }
    // D'autres méthodes getters et setters
}
