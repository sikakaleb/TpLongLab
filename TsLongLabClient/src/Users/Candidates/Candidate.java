package Users.Candidates;

import commonInterfaces.ICandidate;
import commonInterfaces.IPitch;

import java.util.Objects;

public class Candidate  implements ICandidate {
    private static final long serialVersionUID = 1L;
    private int rank; // rang comme identifiant unique
    private String firstNameLastName; // prénom et nom sous forme de chaîne unique
    private IPitch pitch;

    public Candidate() {
    }

    public Candidate(int rank, String firstNameLastName, IPitch pitch) {
        this.rank = rank;
        this.firstNameLastName = firstNameLastName;
        this.pitch = pitch;
    }

    public Candidate(int rank, String firstNameLastName) {
        this.rank = rank;
        this.firstNameLastName = firstNameLastName;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String getFirstNameLastName() {
        return firstNameLastName;
    }

    @Override
    public void setFirstNameLastName(String firstNameLastName) {
        this.firstNameLastName = firstNameLastName;
    }

    @Override
    public String getPitch() {
        return pitch.getPitchContent();
    }

    // Dans la classe qui implémente ICandidate, par exemple `Candidate`:

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ICandidate that = (ICandidate) obj;

        return getRank() == that.getRank();
    }

    @Override
    public int hashCode() {
        return getRank();
    }


    @Override
    public String toString() {
        return "Candidate {" +
                "\n   Rank: " + rank + "," +
                "\n   Name: '" + firstNameLastName + "'," +
                "\n   Pitch: '" + (pitch != null ? pitch.getPitchContent() : "N/A") + "'" +
                "\n}";
    }

}
