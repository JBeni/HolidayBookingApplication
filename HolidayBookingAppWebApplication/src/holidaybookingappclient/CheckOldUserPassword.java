package holidaybookingappclient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ejbholidaybookingapp.EmployeeAppBeanRemote;

@WebServlet("/CheckOldUserPassword")
public class CheckOldUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EmployeeAppBeanRemote employeeAppBean;

	private static final Logger logger = Logger.getLogger(ChangePasswordServlet.class);

    public CheckOldUserPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			int userId = (int) session.getAttribute("userId");
			String oldPassword = request.getParameter("oldPassword");
			boolean validPassword = employeeAppBean.checkUserPassword(userId, oldPassword);

			PrintWriter out = response.getWriter();
			out.print(validPassword);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
