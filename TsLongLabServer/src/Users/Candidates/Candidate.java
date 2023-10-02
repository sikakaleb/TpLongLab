package Users.Candidates;

import Users.Persons.Person;
import commonInterfaces.ICandidate;
import commonInterfaces.IPitch;

import java.util.Objects;

public class Candidate extends Person implements ICandidate {
    private int rank; // rang comme identifiant unique
    private String firstNameLastName; // prénom et nom sous forme de chaîne unique
    private IPitch pitch;


    public Candidate(int rank, String firstNameLastName, IPitch pitch) {
        super(null, null);
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
}
