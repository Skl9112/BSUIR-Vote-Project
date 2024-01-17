package by.Bohdan.Dmitry.project.service.api;

import by.Bohdan.Dmitry.project.service.api.dto.ChoiceWithCounter;
import by.Bohdan.Dmitry.project.service.api.dto.Poll;
import by.Bohdan.Dmitry.project.service.api.dto.SavedPoll;

import java.util.Comparator;
import java.util.List;

public interface IPollService {
    List<String> getArtists();

    List<String> getGenres();

    void createPoll(Poll poll);

    List<ChoiceWithCounter<String>> getArtistTop(Comparator<ChoiceWithCounter>... comparator);
    List<ChoiceWithCounter<String>> getGenreTop(Comparator<ChoiceWithCounter>... comparator);
    List<SavedPoll> getPolls();
}
