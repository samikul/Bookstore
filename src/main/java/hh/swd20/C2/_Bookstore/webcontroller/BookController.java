package hh.swd20.C2._Bookstore.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

		// haetaan tietokannasta kirjat listaan
		List<Book> books = (List<Book>) brepository.findAll();

		// laitetaan model-mappiin kirjalista templatea varten
		model.addAttribute("books", books);

		// palautetaan template booklist.html
		return "booklist";
	}

	// Add new book
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	// Create empty book-form
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {

		// luodaan tyhjä kirja-olio
		model.addAttribute("book", new Book());

		// palautetaan template bookform.html
		return "bookform";
	}

	// Save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {

		// talletetaan yhden kirjan tiedot tietokantaan
		// save() osaa tehdä tarpeen mukaan sql insertin tai updaten riippuen tilanteesta
		brepository.save(book);

		// kutsutaan allbooks-endpointtia
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

		return "bookedit";
	}
}
