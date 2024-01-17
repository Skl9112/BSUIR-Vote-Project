package by.Bohdan.Dmitry.project.endpoints.servlets;

import by.Bohdan.Dmitry.project.service.PollService;
import by.Bohdan.Dmitry.project.service.api.IPollService;
import by.Bohdan.Dmitry.project.service.api.dto.Poll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PollServlet", urlPatterns = "/poll")
public class PollServlet extends HttpServlet {

    private IPollService pollService;

    public PollServlet() {
        this.pollService = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int artist = Integer.parseInt(req.getParameter("artist")) - 1;
        String[] genresRaw = req.getParameterMap().get("genres");

        int[] genres = new int[genresRaw.length];

        for (int i = 0; i < genresRaw.length; i++) {
            genres[i] = Integer.parseInt(genresRaw[i]) - 1;
        }

        String about = req.getParameter("about");

        Poll poll = new Poll(artist, genres, about);

        this.pollService.createPoll(poll);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write(poll.toString());
    }
}
