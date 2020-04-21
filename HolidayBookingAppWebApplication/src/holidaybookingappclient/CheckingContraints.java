package holidaybookingappclient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckingContraints")
public class CheckingContraints extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckingContraints() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String isUserValid = (String) session.getAttribute("username");
		if (isUserValid == null) {
			response.sendRedirect("HolidaySystemAppServlet");
		} else if (isUserValid == "admin") {
			response.sendRedirect("EmployeesServlet");
		} else if (isUserValid == "standard-user") {
			response.sendRedirect("BookingRequestServlet");
		}

		String newPassword = request.getParameter("oldPassword");
		PrintWriter out = response.getWriter();
		out.print("Beniamin Jitca");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
