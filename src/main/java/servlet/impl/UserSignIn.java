package servlet.impl;

import autopark.bean.Role;
import autopark.bean.User;
import autopark.dao.DAOFactory;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserSignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("welcome back");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        System.out.println("welcome back, "+ login);
        //проверяем данные пользователя из базы
        try {

            User thisUser=ServiceFactory.getInstance().getUserService().authorization(login);
            System.out.println("сработал блок  входа в систему ");


            if (userChek(thisUser,password)) {
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
    private static boolean userChek(User thisUser, String password){

        System.out.println("  "+thisUser.getPassword()+"  context-"+password);
        if(thisUser!=null){
            if(password.equals(thisUser.getPassword())){
                System.out.println("отработало сравнение");
                return true;
            }
            else
                return false;
        }
        else
            return false;


    }
}

