package pl.phanta.springnativegradle.infrastructure.repostitories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.phanta.springnativegradle.domain.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<Book> findByIsbn(long isbn);

    void deleteByIsbn(long isbn);

}
