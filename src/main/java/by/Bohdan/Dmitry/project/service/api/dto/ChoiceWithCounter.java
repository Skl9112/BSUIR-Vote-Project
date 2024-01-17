package by.Bohdan.Dmitry.project.service.api.dto;

public class ChoiceWithCounter<T> implements  Comparable<ChoiceWithCounter<?>>{
    private T choice;
    private int countPoll;

    public ChoiceWithCounter(T choice, int countPoll) {
        this.choice = choice;
        this.countPoll = countPoll;
    }

    public T getChoice() {
        return choice;
    }

    public int getCountPoll() {
        return countPoll;
    }

    @Override
    public String toString() {
        return "ChoiceWithCounter{" +
                "choice=" + choice +
                ", countPoll=" + countPoll +
                '}';
    }

    @Override
    public int compareTo(ChoiceWithCounter<?> o) {
        return this.countPoll - o.getCountPoll();
    }
}
