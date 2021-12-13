package impl;

import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserRegistration implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("hi, new user");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber=request.getParameter("phone number");
        String  password=request.getParameter("password");
        System.out.println("ваше имя -" + name + ", ваша фамилия-" + surname+"\n номер телефона -"+phoneNumber+", пароль - "+password);
        PrintWriter out = response.getWriter();
        out.println("<h1> Info for registration->> <br/> name- " + name + "<br/> surname- " + surname +"<br/> phone number- "+phoneNumber+"<br/> password- "+password+"</h1>");
        System.out.println("сработал блок  регистрации ");
    }
}
