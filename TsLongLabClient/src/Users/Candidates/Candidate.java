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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate candidate)) return false;
        if (!super.equals(o)) return false;
        return getRank() == candidate.getRank() && Objects.equals(getFirstNameLastName(), candidate.getFirstNameLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRank(), getFirstNameLastName());
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
