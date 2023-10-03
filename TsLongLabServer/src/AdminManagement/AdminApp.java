package AdminManagement;

import Users.Candidates.Candidate;
import Users.Candidates.Pitch.TextPitch;
import Users.Voters.Voter;
import commonInterfaces.ICandidate;
import commonInterfaces.IVoter;

import java.io.*;
import java.util.*;

public class AdminApp {
    private List<IVoter> voters;
    private List<ICandidate> candidates;
    private static final String VOTERS_FILE = "votersData.csv";
    private static final String CANDIDATES_FILE = "candidatesData.csv";


    public AdminApp() {
        this.voters = new ArrayList<>();
        this.candidates = new ArrayList<>();
        // Charger des données préexistantes si le fichier existe déjà
        loadDataFromFile();
    }

    public void addVoter(IVoter voter) {
        voters.add(voter);
    }

    public void addCandidate(ICandidate candidate) {
        candidates.add(candidate);
    }

    public void saveDataToFile() {
        // Sauvegarder les votants
        try (BufferedWriter bwVoters = new BufferedWriter(new FileWriter(VOTERS_FILE))) {
            for (IVoter voter : voters) {
                bwVoters.write(String.format("%s,%s,%s,%s%n",
                        voter.getName(), voter.getDateOfBirth(), voter.getStudentNumber(), voter.getPassword()));
            }
            System.out.println("Votants sauvegardés avec succès!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sauvegarder les candidats
        try (BufferedWriter bwCandidates = new BufferedWriter(new FileWriter(CANDIDATES_FILE))) {
            for (ICandidate candidate : candidates) {
                bwCandidates.write(String.format("%d,%s%n",
                        candidate.getRank(), candidate.getFirstNameLastName()));
            }
            System.out.println("Candidats sauvegardés avec succès!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void loadDataFromFile() {
        // Charger les votants
        File votersFile = new File(VOTERS_FILE);
        if (votersFile.exists()) {
            try (BufferedReader brVoters = new BufferedReader(new FileReader(VOTERS_FILE))) {
                String line;
                while ((line = brVoters.readLine()) != null) {
                    String[] fields = line.split(",");
                    voters.add(new Voter(fields[0], fields[1], fields[2], fields[3]));
                }
                System.out.println("Votants chargés avec succès!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier de votants trouvé. Démarrage avec une liste vide.");
        }

        // Charger les candidats
        File candidatesFile = new File(CANDIDATES_FILE);
        if (candidatesFile.exists()) {
            try (BufferedReader brCandidates = new BufferedReader(new FileReader(CANDIDATES_FILE))) {
                String line;
                while ((line = brCandidates.readLine()) != null) {
                    String[] fields = line.split(",");
                    // Supposons que `rank` soit de type int. Si ce n'est pas le cas, ajustez le code en conséquence.
                    int rank = Integer.parseInt(fields[0]);
                    String firstNameLastName = fields[1];
                    // Si vous avez sauvegardé des informations concernant le pitch, vous pouvez les extraire ici.
                    // Pour l'instant, je mets un placeholder pour le pitch.
                    candidates.add(new Candidate(rank, firstNameLastName, null)); // Remplacez `null` par le pitch si nécessaire
                }
                System.out.println("Candidats chargés avec succès!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier de candidats trouvé. Démarrage avec une liste vide.");
        }

    }

    public List<IVoter> getVoters() {
        return voters;
    }

    public void setVoters(List<IVoter> voters) {
        this.voters = voters;
    }

    public List<ICandidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<ICandidate> candidates) {
        this.candidates = candidates;
    }

    public static void main(String[] args) {
        // Interface utilisateur pour ajouter des votants et des candidats
        // et pour sauvegarder les données
        // Pour l'instant, je vais montrer un exemple très basique pour tester.
        AdminApp app = new AdminApp();
        // Ajoutons quelques votants et candidats
        app.addVoter(new Voter("Alice", "01/01/1990", "AlicePass", "password1"));
        app.addCandidate(new Candidate(1, "John Doe", new TextPitch("Je suis John Doe et je veux être votre président!")));
        // Sauvegarde des données dans le fichier
        app.saveDataToFile();

        // Ici, vous pouvez ajouter une véritable interface utilisateur pour interagir avec l'administrateur.
    }
}

