package holidaybookingappclient;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import ejbholidaybookingapp.EmployeeAppBeanRemote;
import entityclasses.EmployeeDTO;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EmployeeAppBeanRemote employeeAppBean;

	private static final Logger logger = Logger.getLogger(ChangePasswordServlet.class);

    public ChangePasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			}

			request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			int userId = (int) session.getAttribute("userId");
			EmployeeDTO employee = employeeAppBean.getEmployeeById(userId);

			String newPassword = request.getParameter("newPassword");
			employee.setPassword(newPassword);

			employeeAppBean.updatePassWord(employee);
			response.sendRedirect("EmployeesServlet");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
