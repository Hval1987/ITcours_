package servlet;

import autopark.dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
