/**
 * AdministratorManagementSys.java
 * @author Ziyi Chen 
 * <br> Email: <a href="mailto:ziyi_chen@gcitsolutions.com">ziyi_chen@gcitsolutions.com</a>
 * <br> Created on Sep 29, 2015
 */
package com.jdbc.lmsys;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.lmdao.AuthorDAO;
import com.jdbc.lmdao.BookDAO;
import com.jdbc.lmdao.BookLoansDAO;
import com.jdbc.lmdao.BorrowerDAO;
import com.jdbc.lmdao.BranchDAO;
import com.jdbc.lmdao.GenreDAO;
import com.jdbc.lmdao.PublisherDAO;
import com.jdbc.lmdo.Author;
import com.jdbc.lmdo.Book;
import com.jdbc.lmdo.BookLoans;
import com.jdbc.lmdo.Borrower;
import com.jdbc.lmdo.Branch;
import com.jdbc.lmdo.Genre;
import com.jdbc.lmdo.Publisher;

public class AdministratorManagementSys {

	public List<Book> getAllBooks () throws SQLException {
		BookDAO bkDAO = new BookDAO();
		return bkDAO.readAll();
	}
	
	public List<Book> getAllFullLoadBooks () throws SQLException {
		BookDAO bkDAO = new BookDAO();
		List<Book> bks = bkDAO.readAll();
		for (Book bk : bks) {
			bk.setAuthors(new AuthorDAO().readAllByBook(bk));
			bk.setGenres(new GenreDAO().readAllByBook(bk));
		}
		return bks;
	}
	
	public Book getBookById (int id) throws SQLException {
		BookDAO bkDAO = new BookDAO();
		return bkDAO.readOne(id);
	}
	
	public Book getFullLoadBookById (int id) throws SQLException {
		BookDAO bkDAO = new BookDAO();
		Book bk = bkDAO.readOne(id);
		bk.setAuthors(new AuthorDAO().readAllByBook(bk));
		bk.setGenres(new GenreDAO().readAllByBook(bk));
		return bk;
	}
	
	public List<Author> getAllValidAuthorsByBook(Book bk) throws SQLException {
		List<Author> res = new ArrayList<Author>();
		AuthorDAO authDAO = new AuthorDAO(); 
		List<Author> addedAths = authDAO.readAllByBook(bk);
		bk.setAuthors(addedAths);
		res = authDAO.readAll();
		res.removeAll(addedAths);
		return res;
	}
	
	public List<Genre> getAllValidGenresByBook(Book bk) throws SQLException {
		List<Genre> res = new ArrayList<Genre>();
		GenreDAO genDAO = new GenreDAO(); 
		List<Genre> addedGens = genDAO.readAllByBook(bk);
		bk.setGenres(addedGens);
		res = genDAO.readAll();
		res.removeAll(addedGens);
		return res;
	}
	
	public List<Publisher> getAllValidPublishersByBook(Book bk) throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO(); 
		List<Publisher> res = pubDAO.readAll();
		res.remove(bk.getPublisher());
		return res;
	}
	
	public void updateBook (Book bk) throws SQLException {
		BookDAO bkDAO = new BookDAO();
		bkDAO.update(bk);
	}
	
	public void deleteBook (Book bk) throws SQLException {
		BookDAO bkDAO = new BookDAO();
		bkDAO.delete(bk);
	}
	
	public void insertBook (Book bk) throws SQLException {
		BookDAO bkDAO = new BookDAO();
		bkDAO.insert(bk);
	}
	
	public List<Publisher> getAllPublishers () throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO();
		return pubDAO.readAll();
	}
	
	public Publisher getPublisherById (int id) throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO();
		return pubDAO.readOne(id);
	}
	
	public void updatePublisher (Publisher pub) throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO();
		pubDAO.update(pub);
	}
	
	public void deletePublisher (Publisher pub) throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO();
		pubDAO.delete(pub);
	}
	
	public void insertPublisher (Publisher pub) throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO();
		pubDAO.insert(pub);
	}
	
	public List<Author> getAllAuthors () throws SQLException {
		AuthorDAO authDAO = new AuthorDAO();
		return authDAO.readAll();
	}
	
	public Author getAuthorById (int id) throws SQLException {
		AuthorDAO authDAO = new AuthorDAO();
		return authDAO.readOne(id);
	}
	
	public void updateAuthor (Author auth) throws SQLException {
		AuthorDAO authDAO = new AuthorDAO();
		authDAO.update(auth);
	}
	
	public void deleteAuthor (Author auth) throws SQLException {
		AuthorDAO authDAO = new AuthorDAO();
		authDAO.delete(auth);
	}
	
	public void insertAuthor (Author auth) throws SQLException {
		AuthorDAO authDAO = new AuthorDAO();
		authDAO.insert(auth);
	}
	
	public List<Branch> getAllBranch () throws SQLException {
		BranchDAO bhDAO = new BranchDAO();
		return bhDAO.readAll();
	}
	
	public void updateBranch (Branch bh) throws SQLException {
		BranchDAO bhDAO = new BranchDAO();
		bhDAO.update(bh);
	}
	
	public void deleteBranch (Branch bh) throws SQLException {
		BranchDAO bhDAO = new BranchDAO();
		bhDAO.delete(bh);
	}
	
	public void insertBranch (Branch bh) throws SQLException {
		BranchDAO bhDAO = new BranchDAO();
		bhDAO.insert(bh);
	}
	
	public List<Borrower> getAllBorrower () throws SQLException {
		BorrowerDAO brDAO = new BorrowerDAO();
		return brDAO.readAll();
	}
	
	public void updateBorrower (Borrower br) throws SQLException {
		BorrowerDAO brDAO = new BorrowerDAO();
		brDAO.update(br);
	}
	
	public void deleteBorrower (Borrower br) throws SQLException {
		BorrowerDAO brDAO = new BorrowerDAO();
		brDAO.delete(br);
	}
	
	public void insertBorrower (Borrower br) throws SQLException {
		BorrowerDAO brDAO = new BorrowerDAO();
		brDAO.insert(br);
	}
	
	public List<BookLoans> getAllBookLoans () throws SQLException {
		BookLoansDAO blDAO = new BookLoansDAO();
		return blDAO.readAll();
	} 
	
	public void overrideDueDate (BookLoans bl, Date date) throws SQLException {
		bl.setDueDate(date);
		BookLoansDAO blDAO = new BookLoansDAO();
		blDAO.update(bl);
	}
	
	public List<Genre> getAllGenres () throws SQLException {
		return new GenreDAO().readAll();
	}
	
	public Genre getGenreById (int id) throws SQLException {
		return new GenreDAO().readOne(id);
	}

	public List<Book> searchSizedFullLoadBooks(int pageNo, int pageSize, String searchText) throws SQLException {
		List<Book> bks = new BookDAO().searchSizedBooks(pageNo, pageSize, searchText);
		AuthorDAO auDAO = new AuthorDAO();
		GenreDAO genDAO = new GenreDAO();
		for (Book bk : bks) {
			bk.setAuthors(auDAO.readAllByBook(bk));
			bk.setGenres(genDAO.readAllByBook(bk));
		}
		return bks;
	}
	
	public List<Book> getSizedFullLoadBooks() throws SQLException {
		List<Book> bks = new BookDAO().readAll();
		AuthorDAO auDAO = new AuthorDAO();
		GenreDAO genDAO = new GenreDAO();
		for (Book bk : bks) {
			bk.setAuthors(auDAO.readAllByBook(bk));
			bk.setGenres(genDAO.readAllByBook(bk));
		}
		return bks;
	}
	
	public int countBook (String searchText) throws SQLException {
		return new BookDAO().countBooks(searchText);
	}
	
	public List<Author> searchAuthors(int pageNo, int pageSize, String search) throws SQLException {
		AuthorDAO authDAO = new AuthorDAO();
		return authDAO.searchSizedAuthors(pageNo, pageSize, search);
	}
	public int countAuthor(String search) throws SQLException {
		return new AuthorDAO().countAuthors(search);
	}

	public List<Publisher> searchPublishers(int pageNo, int pageSize,
			String search) throws SQLException {
		PublisherDAO pubDAO = new PublisherDAO();
		return pubDAO.searchSizedPublishers(pageNo, pageSize, search);
	}

	public int countPublisher(String search) throws SQLException {
		return new PublisherDAO().countPublishers(search);
	}
}
