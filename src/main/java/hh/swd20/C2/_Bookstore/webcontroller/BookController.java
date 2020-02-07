package hh.swd20.C2._Bookstore.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getBooks(Model model) {
		return "index";
	}
}
