package AdminManagement;

import Users.Candidates.Candidate;
import Users.Candidates.CandidateManager;
import Users.Candidates.Pitch.TextPitch;
import Users.Voters.Voter;
import Users.Voters.VoterManager;
import commonInterfaces.ICandidate;
import commonInterfaces.IVoter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        VoterManager.getInstance().registerVoter(voter);
    }

    public void addCandidate(ICandidate candidate) {
        CandidateManager.getInstance().addCandidate(candidate);
    }


    public void saveDataToFile() {
        // Utilisez VoterManager pour obtenir la liste des votants
        Set<IVoter> allVoters = VoterManager.getInstance().getAllVoters();

        // Sauvegarder les votants
        try (BufferedWriter bwVoters = new BufferedWriter(new FileWriter(VOTERS_FILE))) {
            for (IVoter voter : allVoters) {
                bwVoters.write(String.format("%s,%s,%s,%s,%s,%n",
                        voter.getName(), voter.getDateOfBirth(), voter.getStudentNumber(), voter.getPassword(), voter.getOtp()));
            }
            System.out.println("Votants sauvegardés avec succès!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Utilisez CandidateManager pour obtenir la liste des candidats
        List<ICandidate> allCandidates = CandidateManager.getInstance().getCandidates().stream().toList();

        // Sauvegarder les candidats
        try (BufferedWriter bwCandidates = new BufferedWriter(new FileWriter(CANDIDATES_FILE))) {
            for (ICandidate candidate : allCandidates) {
                bwCandidates.write(String.format("%d,%s,%s%n",
                        candidate.getRank(), candidate.getFirstNameLastName(), candidate.getPitch()));
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
                    Voter voter = new Voter(fields[0], fields[1], fields[2], fields[3]);
                    VoterManager.getInstance().registerVoter(voter);
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
                    int rank = Integer.parseInt(fields[0]);
                    String firstNameLastName = fields[1];
                    String pitch = fields[2];
                    Candidate candidate = new Candidate(rank, firstNameLastName,new TextPitch(pitch));
                    CandidateManager.getInstance().addCandidate(candidate);
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

