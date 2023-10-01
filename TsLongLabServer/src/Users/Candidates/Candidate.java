package Users.Candidates;

import Users.Voters.Voter;

import java.util.Objects;

public class Candidate extends Voter {
    private int rank; // rang comme identifiant unique
    private String firstNameLastName; // prénom et nom sous forme de chaîne unique
    //private Pitch pitch;


    public Candidate(String name, String dateOfBirth, String studentNumber, String password, int rank, String firstNameLastName) {
        super(name, dateOfBirth, studentNumber, password);
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
