package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.course.web.BookController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookListApplicationTests {
	
	@Autowired
	private	BookController controller;

	@Test
	public void contextLoads() throws Exception  {
		assertThat(controller).isNotNull();
	}

}
