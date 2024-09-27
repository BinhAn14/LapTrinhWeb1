package com.example.ProjectAPI.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ProjectAPI.models.Book;

@Controller
public class BookController {
    List<Book> listBooks = new ArrayList<>();

    public BookController() {
        // Khởi tạo danh sách sách mẫu
        Book b1 = new Book("1", "Harry Potter", "J. K. Rowling", "Bloomsbury Publishing");
        Book b2 = new Book("2", "Doraemon", "Fujiko F. Fujio ", "Kim Đồng");
        listBooks.add(b1);
        listBooks.add(b2);
    }

    // 1. API GET danh sách sách
    @GetMapping("/books")
    @ResponseBody
    public List<Book> getListBooks() {
        return listBooks;
    }

    // 2. API GET sách theo ID
    @GetMapping("/book/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        for (Book b : listBooks) {
            if (b.getId().equals(id)) {
                return ResponseEntity.status(200).body(b);
            }
        }
        return ResponseEntity.status(404).body(null); 
    }

    // 3. API DELETE sách theo ID
    @DeleteMapping("/books/{id}")
    @ResponseBody
    public ResponseEntity<List<Book>> deleteBook(@PathVariable("id") String bookId) {
        Iterator<Book> iterator = listBooks.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(bookId)) {
                iterator.remove(); // Xóa sách bằng iterator
                return ResponseEntity.status(204).body(listBooks); 
            }
        }
        return ResponseEntity.status(404).body(null); 
    }

    // 4. API POST sách mới
    @PostMapping("/book")
    @ResponseBody
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        listBooks.add(newBook);
        return ResponseEntity.status(201).body(newBook); 
    }

    // 5. API PUT cập nhật sách theo ID
    @PutMapping("/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> updateBook(@PathVariable("id") String bookId, @RequestBody Book updateBook) {
        for (Book book : listBooks) {
            if (book.getId().equals(bookId)) {
                book.setTitle(updateBook.getTitle());
                book.setAuthor(updateBook.getAuthor());
                book.setNhaXB(updateBook.getNhaXB()); 

                return ResponseEntity.status(200).body(book); 
            }
        }

        return ResponseEntity.status(404).body(null); 
    }
}
