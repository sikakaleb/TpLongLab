package Votes;

import commonInterfaces.ICandidate;
import commonInterfaces.IVote;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VoteManager implements Serializable {
    private static final String VOTES_FILE = "votes.csv";  // Chemin vers le fichier CSV
    private static VoteManager instance;

    private Map<ICandidate, Integer> voteSum;

    private VoteManager() {
        voteSum = new HashMap<>();
    }

    public static synchronized VoteManager getInstance() {
        if (instance == null) {
            instance = new VoteManager();
        }
        return instance;
    }

    public void recordVote(IVote vote) {
        voteSum.put(vote.getCandidate(), voteSum.getOrDefault(vote.getCandidate(), 0) + vote.getScore());
        writeVoteToFile(vote);  // Écrire le vote dans le fichier après l'enregistrement
    }

    public Map<ICandidate, Integer> getVotesByCandidate() {
        return voteSum.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                ));
    }

    // Méthode pour écrire le vote dans un fichier CSV
    private void writeVoteToFile(IVote vote) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VOTES_FILE, true))) {
            bw.write(String.format("%s,%d%n", vote.getCandidate().getFirstNameLastName(), vote.getScore()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
