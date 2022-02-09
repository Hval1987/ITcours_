package by.http.autopark.filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter")
public class CharsetFilter implements Filter {
    private String encoding;
    private ServletContext context;
    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
        request.setCharacterEncoding(encoding);
response.setCharacterEncoding(encoding);
context.log("Charset was set.");
chain.doFilter(request, response);
}
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("characterEncoding");
        context = fConfig.getServletContext();
    }
}