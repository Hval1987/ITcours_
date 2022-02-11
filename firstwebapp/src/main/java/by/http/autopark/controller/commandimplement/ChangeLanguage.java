package by.http.autopark.controller.commandimplement;

import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * in this class, we implement the command interface
 * to change the language in the application
 */

public class ChangeLanguage implements Command {
    private static final String LOCALE="locale";
    private static final String LOCAL="local";
    private static final String URL="url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();
        String lang=request.getParameter(LOCALE);
        session.setAttribute(LOCAL,lang);

        String url= (String) request.getSession().getAttribute(URL);
        response.sendRedirect(url);

    }
}
