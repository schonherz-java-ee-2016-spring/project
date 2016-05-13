package hu.schonherz.training.web.supervisor.servlets;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cv.pdf")
public class PdfServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			String requestParam = request.getParameter("pdfPath");
			Path pdfPath = Paths.get(requestParam);
			byte[] pdfByteArray = Files.readAllBytes(pdfPath);
			response.setContentType("application/pdf");
			response.setContentLength(pdfByteArray.length);
			response.getOutputStream().write(pdfByteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
