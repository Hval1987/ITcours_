package servlet.commandImplements;

import autopark.bean.Role;
import autopark.bean.User;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserSignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("welcome back");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        System.out.println("welcome back, "+ login);
        User thisUser;
        boolean flag=false;
        //проверяем данные пользователя из базы
        try {

            flag=ServiceFactory.getInstance().getUserService().authorization(login,password);
            System.out.println("сработал блок  входа в систему ");
            thisUser=ServiceFactory.getInstance().getUserService().findUserByLogin(login);
            System.out.println("сработал блок  входа в систему ");System.out.println("сработал блок  входа в систему ");


            if (flag) {
                System.out.println("user was find");
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
                System.out.println(role);

                response.sendRedirect("Controller?command=GO_TO_MAIN_MENU&message=hello");
            } else {
                System.out.println("user was nor find!!!");
                request.setAttribute("Unsuccess", "введите верные логин и пароль");
                request.getRequestDispatcher("jsp/SignInPage.jsp").forward(request, response);

            }
        }catch (ServiceException exc){
            System.out.println("ошибка слоя Service");
        }
    }

}

