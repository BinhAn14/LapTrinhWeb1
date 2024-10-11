package com.example.ProjectAPI.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ProjectAPI.models.Book;

@RestController
@RequestMapping("/api") // Đặt tiền tố chung cho tất cả API
public class BookController {
    List<Book> listBooks = new ArrayList<>();

    public BookController() {
        // Khởi tạo danh sách sách mẫu
        Book b1 = new Book("1", "Harry Potter", "J. K. Rowling", "Bloomsbury Publishing");
        Book b2 = new Book("2", "Doraemon", "Fujiko F. Fujio", "Kim Đồng");
        listBooks.add(b1);
        listBooks.add(b2);
    }

    // 1. API GET danh sách sách
    @GetMapping("/books")
    public List<Book> getListBooks() {
        return listBooks;
    }

    // 2. API GET sách theo ID
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") String id) {
        for (Book b : listBooks) {
            if (b.getId().equals(id)) {
                return ResponseEntity.ok(b); // Trả về 200 OK
            }
        }
        return ResponseEntity.status(404).body("Book not found"); // Trả về thông báo lỗi
    }

    // 3. API DELETE sách theo ID
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String bookId) {
        Iterator<Book> iterator = listBooks.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(bookId)) {
                iterator.remove();
                return ResponseEntity.noContent().build(); // Trả về 204 No Content
            }
        }
        return ResponseEntity.status(404).body("Book not found");
    }

    // 4. API POST sách mới
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        listBooks.add(newBook);
        return ResponseEntity.status(201).body(newBook); // Trả về 201 Created
    }

    // 5. API PUT cập nhật sách theo ID
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") String bookId, @RequestBody Book updateBook) {
        for (Book book : listBooks) {
            if (book.getId().equals(bookId)) {
                book.setTitle(updateBook.getTitle());
                book.setAuthor(updateBook.getAuthor());
                book.setNhaXB(updateBook.getNhaXB());
                return ResponseEntity.ok(book); // Trả về 200 OK với sách đã cập nhật
            }
        }
        return ResponseEntity.status(404).body("Book not found");
    }
}
