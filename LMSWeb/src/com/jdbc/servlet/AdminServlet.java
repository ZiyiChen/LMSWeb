package com.jdbc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbc.lmdo.Author;
import com.jdbc.lmdo.Book;
import com.jdbc.lmdo.Genre;
import com.jdbc.lmdo.Publisher;
import com.jdbc.lmsys.AdministratorManagementSys;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/listAuthors","/addAuthor","/deleteAuthor","/updateAuthor",
	"/listBooks","/addBook","/deleteBook","/updateBook",
	"/listPublishers","/addPublisher","/deletePublisher","/updatePublisher",
	"/listBranchs","/addBranch","/deleteBranch","/updateBranch",
	"/listBorrowers","/addBorower","/deleteBorower","/updateBorower",
	"/listLoans","/updateLoan",
	"/listGenres"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public AdminServlet() {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String function = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String message = null;
		System.out.println("action: "+function);
		
		switch (function) {
		case "/addAuthor": {
			String authorName = request.getParameter("authorName");
			Author author = new Author();
			author.setAuthorName(authorName);

			try {
				new AdministratorManagementSys().insertAuthor(author);
				message = "Author add succesfully";
			} catch (Exception e) {
				e.printStackTrace();
				message = null;
				message = "Author add failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/updateAuthor": {
			String authorName = request.getParameter("authorName");
			int authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorName(authorName);
			author.setAuthorId(authorId);
			try {
				new AdministratorManagementSys().updateAuthor(author);
				message = "Author update succesfully";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Author update failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/deleteAuthor": {
			int authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				new AdministratorManagementSys().deleteAuthor(author);
				message = "Author delete succesfully";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Author delete failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/listAuthors": {
			try {
				List<Author> aus = new AdministratorManagementSys().getAllAuthors();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), aus);
			}catch (Exception e) {
				e.printStackTrace();
				message = "List Authors failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		case "/listGenres": {
			try {
				List<Genre> gens = new AdministratorManagementSys().getAllGenres();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), gens);
			}catch (Exception e) {
				e.printStackTrace();
				message = "List Genres failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		case "/addPublisher": {
			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");
			Publisher publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setAddress(publisherAddress);
			publisher.setPhone(publisherPhone);
			
			try {
				new AdministratorManagementSys().insertPublisher(publisher);
				message = "Publisher add succesfully";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Publisher add failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/updatePublisher": {
			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");
			int publisherId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setAddress(publisherAddress);
			publisher.setPhone(publisherPhone);
			publisher.setPublisherId(publisherId);
			try {
				new AdministratorManagementSys().updatePublisher(publisher);
				message = "Publisher update succesfully";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Publisher update failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/deletePublisher": {
			int publisherId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);
			try {
				new AdministratorManagementSys().deletePublisher(publisher);
				message = "Publisher delete succesfully";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Publisher delete failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/listPublishers": {
			try {
				List<Publisher> pbs = new AdministratorManagementSys().getAllPublishers();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), pbs);
			}catch (Exception e) {
				e.printStackTrace();
				message = "List Publishers failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		case "/addBook": {
			try {
				AdministratorManagementSys amSys = new AdministratorManagementSys();
				
				Book bk = new Book();
				String bookTitle = request.getParameter("bookTitle");
				bk.setTitle(bookTitle);
				
				int pubId = Integer.parseInt(request.getParameter("bookPublisher"));
				if (pubId != -1) {
					Publisher pub = amSys.getPublisherById(pubId);
					bk.setPublisher(pub);
				}
				
				String[] authStrs = request.getParameterValues("addedAuthors[]");
				if (authStrs != null) {
					List<Author> auths = new ArrayList<Author>();
					for(String s : authStrs) {
						auths.add(amSys.getAuthorById(Integer.parseInt(s)));
					}
					bk.setAuthors(auths);
				}
				
				String[] genreStrs = request.getParameterValues("addedGenres[]");
				if (genreStrs != null) {
					List<Genre> genres = new ArrayList<Genre>();
					for(String s : genreStrs) {
						genres.add(amSys.getGenreById(Integer.parseInt(s)));
					}
					bk.setGenres(genres);
				}
				
				amSys.insertBook(bk);
			} catch (Exception e) {
				e.printStackTrace();
				message = "Book add failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		case "/updateBook": {
			try {
				AdministratorManagementSys amSys = new AdministratorManagementSys();
				
				Book bk = new Book();
				bk.setBookId(Integer.parseInt(request.getParameter("bookId")));
				
				String bookTitle = request.getParameter("bookTitle");
				bk.setTitle(bookTitle);
				
				int pubId = Integer.parseInt(request.getParameter("bookPublisher"));
				if (pubId != -1) {
					Publisher pub = amSys.getPublisherById(pubId);
					bk.setPublisher(pub);
				}
				
				String[] authStrs = request.getParameterValues("updatedAuthors");
				if (authStrs != null) {
					List<Author> auths = new ArrayList<Author>();
					for(String s : authStrs) {
						auths.add(amSys.getAuthorById(Integer.parseInt(s)));
					}
					bk.setAuthors(auths);
				}
				
				String[] genreStrs = request.getParameterValues("updatedGenres");
				if (genreStrs != null) {
					List<Genre> genres = new ArrayList<Genre>();
					for(String s : genreStrs) {
						genres.add(amSys.getGenreById(Integer.parseInt(s)));
					}
					bk.setGenres(genres);
				}
				
				amSys.updateBook(bk);
			} catch (Exception e) {
				e.printStackTrace();
				message = "Book add failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		case "/deleteBook": {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			try {
				new AdministratorManagementSys().deleteBook(book);
			} catch (Exception e) {
				e.printStackTrace();
				message = "Book delete failed. Reason: " + e.getMessage();
			}
			break;
		}
		case "/listBooks": {
			try {
				List<Book> bks = new AdministratorManagementSys().getAllFullLoadBooks();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), bks);
			}catch (Exception e) {
				e.printStackTrace();
				message = "List books failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		case "/getBookById": {
			try {
				Book bk = new AdministratorManagementSys().getBookById(Integer.parseInt(request.getParameter("bookId")));
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), bk);
			}catch (Exception e) {
				e.printStackTrace();
				message = "Get book by id failed. Reason: " + e.getMessage();
				response.getWriter().write(message);
			}
			break;
		}
		default:
			break;
		}
	}

}
