package fi.haagahelia.course;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repo;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repo.findById("Science World");
		System.out.println("OK");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle().contains("Science"));
		
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("harry potter and the cursed child", "J.K Rolling", 2016, "987-854", 26, new Category("Comic"));
		repo.save(book);
		assertThat(book.getId()).isNotNull();
	}

}
