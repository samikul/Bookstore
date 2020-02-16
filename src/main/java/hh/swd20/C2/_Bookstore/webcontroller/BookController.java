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

@Controller
public class BookController {
	
	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan
	// luokan olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository; 
	
	/* 
	 * 
	 * findAll()
	 * LISTAA KIRJAT
	 * 
	 * */
	@RequestMapping(value = "/allbooks", method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		
		// haetaan tietokannasta kirjat listaan
		List<Book> books = (List<Book>) bookRepository.findAll();
		
		// laitetaan model-mappiin kirjalista templatea varten
		model.addAttribute("books", books);
		
		// palautetaan template booklist.html
		return "booklist";
	}
	
	/*
	 * 
	 * TYHJÄN KIRJALOMAKKEEN MUODOSTAMINEN
	 * 
	 * */
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		
		// luodaan tyhjä kirja-olio
		model.addAttribute("book", new Book());
		
		// palautetaan template bookform.html
		return "bookform";
	}
	
	/*
	 * 
	 * save()
	 * TALLENNA UUSI KIRJA
	 * 
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		
		// talletetaan yhden kirjan tiedot tietokantaan
		// save() osaa tehdä tarpeen mukaan sql insertin tai updaten riippuen tilanteesta
		bookRepository.save(book);
		
		// kutsutaan allbooks-endpointtia
		return "redirect:/allbooks";
	}
	
	/*
	 * 
	 * deleteById()
	 * POISTA KIRJA
	 * 
	 * */
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:/allbooks";
	}
	
}
