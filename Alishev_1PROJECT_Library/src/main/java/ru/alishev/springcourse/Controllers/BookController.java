package ru.alishev.springcourse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.DAO.BookDAO;
import ru.alishev.springcourse.DAO.PersonDAO;
import ru.alishev.springcourse.Models.Book;
import ru.alishev.springcourse.Models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final  BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String listBook(Model model){
        model.addAttribute("books",bookDAO.addListBook());
        return "book/listBook";
    }


    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("person") Person person){
        Book book = bookDAO.show(id);
        if (book == null) {
            return "redirect:/book";
        }
        model.addAttribute("book", book);
        if (book.getId_person() == 0) {
            model.addAttribute("people", personDAO.addListPerson());
        } else {
            model.addAttribute("owner", personDAO.show(book.getId_person()));
        }
        return "book/showBook";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        //Возвращием форму на создание нового человека
        return "book/new";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult){
        //проверяем есть ли ошибки валидации, если есть возвращаем форму заново.
        if(bindingResult.hasErrors())
            return "book/new";
        //Если ошибок нету отправляем SQL запрос на добавление книги в БД.
        bookDAO.save(book);
        return "redirect:/book";
    }

    //Адресс в виде ID, для того что бы получить ID книги которую редактируем из запроса,
    //и  далее получить ее с БД с помошью ID - bookDAO.show(id)
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        //Получаем с помощью метода show человека по id, и передаем его через модель в форму для редактирования.
        model.addAttribute("book",bookDAO.show(id));
        //возвращаем форму
        return "book/edit";
    }

    //Адресс в виде ID, для того что бы указать книгу с каким ID заменяем в БД.
    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "book/edit";
        bookDAO.update(id,book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person")Person person){
        bookDAO.assign(id,person);
        return "redirect:/book/{id}";
    }

}
