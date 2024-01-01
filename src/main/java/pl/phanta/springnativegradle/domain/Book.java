package pl.phanta.springnativegradle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "books")
public class Book extends BaseDocument {

    private String title;

    private String description;

    @Indexed(unique = true)
    private long isbn;

    private BigDecimal price;

    private boolean available;

}
