package by.Bohdan.Dmitry.project.endpoints.servlets;

import by.Bohdan.Dmitry.project.service.PollService;
import by.Bohdan.Dmitry.project.service.api.IPollService;
import by.Bohdan.Dmitry.project.service.api.dto.ChoiceWithCounter;
import by.Bohdan.Dmitry.project.service.api.dto.Poll;
import by.Bohdan.Dmitry.project.service.api.dto.SavedPoll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TopServlet", urlPatterns = "/top")
public class TopServlet extends HttpServlet {

    private IPollService pollService;

    public TopServlet() {
        this.pollService = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<ChoiceWithCounter<String>> artistTop = pollService.getArtistTop();
        List<ChoiceWithCounter<String>> genreTop = pollService.getGenreTop();

        writer.write("Топ артисты</br>");

        for (ChoiceWithCounter<String> choice : artistTop) {
            writer.write(choice.getChoice() + ":" + choice.getCountPoll() + "</br>");
        }

        writer.write("Топ жанры</br>");

        for (ChoiceWithCounter<String> choice : genreTop) {
            writer.write(choice.getChoice() + ":" + choice.getCountPoll() + "</br>");
        }

        List<SavedPoll> polls = pollService.getPolls();

        for (SavedPoll poll : polls) {
            writer.write(poll.toString());
        }
    }
}
