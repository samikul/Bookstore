package hh.swd20.C2._Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.C2._Bookstore.domain.Book;
import hh.swd20.C2._Bookstore.domain.BookRepository;
import hh.swd20.C2._Bookstore.domain.Category;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository brepository;

	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = brepository.findByTitle("Ulkopuolinen");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Stephen King");
	}
	
	@Test
	public void createNewBookShouldCreateANewBookWithCategory() {
		Book book = new Book("KirjanNimi", "Kirjailija", 2020, "123-123-123", 99.99, new Category("Kategoria"));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteByIdShouldDeleteBookWithIdFive() {
		brepository.deleteById((long) 5);
	}
}