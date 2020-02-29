package hh.swd20.C2._Bookstore.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.C2._Bookstore.domain.Category;
import hh.swd20.C2._Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository crepository;
	
	// Show all categories
	@RequestMapping(value = "/allcategories", method = RequestMethod.GET)
	public String getAllCategories(Model model) {

		// haetaan tietokannasta kategoriat listaan
		List<Category> categories = (List<Category>) crepository.findAll();

		// laitetaan model-mappiin kategorialista templatea varten
		model.addAttribute("categories", categories);

		// palautetaan template categorylist.html
		return "categorylist";
	}
	
	// Add new category
	@RequestMapping(value="/newcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoryform";
	}
	
	// Save new category
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute Category category) {

		// talletetaan yhden kategorian tiedot tietokantaan
		// save() osaa tehdä tarpeen mukaan sql insertin tai updaten riippuen tilanteesta
		crepository.save(category);

		// kutsutaan allbooks-endpointtia
		return "redirect:/allcategories";
	}
}
