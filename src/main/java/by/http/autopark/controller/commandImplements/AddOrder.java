package by.http.autopark.controller.commandImplements;

import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrder implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/jsp/AddOrderPage.jsp").forward(request,response);
    }
}
