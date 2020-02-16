package hh.swd20.C2._Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.C2._Bookstore.domain.Book;
import hh.swd20.C2._Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookcrudApplication {

	private static final Logger log = LoggerFactory.getLogger(BookcrudApplication.class); // uusi loggerattribuutti
	
	public static void main(String[] args) {
		SpringApplication.run(BookcrudApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("tallennetaan testidataa");
			bookRepository.save(new Book("Sinuhe egyptiläinen", "Mika Waltari", 1949, "978-951-0-09875-2", 29.90));
			bookRepository.save(new Book("Tuntematon sotilas", "Väinö Linna", 1954, "978-951-0-44578-5", 17.90));
			bookRepository.save(new Book("Seitsemän veljestä", "Aleksis Kivi", 1870, "978-952-2-15444-6", 30.40));
			log.info("haetaan kaikki kirjat");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
}