package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Genre;

import java.time.LocalDate;

@Getter
@Setter
public class BookResponse {

    private Long id;
    private String name;
    private LocalDate publicationDate;
    private String description;
    private Genre genre;
    private String publisher;
    private int bookYear;//skolko let proshlo

    public BookResponse(Long id, String name, LocalDate publicationDate, String description, Genre genre, String publisher, int bookYear) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.description = description;
        this.genre = genre;
        this.publisher = publisher;
        this.bookYear = bookYear;
    }
}
