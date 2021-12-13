package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private final CommandProvider provider=new CommandProvider();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      process(request, response);

    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String command=request.getParameter("command");
        Command currentCommand=provider.getCommand(command);
        currentCommand.execute(request, response);

//        switch (command){
//            case "nameinput" :
//
//
//            String name = request.getParameter("name");
//            String surname = request.getParameter("surname");
//            System.out.println("ваше имя -" + name + ", ваша фамилия-" + surname);
//            PrintWriter out = response.getWriter();
//            out.println("<h1> Hello " + name + "  " + surname + "</h1>");
//                System.out.println("сработал блок ввода имени");
//            break;
//
//        }
        switch (command){
            case "registration":

                String name = request.getParameter("password1");
                String surname = request.getParameter("password2");
                System.out.println("пароль 1 -" + name + ", пароль 2-" + surname);
                PrintWriter out = response.getWriter();
                if(name.equals(surname)) {
                    out.println("<h1> passwords " + name + "  " + surname + " -are true </h1>");
                }
                else
                    out.println("<h1> passwords " + name + "  " + surname + " -is not true </h1>");
                System.out.println("сработал блок ввода имени");
                break;

        }

    }
}
