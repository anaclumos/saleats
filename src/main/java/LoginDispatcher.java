
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import controller.Service;
import model.User;

@WebServlet("/login")
public class LoginDispatcher extends HttpServlet {

	private Service service = Service.getInstance();

	@Serial
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email.isEmpty() || password.isEmpty()) {
			request.setAttribute("error", "Please enter your email and password.");
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
			return;
		}
		User user = service.login(email, password);
		if (user != null && user.isOk()) {
			response.addCookie(service.createCookie(user.getName()));
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("error", "Invalid email or password.");
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
