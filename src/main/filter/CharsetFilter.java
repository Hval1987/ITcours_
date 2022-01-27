package filter;
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

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        request.setCharacterEncoding(encoding);
//        next.doFilter(request,response);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding=config.getInitParameter("requestEncoding");
        if(encoding==null) encoding="utf-8";



    }

}
