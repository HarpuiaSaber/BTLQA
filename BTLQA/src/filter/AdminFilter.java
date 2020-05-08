package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//		resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//		resp.addHeader("Access-Control-Max-Age", "1728000");
        
		HttpSession session = req.getSession();
		Object object = session.getAttribute("user");
		if (object != null) {
			User user = (User) object;
			if (user.getRole().getId() == 1) {
				chain.doFilter(request, response);
				return;
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/error/401.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
