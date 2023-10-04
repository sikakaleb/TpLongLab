package AdminManagement;

import Votes.InvalidVoteException;
import Votes.Vote;
import commonInterfaces.ICandidate;
import commonInterfaces.IVote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<IVote> resultMap;

    public Referee() {
        this.resultMap = new ArrayList<>();
    }

    public Referee(Map<ICandidate, Integer> resultMap) throws InvalidVoteException {
        this.resultMap = new ArrayList<>();

        for (Map.Entry<ICandidate, Integer> entry : resultMap.entrySet()) {
            IVote vote = new Vote(entry.getKey(), entry.getValue());
            this.resultMap.add(vote) ;
        }
    }

    public List<IVote> getResultMap() {
        return resultMap;
    }

    public void setResultMap(List<IVote> resultMap) {
        this.resultMap = resultMap;
    }

    // Vous pouvez ajouter d'autres méthodes utiles si nécessaire, comme des méthodes pour ajouter des résultats, obtenir un résultat pour une clé spécifique, etc.
}
