package pl.phanta.springnativegradle.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import pl.phanta.springnativegradle.domain.Book;
import pl.phanta.springnativegradle.model.BookDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toBook(BookDto bookDto);

    void updateDocumentFromBook(Book source, @MappingTarget Book target);

    void updateDocumentFromDto(BookDto dto, @MappingTarget Book document);

    List<BookDto> toBookDtos(List<Book> books);

    List<Book> toBooks(List<BookDto> bookDtos);

}
