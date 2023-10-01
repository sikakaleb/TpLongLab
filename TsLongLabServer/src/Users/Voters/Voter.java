package Users.Voters;

import Users.Persons.Person;

import java.util.Objects;

public class Voter extends Person {
    private String studentNumber; // numéro d'étudiant comme identifiant unique
    private String password;

    public Voter(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
    public Voter(String name, String dateOfBirth, String studentNumber, String password) {
        super(name, dateOfBirth);
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter voter)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getStudentNumber(), voter.getStudentNumber()) && Objects.equals(getPassword(), voter.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentNumber(), getPassword());
    }
}
