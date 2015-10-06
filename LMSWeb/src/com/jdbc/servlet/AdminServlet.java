package com.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.lmdo.Author;
import com.jdbc.lmsys.AdministratorManagementSys;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public AdminServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			//System.out.println("HERE: "+ action);
			if (action.equals("listAuthors")) {
				response.setContentType("text/html");

				PrintWriter out = response.getWriter();
				String title = "List of Author";
				out.println("<!DOCTYPE html>" +
						"<html>\n" +
						"<head><title>" + title + "</title>"+
						"<style>"+
						"table, th, td {border: 1px solid black;}"+
						"</style> </head>\n" +
						"<h1 align=\"center\">" + title + "</h1>\n" +
						"<table>"+
						"<tr>" +
						"<th>ID</th>" +
						"<th>Name</th>" +
						"</tr>");
				AdministratorManagementSys ams = new AdministratorManagementSys();
				List<Author> auths = ams.getAllAuthor();
				for(Author auth : auths){
					out.println("<tr>"+
							"<td>"+auth.getAuthorId()+"</td>"+
							"<td>"+auth.getAuthorName()+"</td>"+
							"</tr>");

				}
				out.println("</table>"+
						"</body></html>");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			//System.out.println("HERE: "+ action);
			if (action.equals("addAuthor")) {
				Author author = new Author();
				author.setAuthorName(request.getParameter("authorName"));
				new AdministratorManagementSys().insertAuthor(author);
				RequestDispatcher view = request.getRequestDispatcher("");
				view.forward(request, response);
			}
			else if (action.equals("updateAuthor")) {
				Author author = new Author();
				author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				author.setAuthorName(request.getParameter("authorName"));
				new AdministratorManagementSys().updateAuthor(author);
				RequestDispatcher view = request.getRequestDispatcher("");
				view.forward(request, response);
			}else if (action.equals("deleteAuthor")) {
				Author author = new Author();
				author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				new AdministratorManagementSys().deleteAuthor(author);
				RequestDispatcher view = request.getRequestDispatcher("");
				view.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
