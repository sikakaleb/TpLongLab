package Users.commonInterfaces;

public interface IVote {
    ICandidate getCandidate();

    int getScore();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
