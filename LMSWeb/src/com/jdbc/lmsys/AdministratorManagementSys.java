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
	
	public Book getBookById (int id) throws SQLException {
		BookDAO bkDAO = new BookDAO();
		return bkDAO.readOne(id);
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
	
	public List<Publisher> getAllPublisher () throws SQLException {
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
	
	public List<Author> getAllAuthor () throws SQLException {
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
}
