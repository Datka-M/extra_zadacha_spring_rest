package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Genre;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_gen")
    @SequenceGenerator(name = "book_gen",sequenceName = "book_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private int bookYear;
    private String publisher;
    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private Author author;

}
