package fi.haagahelia.course.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.CategoryRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	// Show all students
	@RequestMapping(value="/bookList")
	public String bookList(Model model) {	
		model.addAttribute("students", brepository.findAll());
	    return "bookList";
	}
	
	// RESTful service to get all books
	@RequestMapping(value="/books", method=RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest(){
		return(List<Book>)brepository.findAll();
	}
	// RESTful service to get book by id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return brepository.findById(bookId);
	}
}
