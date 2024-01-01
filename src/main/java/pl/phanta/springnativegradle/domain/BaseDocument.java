package pl.phanta.springnativegradle.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter(AccessLevel.PACKAGE)
abstract class BaseDocument {

    @Id
    private String id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}
