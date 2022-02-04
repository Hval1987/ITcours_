package by.http.autopark.controller.commandImplements;

import by.http.autopark.bean.Role;
import by.http.autopark.bean.User;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final String MESSAGE="message";
    private static final String ROLE="role";
    private static final String ROLE_ID="roleId";
    private static final String USER_ID="userId";
    private static final String LOGIN="login";
    private static final String PASSWORD="password";

    private final static Logger logger= LoggerFactory.getLogger(UserSignIn.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("достаем логин и пароль из реквеста для входа в систему");
        String login=request.getParameter(LOGIN);
        String password=request.getParameter(PASSWORD);
        User thisUser;
        boolean conditions=false;

        try {
            logger.debug("авторизуемся в системе");
            conditions=ServiceFactory.getInstance().getUserService().authorization(login,password);
            logger.debug("получаем экземпляр класса User по логину");
            thisUser=ServiceFactory.getInstance().getUserService().findUserByLogin(login);
/**
 * in this condition, in case of successful login,
 * we put the Role Id, user name and the role itself
 * into the session for further use during the session.
 */
            if (conditions) {
                logger.info("пользователь "+ login+" найден в системе");
                List<Role> list=ServiceFactory.getInstance().getRoleService().getAllRoles();
                HttpSession session=request.getSession();

                int roleId=thisUser.getRoles_id();
                String role=null;

                for (Role tmp: list) {
                    if(tmp.getId()==roleId){
                        role=tmp.getRole();
                    }
                }

                session.setAttribute(ROLE,role);
                session.setAttribute(ROLE_ID,roleId);
                session.setAttribute(USER_ID,thisUser.getId());
                logger.debug("получена роль пользователя-> "+role+" и положена в сессию");

                response.sendRedirect("Controller?command=GO_TO_MAIN_MENU&message=hello");
            } else {
                logger.info("неправильно введен пароль и логин"+login+" "+password);
                request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.data.exception"));
                request.getRequestDispatcher("jsp/SignInPage.jsp").forward(request, response);

            }
        }catch (ServiceException exc){
            logger.warn("ошибка сервисов-",exc);
            request.setAttribute(MESSAGE,DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
    }

}

