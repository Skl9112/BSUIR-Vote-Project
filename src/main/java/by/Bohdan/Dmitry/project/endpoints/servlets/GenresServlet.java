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

@WebServlet(name = "GenresServlet", urlPatterns = "/genres")
public class GenresServlet extends HttpServlet {

    private IPollService pollService;

    public GenresServlet() {
        this.pollService = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<String> genres = pollService.getGenres();

        int index = 0;
        for (String genre : genres) {
            writer.write(++index + " " + genre + "</br>");
        }
    }
}
