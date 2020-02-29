package hh.swd20.C2._Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	// CrudRepository-rajapinnan parametrisoinnissa annetaan Entity-luokan nimi: tässä Book
	// ja toisena parametrina pääavainsarakkeen luokkatietotyyppi: tässä Long
	
	// BookRepository periytyy (extends) CrudRepositorysta ja perii mm. metodiesittelyt
	// findAll(), findById(), save(), deleteById()
	
	List<Book> findByTitle(String title);

}
