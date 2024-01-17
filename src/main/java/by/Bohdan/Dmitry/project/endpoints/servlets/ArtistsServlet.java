package by.Bohdan.Dmitry.project.endpoints.servlets;

import by.Bohdan.Dmitry.project.service.PollService;
import by.Bohdan.Dmitry.project.service.api.IPollService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artists")
public class ArtistsServlet extends HttpServlet {

    private IPollService pollService;

    public ArtistsServlet() {
        this.pollService = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<String> artists = pollService.getArtists();

        int index = 0;
        for (String artist : artists) {
            writer.write(++index + " " + artist + "</br>");
        }
    }
}
