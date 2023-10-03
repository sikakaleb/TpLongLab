package Users.Persons;

import java.util.Objects;

public class Person  {

    protected String name;
    protected String dateOfBirth;

    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getName(), person.getName()) && Objects.equals(getDateOfBirth(), person.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateOfBirth());
    }
}
