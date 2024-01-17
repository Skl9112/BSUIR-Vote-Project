package by.Bohdan.Dmitry.project.endpoints.main;

import by.Bohdan.Dmitry.project.service.PollService;
import by.Bohdan.Dmitry.project.service.api.IPollService;
import by.Bohdan.Dmitry.project.service.api.dto.Poll;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPollService service = PollService.getInstance();

        List<String> artists = service.getArtists();
        System.out.println("Выберите артиста");
        int indexArtist = 0;
        for (String artist : artists) {
            System.out.println(++indexArtist + " - " + artist);
        }
        int choiceArtist = scanner.nextInt();

        List<String> genres = service.getGenres();
        int[] choiceGenres = new int[3];
        for (int i = 0; i < choiceGenres.length; i++) {
            System.out.println("Выберите " + (i + 1) + " жанр");
            int indexGenre = 0;
            for (String genre : genres) {
                System.out.println(++indexGenre + " - " + genre);
            }
            choiceGenres[i] = scanner.nextInt();
        }

        System.out.println("Расскажите о себе");

        scanner = new Scanner(System.in);

        String about = scanner.nextLine();

        Poll poll = new Poll(choiceArtist, choiceGenres, about);

        service.createPoll(poll);
    }
}
