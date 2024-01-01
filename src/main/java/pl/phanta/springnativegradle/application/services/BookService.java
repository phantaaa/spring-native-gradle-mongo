package pl.phanta.springnativegradle.application.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.phanta.springnativegradle.application.exceptions.ResourceNotFoundException;
import pl.phanta.springnativegradle.application.mappers.BookMapper;
import pl.phanta.springnativegradle.domain.Book;
import pl.phanta.springnativegradle.infrastructure.repostitories.BookRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public Book findBookByIsbn(long isbn) {
        log.info("Finding book by ISBN={}", isbn);
        return bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new ResourceNotFoundException("Couldn't find a book with isbn=" + isbn));
    }

    public Page<Book> findBooks(int pageNum, int limit, String sortField, String sortOrder) {
        log.info("Finding books page={} limit={} sortField={} sortOrder={}", pageNum, limit, sortField, sortOrder);
        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("desc") ? Sort.Order.desc(sortField) : Sort.Order.asc(sortField));
        Pageable pageable = PageRequest.of(pageNum, limit, sort);
        return bookRepository.findAll(pageable);
    }

    public void addNewBook(Book book) {
        log.info("Adding book={}", book.toString());
        bookRepository.save(book);
    }

    public void deleteBook(long isbn) {
        log.info("Deleting book={}", isbn);
        bookRepository.deleteByIsbn(isbn);
    }

    public void updateBook(long isbn, Book book) {
        log.info("Updating book={}", isbn);
        Book dbBook = bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new ResourceNotFoundException("Couldn't find a book with isbn=" + isbn));
        bookMapper.updateDocumentFromBook(book, dbBook);
        bookRepository.save(dbBook);
    }

}
