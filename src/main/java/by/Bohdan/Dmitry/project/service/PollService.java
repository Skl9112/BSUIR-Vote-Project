package by.Bohdan.Dmitry.project.service;

import by.Bohdan.Dmitry.project.service.api.IPollService;
import by.Bohdan.Dmitry.project.service.api.dto.ChoiceWithCounter;
import by.Bohdan.Dmitry.project.service.api.dto.Poll;
import by.Bohdan.Dmitry.project.service.api.dto.SavedPoll;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PollService implements IPollService {

    private static final PollService instance = new PollService();

    private List<String> artists = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<SavedPoll> polls = new ArrayList<>();

    private PollService() {
        this.artists.add("Ария");
        this.artists.add("Квин");
        this.artists.add("Баста");
        this.artists.add("Европа+");

        this.genres.add("Поп");
        this.genres.add("Рок");
        this.genres.add("Джаз");
        this.genres.add("Классика");
        this.genres.add("...");
    }

    @Override
    public List<String> getArtists() {
        return Collections.unmodifiableList(artists);
    }

    @Override
    public List<String> getGenres() {
        return Collections.unmodifiableList(genres);
    }

    @Override
    public void createPoll(Poll poll) {
        SavedPoll saved = new SavedPoll(LocalDateTime.now(), poll);
        this.polls.add(saved);
        this.polls.sort(SavedPoll::compareTo);
    }

    @Override
    public List<ChoiceWithCounter<String>> getArtistTop(Comparator<ChoiceWithCounter>... comparator) {
        Map<Integer, Integer> data = new HashMap<>();

        for (SavedPoll poll : this.polls) {
            int artist = poll.getPoll().getArtist();

            Integer count = data.getOrDefault(artist, 0);

            data.put(artist, count + 1);
        }

        Comparator<Map.Entry<Integer, Integer>> selectedComparator = (o1, o2) -> {
            return o1.getValue() - o2.getValue();
        };

        List<Map.Entry<Integer, Integer>> entries = data.entrySet().stream()
                .sorted(selectedComparator.reversed())
                .collect(Collectors.toList());

        List<ChoiceWithCounter<String>> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : entries) {
            result.add(new ChoiceWithCounter<>(this.artists.get(entry.getKey()), entry.getValue()));
        }

        return result;
    }

    @Override
    public List<ChoiceWithCounter<String>> getGenreTop(Comparator<ChoiceWithCounter>... comparator) {
        Map<Integer, Integer> data = new HashMap<>();

        for (SavedPoll poll : this.polls) {
            int[] genres = poll.getPoll().getGenres();

            for (int genre : genres) {
                Integer count = data.getOrDefault(genre, 0);

                data.put(genre, count + 1);
            }
        }

        Comparator<Map.Entry<Integer, Integer>> selectedComparator = (o1, o2) -> {
            return o1.getValue() - o2.getValue();
        };

        List<Map.Entry<Integer, Integer>> entries = data.entrySet().stream()
                .sorted(selectedComparator.reversed())
                .collect(Collectors.toList());

        List<ChoiceWithCounter<String>> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : entries) {
            result.add(new ChoiceWithCounter<>(this.genres.get(entry.getKey()), entry.getValue()));
        }

        return result;
    }

    @Override
    public List<SavedPoll> getPolls() {
        return Collections.unmodifiableList(this.polls);
    }

    public static PollService getInstance() {
        return instance;
    }
}
