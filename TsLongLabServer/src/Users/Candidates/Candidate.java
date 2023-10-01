package Users.Candidates;

import Users.Persons.Person;
import Users.Voters.Voter;
import commonInterfaces.IPitch;

import java.util.Objects;

public class Candidate extends Person {
    private int rank; // rang comme identifiant unique
    private String firstNameLastName; // prénom et nom sous forme de chaîne unique
    private IPitch pitch;


    public Candidate(String name, String dateOfBirth, int rank, String firstNameLastName) {
        super(name, dateOfBirth);
        this.rank = rank;
        this.firstNameLastName = firstNameLastName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFirstNameLastName() {
        return firstNameLastName;
    }

    public void setFirstNameLastName(String firstNameLastName) {
        this.firstNameLastName = firstNameLastName;
    }

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
