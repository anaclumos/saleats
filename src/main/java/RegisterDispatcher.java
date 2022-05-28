
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.io.Serial;

import controller.Service;
import model.Status;
import model.User;

@WebServlet("/register")
public class RegisterDispatcher extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	private Service service = Service.getInstance();

	public RegisterDispatcher() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		Boolean agreed = Boolean.parseBoolean(request.getParameter("agree"));

		if (!agreed) {
			request.setAttribute("error", "You must agree to the terms and conditions.");
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
			return;
		}

		Status s = service.registerUser(email, name, password, confirmPassword);

		response.setContentType("text/html");
		if (s.isOk()) {
			User user = service.login(email, password);
			response.addCookie(service.createCookie(user.getName()));
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("error", s.says());
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
