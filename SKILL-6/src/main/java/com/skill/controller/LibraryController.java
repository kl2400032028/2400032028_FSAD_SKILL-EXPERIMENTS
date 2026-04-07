package com.skill.controller;

import com.skill.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    private List<Book> books = new ArrayList<>();

    public LibraryController() {
        // Initialize with some dummy data
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 10.99));
        books.add(new Book(2, "1984", "George Orwell", 8.99));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee", 12.50));
    }

    // 2. Map to /welcome
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System!";
    }

    // 3. Map to /count
    @GetMapping("/count")
    public int getBookCount() {
        return books.size();
    }

    // 4. Map to /price
    @GetMapping("/price")
    public double getSamplePrice() {
        if (!books.isEmpty()) {
            return books.get(0).getPrice();
        }
        return 0.0;
    }

    // 5. Map to /books to return titles
    @GetMapping("/books")
    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            titles.add(book.getTitle());
        }
        return titles;
    }

    // 6. Map to /books/{id}
    @GetMapping("/books/{id}")
    public Book getBookDetails(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; // In a real scenario, returning 404 would be better
    }

    // 7. Map to /search with request parameter
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Search confirmation: You searched for the book titled '" + title + "'.";
    }

    // 8. Map to /author/{name}
    @GetMapping("/author/{name}")
    public String getAuthorInfo(@PathVariable String name) {
        return "Format confirmation: Information for author '" + name + "'.";
    }

    // 9. Map to /addbook with @PostMapping
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        books.add(book);
        return "Book '" + book.getTitle() + "' has been successfully added to the system!";
    }

    // 10. Map to /viewbooks
    @GetMapping("/viewbooks")
    public List<Book> getAllBooks() {
        return books;
    }
}
