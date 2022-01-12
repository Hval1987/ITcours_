package servlet.impl;

import autopark.bean.User;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //регистрируем пользователя, addUser в бд

        System.out.println("hi, new user");
        String name = request.getParameter("name");
        String emale = request.getParameter("emale");
        String roleId=request.getParameter("access type");
        String passportNumber=request.getParameter("passportNumber");
        String  password=request.getParameter("userPassword");

        System.out.println("ваше имя -" + name + ", ваша фамилия-" + emale+"\n номер телефона -"+passportNumber+", пароль - "+password);
        System.out.println("сработал блок  регистрации ");

//        boolean flag=true;
        if(name!=""&&
                emale!=""&&
                    roleId!=""&&
                         passportNumber!=""&&
                             password!="" ){
            System.out.println("!!"+name+" "+ emale+" "+roleId+" "+ passportNumber+" "+password);

            System.out.println("success");
            User user=new User();

            System.out.println(roleId.getClass().getName().toString());
            user.setRoles_id((int)(Integer.parseInt(roleId)));
            System.out.println("process");
            user.setEmail(emale);
            user.setName(name);
            user.setPassportNumber(passportNumber);
            user.setPassword(password);


            try {

                ServiceFactory.getInstance().getUserService().registration(user);
                User tmp=ServiceFactory.getInstance().getUserService().authorization(name);
                System.out.println("saved");

            }catch (ServiceException exc){
                System.out.println("ошибка слоя service");

            }
            response.sendRedirect("Controller?command=MESSAGE_SUCCESS_REGISTRATION");
        }
        else{
            request.setAttribute("Unsuccess","введите верные логин и пароль");
            request.getRequestDispatcher("jsp/RegistrationPage.jsp").forward(request,response);
        }




    }

}


