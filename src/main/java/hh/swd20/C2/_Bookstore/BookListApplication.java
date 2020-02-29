package hh.swd20.C2._Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.C2._Bookstore.domain.Book;
import hh.swd20.C2._Bookstore.domain.BookRepository;
import hh.swd20.C2._Bookstore.domain.Category;
import hh.swd20.C2._Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookListApplication {
	private static final Logger log = LoggerFactory.getLogger(BookListApplication.class); // uusi loggerattribuutti
	
	public static void main(String[] args) {
		SpringApplication.run(BookListApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			
			log.info("tallennetaan testikategoriat");	
			crepository.save(new Category("Classics"));
			crepository.save(new Category("Crime and horror"));
			crepository.save(new Category("Sci-fi and fantasy"));
			crepository.save(new Category("Biography"));
			
			log.info("tallennetaan testikirjat");
			brepository.save(new Book("Sinuhe egyptiläinen", "Mika Waltari", 1949, "978-951-0-09875-2", 29.90, crepository.findByName("Classics").get(0)));		
			brepository.save(new Book("Ulkopuolinen", "Stephen King", 2018, "978-952-0-40341-6", 29.90, crepository.findByName("Crime and horror").get(0)));
			brepository.save(new Book("Taru Sormusten herrasta", "J.R.R. Tolkien", 1975, "978-951-0-33337-2", 30.00, crepository.findByName("Sci-fi and fantasy").get(0)));
			brepository.save(new Book("Nancy", "Deborah Spungen", 1983, "978-951-2-40459-9", 10.90, crepository.findByName("Biography").get(0)));

			log.info("haetaan kaikki kirjat ja kategoriat");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			
		};
	}
	
}
