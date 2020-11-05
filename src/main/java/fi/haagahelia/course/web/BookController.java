package fi.haagahelia.course.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	@RequestMapping(value="/booklist")
	public String bookList(Model model) {	
		model.addAttribute("books", brepository.findAll());
	    return "bookList";
	}
	//Add books
	@RequestMapping(value = "/addbook")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
		brepository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value = "/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBook(@PathVariable("id") long id, Model model){
		model.addAttribute("book", brepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)	
	public String deletBook(@PathVariable("id") Long id, Model model) {
		brepository.deleteById(id);
		return "redirect:../booklist";
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
	
	@RequestMapping(value="/login")
	public String login() {
	    return "login";
	}
	@GetMapping(value = "/saveEdit")
	public String saveEdit(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}
}
