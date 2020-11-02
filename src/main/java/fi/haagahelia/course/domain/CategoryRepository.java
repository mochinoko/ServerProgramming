package fi.haagahelia.course.domain;
import java.util.List;
import fi.haagahelia.course.domain.Category;

import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long>{
	Category findByName(String name);
}
