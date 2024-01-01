package pl.phanta.springnativegradle.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.phanta.springnativegradle.api.BooksApiDelegate;
import pl.phanta.springnativegradle.application.mappers.BookMapper;
import pl.phanta.springnativegradle.application.services.BookService;
import pl.phanta.springnativegradle.model.BookDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BooksController implements BooksApiDelegate {

    private final BookMapper bookMapper;

    private final BookService bookService;

    @Override
    public ResponseEntity<List<BookDto>> findBooks(Integer page, Integer limit, String sort, String order) {
        return ResponseEntity.ok(bookMapper.toBookDtos(bookService.findBooks(page, limit, sort, order).toList()));
    }

    @Override
    public ResponseEntity<Void> addBook(BookDto book) {
        bookService.addNewBook(bookMapper.toBook(book));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long bookIsbn) {
        bookService.deleteBook(bookIsbn);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookDto> findBookByIsbn(Long bookIsbn) {
        return ResponseEntity.ok(bookMapper.toDto(bookService.findBookByIsbn(bookIsbn)));
    }

    @Override
    public ResponseEntity<Void> updateBook(Long bookIsbn, BookDto book) {
        bookService.updateBook(bookIsbn, bookMapper.toBook(book));
        return ResponseEntity.noContent().build();
    }

}
