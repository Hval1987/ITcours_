package servlet.commandImplements;

import autopark.bean.Role;
import autopark.bean.User;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * in this class, we implement the command interface
 * for transmitting information from the login page for further verification
 * through the database and authorization in the system. In case of an exception,
 * we forward it to the error page
 */

public class UserSignIn implements Command {
    private final static Logger logger= LoggerFactory.getLogger(UserSignIn.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("достаем логин и пароль из реквеста для входа в систему");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        User thisUser;
        boolean flag=false;

        try {
            logger.debug("авторизуемся в системе");
            flag=ServiceFactory.getInstance().getUserService().authorization(login,password);
            logger.debug("получаем экземпляр класса User по логину");
            thisUser=ServiceFactory.getInstance().getUserService().findUserByLogin(login);
/**
 * in this condition, in case of successful login,
 * we put the Role Id, user name and the role itself
 * into the session for further use during the session.
 */
            if (flag) {
                logger.info("пользователь "+ login+" найден в системе");
                List<Role> list=ServiceFactory.getInstance().getRoleService().getAllRoles();
                HttpSession session=request.getSession();

                //получаем значение роли
                int roleId=thisUser.getRoles_id();
                String role=null;

                for (Role tmp: list) {
                    if(tmp.getId()==roleId){
                        role=tmp.getRole();
                    }


                }
                session.setAttribute("role",role);
                session.setAttribute("roleId",roleId);
                session.setAttribute("userId",thisUser.getId());
                logger.debug("получена роль пользователя-> "+role+" и положена в сессию");

                response.sendRedirect("Controller?command=GO_TO_MAIN_MENU&message=hello");
            } else {
                logger.info("неправильно введен пароль и логин"+login+" "+password);
                request.setAttribute("Unsuccess", "введите верные логин и пароль");
                request.getRequestDispatcher("jsp/SignInPage.jsp").forward(request, response);

            }
        }catch (ServiceException exc){
            logger.warn("ошибка сервисов-",exc);
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
    }

}

