package hh.swd20.C2._Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.C2._Bookstore.webcontroller.BookController;
import hh.swd20.C2._Bookstore.webcontroller.CategoryController;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private BookController BookController;

	@Autowired
	private CategoryController CategoryController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(BookController).isNotNull();
		assertThat(CategoryController).isNotNull();
	}

}