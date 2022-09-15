package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Genre;

import java.time.LocalDate;

@Getter
@Setter
public class BookRequest {

    private String name;
    private LocalDate publicationDate;
    private String description;
    private Genre genre;
    private String publisher;
    private Long authorId;

}
