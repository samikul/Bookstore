package hh.swd20.C2._Bookstore.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "/allbooks", method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		
		// haetaan tietokannasta kirjat listaan
		List<Book> books = (List<Book>) bookRepository.findAll();
		
		// laitetaan model-mappiin kirjalista templatea varten
		model.addAttribute("books", books);
		
		// palautetaan template booklist.html
		return "booklist";
	}
}
