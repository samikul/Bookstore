package hh.swd20.C2._Bookstore.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.C2._Bookstore.domain.Book;
import hh.swd20.C2._Bookstore.domain.BookRepository;
import hh.swd20.C2._Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;

	@Autowired
	private CategoryRepository crepository;

	// Show all books
	@RequestMapping(value = "/allbooks", method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		List<Book> books = (List<Book>) brepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}

	// Add new book
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll()); 
		return "bookform";
	}

	// Save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		brepository.save(book);
		return "redirect:/allbooks";
	}

	// Delete book
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		brepository.deleteById(bookId);
		return "redirect:/allbooks";
	}

	// Edit book
	@RequestMapping(value = "/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId));
		model.addAttribute("categories", crepository.findAll()); 
		return "bookedit";
	}

	// REST service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}

	// REST service to get book by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return brepository.findById(id);
	}
}
