package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@SpringBootApplication
public class BookListApplication {
	private static final Logger log = LoggerFactory.getLogger(BookListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookListApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository ) {
		return (args) -> {
			log.info("save a couple of books");
			
			
			crepository.save(new Category("IT"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Language"));
			crepository.save(new Category("Romance"));
			
			brepository.save(new Book("Java in a nutshell", "Hallison", 2018, "1234-341", 12.50, crepository.findByName("IT")));
			brepository.save(new Book("Science World", "T.Hawkings", 2018, "987-8974", 19.90, crepository.findByName("Life")));
			brepository.save(new Book("Spanish", "Patrick", 2019, "f65-38474", 25.50, crepository.findByName("Language")));
			brepository.save(new Book("Three sisters", "Micode", 2019, "654-541", 20, crepository.findByName("Romance")));	
			
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			

		};
	}
}
