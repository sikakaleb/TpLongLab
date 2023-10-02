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
    private static final String VOTERS_FILE = "votersData.ser";
    private static final String CANDIDATES_FILE = "candidatesData.ser";


    public AdminApp() {
        this.voters = new ArrayList<>();
        this.candidates = new ArrayList<>();
        // Charger des données préexistantes si le fichier existe déjà
        loadDataFromFile();
    }

    public void addVoter(Voter voter) {
        voters.add(voter);
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public void saveDataToFile() {
        // Sauvegarder les votants
        try (ObjectOutputStream oosVoters = new ObjectOutputStream(new FileOutputStream(VOTERS_FILE))) {
            oosVoters.writeObject(voters);
            System.out.println("Votants sauvegardés avec succès!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sauvegarder les candidats
        try (ObjectOutputStream oosCandidates = new ObjectOutputStream(new FileOutputStream(CANDIDATES_FILE))) {
            oosCandidates.writeObject(candidates);
            System.out.println("Candidats sauvegardés avec succès!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadDataFromFile() {
        // Charger les votants
        File votersFile = new File(VOTERS_FILE);
        if (votersFile.exists()) {
            try (ObjectInputStream oisVoters = new ObjectInputStream(new FileInputStream(VOTERS_FILE))) {
                voters = (List<Voter>) oisVoters.readObject();
                System.out.println("Votants chargés avec succès!");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier de votants trouvé. Démarrage avec une liste vide.");
        }

        // Charger les candidats
        File candidatesFile = new File(CANDIDATES_FILE);
        if (candidatesFile.exists()) {
            try (ObjectInputStream oisCandidates = new ObjectInputStream(new FileInputStream(CANDIDATES_FILE))) {
                candidates = (List<Candidate>) oisCandidates.readObject();
                System.out.println("Candidats chargés avec succès!");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier de candidats trouvé. Démarrage avec une liste vide.");
        }
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public List<ICandidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
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

