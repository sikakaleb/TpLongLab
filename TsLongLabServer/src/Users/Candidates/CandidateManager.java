package Users.Candidates;

import commonInterfaces.ICandidate;

import java.util.HashSet;
import java.util.Set;

public class CandidateManager {
    private static CandidateManager instance;
    private Set<ICandidate> candidates;

    public CandidateManager() {
        candidates = new HashSet<>();
    }

    public static synchronized CandidateManager getInstance() {
        if (instance == null) {
            instance = new CandidateManager();
        }
        return instance;
    }

    // Ajouter un candidat. Retourne false si le candidat existe déjà.
    public boolean addCandidate(ICandidate candidate) {
        return candidates.add(candidate);
    }

    // Supprimer un candidat
    public boolean removeCandidate(ICandidate candidate) {
        return candidates.remove(candidate);
    }

    // Rechercher un candidat par son rang
    public ICandidate findByRank(int rank) {
        for (ICandidate c : candidates) {
            if (c.getRank() == rank) {
                return c;
            }
        }
        return null;
    }

    public Set<ICandidate> getCandidates() {
        return new HashSet<>(candidates);  // Retourner une copie pour éviter les modifications non contrôlées
    }
    // D'autres méthodes comme la sauvegarde/chargement, le tri, le filtrage, etc. peuvent être ajoutées ici.
}
