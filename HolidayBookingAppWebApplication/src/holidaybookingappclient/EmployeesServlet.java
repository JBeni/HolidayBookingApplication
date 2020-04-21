package holidaybookingappclient;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/EmployeesServlet")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
	private EmployeeAppBeanRemote employeeAppBean;

	private static final Logger logger = Logger.getLogger(EmployeesServlet.class);

    public EmployeesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			} else if (isUserValid.equals("standard-user")) {
				response.sendRedirect("BookingRequestServlet");
			}

			List<EmployeeDTO> allEmployees = employeeAppBean.getAllEmployees();
			List<EmployeeDTO> allDeletedEmployees = employeeAppBean.getAllDeletedEmployees();
			request.setAttribute("allEmployees", allEmployees);
			request.setAttribute("allDeletedEmployees", allDeletedEmployees);
			request.getRequestDispatcher("/employee.jsp").forward(request, response);
    	} catch (Exception e) {
			logger.error(e.getMessage());
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
