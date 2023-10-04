package commonInterfaces;

import java.io.Serializable;

public interface IVote extends Serializable {
    ICandidate getCandidate();

    int getScore();


    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
