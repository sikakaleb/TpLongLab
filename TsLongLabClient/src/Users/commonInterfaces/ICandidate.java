package Users.commonInterfaces;

import java.io.Serializable;

public interface ICandidate extends Serializable {
    int getRank();

    void setRank(int rank);

    String getFirstNameLastName();

    void setFirstNameLastName(String firstNameLastName);

    String getPitch();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
