package com.library.Management.Services;

import com.library.Management.Entities.Book;
import com.library.Management.Repository.BookRepository;
import com.library.Management.Exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Retrieve all books
    @Cacheable("books")
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Retrieve a specific book by ID
    @Cacheable(value = "book", key = "#id")
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    // Add a new book
    @CacheEvict(value = "books", allEntries = true)
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Update an existing book
    @CacheEvict(value = {"book", "books"}, key = "#id")
    @Transactional
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        // Update the existing book with new data
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setIsbn(book.getIsbn());

        return bookRepository.save(existingBook);
    }

    // Delete a book
    @CacheEvict(value = {"book", "books"}, key = "#id")
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}