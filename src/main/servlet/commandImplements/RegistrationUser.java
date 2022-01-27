package servlet.commandImplements;

import autopark.bean.User;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;
import servlet.Command;

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


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //регистрируем пользователя, addUser в бд

        System.out.println("hi, new user");
        String login = request.getParameter(LOGIN);
        String name=request.getParameter(NAME);
        String emale = request.getParameter(EMALE);
        String roleId=request.getParameter(ACCESS_TYPE);
        String passportNumber=request.getParameter(PASSPORT_NUMBER);
        String  password=request.getParameter(USERS_PASSWORD);

        System.out.println("ваш логин -" + login + ", ваш пароль-" + emale+"\n номер пасспорта -"+passportNumber+", пароль - "+password);
        System.out.println("сработал блок  регистрации ");

//        boolean flag=true;

            System.out.println("!!"+login+" "+ emale+" "+roleId+" "+ passportNumber+" "+password);

            System.out.println("success");
            User user=new User();


            System.out.println(roleId.getClass().getName().toString());
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
                request.setAttribute("message",exc.getMessage());
                request.getRequestDispatcher("Controller?command=GO_TO_REGISTRATION").forward(request,response);

            }catch (ServiceException exc){
                request.setAttribute("message",exc.getMessage());
                request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
            }
    }
}



