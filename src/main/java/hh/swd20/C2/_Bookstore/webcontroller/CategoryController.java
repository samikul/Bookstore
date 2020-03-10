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

import hh.swd20.C2._Bookstore.domain.Category;
import hh.swd20.C2._Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository crepository;

	// Show all categories
	@RequestMapping(value = "/allcategories", method = RequestMethod.GET)
	public String getAllCategories(Model model) {
		List<Category> categories = (List<Category>) crepository.findAll();
		model.addAttribute("categories", categories);
		return "categorylist";
	}

	// Add new category
	@RequestMapping(value="/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoryform";
	}

	// Save new category
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute Category category) {
		crepository.save(category);
		return "redirect:/allcategories";
	}

	// REST service to get all categories
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public @ResponseBody List<Category> categoryListRest() {
		return (List<Category>) crepository.findAll();
	}

	// REST service to get category by id
	@RequestMapping(value="/category/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long id) {
		return crepository.findById(id);
	}
}
