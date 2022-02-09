package by.http.autopark.controller.commandImplements;

import by.http.autopark.bean.User;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.DataErrorException;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we read data from the jsp page,
 * create an instance of the user and pass it on to the services layer
 * for check the data and enter it into the database
 */


public class RegistrationUser implements Command {

    private static final String LOGIN="login";
    private static final String NAME="name";
    private static final String EMALE="emale";
    private static final String ACCESS_TYPE="access type";
    private static final String PASSPORT_NUMBER="passportNumber";
    private static final String USERS_PASSWORD="userPassword";
    private static final String MESSAGE="message";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(LOGIN);
        String name=request.getParameter(NAME);
        String emale = request.getParameter(EMALE);
        String roleId=request.getParameter(ACCESS_TYPE);
        String passportNumber=request.getParameter(PASSPORT_NUMBER);
        String  password=request.getParameter(USERS_PASSWORD);

        User user=new User();

            user.setRoles_id((int)(Integer.parseInt(roleId)));
            user.setName(name);
            user.setEmail(emale);
            user.setLogin(login);
            user.setPassportNumber(passportNumber);
            user.setPassword(password);

            try {

                ServiceFactory.getInstance().getUserService().registration(user);
                response.sendRedirect("Controller?command=MESSAGE_SUCCESS_REGISTRATION");

            }catch (DataErrorException exc){
                request.setAttribute(MESSAGE,exc.getMessage());
                request.getRequestDispatcher("Controller?command=GO_TO_REGISTRATION").forward(request,response);

            }catch (ServiceException exc){
                request.setAttribute(MESSAGE,"Извините, но в слое Service возникла ошибка");
                request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
            }
    }
}



