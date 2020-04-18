package holidaybookingappclient;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ChangePasswordServlet.class);

    public ChangePasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}