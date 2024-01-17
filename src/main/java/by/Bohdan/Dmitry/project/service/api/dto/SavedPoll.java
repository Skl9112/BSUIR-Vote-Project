package by.Bohdan.Dmitry.project.service.api.dto;

import java.time.LocalDateTime;

public class SavedPoll implements Comparable<SavedPoll>{
    private final LocalDateTime time;
    private final Poll poll;

    public SavedPoll(LocalDateTime time, Poll poll) {
        this.time = time;
        this.poll = poll;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Poll getPoll() {
        return poll;
    }

    @Override
    public int compareTo(SavedPoll o) {
        return this.time.compareTo(o.getTime());
    }

    @Override
    public String toString() {
        return "SavedPoll{" +
                "time=" + time +
                ", poll=" + poll +
                '}';
    }
}
