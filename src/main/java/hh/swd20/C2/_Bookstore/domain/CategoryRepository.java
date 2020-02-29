package hh.swd20.C2._Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByName(String name);

}


