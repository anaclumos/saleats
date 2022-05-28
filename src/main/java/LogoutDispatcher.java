
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/logout")
public class LogoutDispatcher extends HttpServlet {

	Service service = Service.getInstance();

	@Serial
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.removeCookie(request, response);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
