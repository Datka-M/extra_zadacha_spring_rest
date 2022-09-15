package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.BookRequest;
import peaksoft.dto.BookResponse;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.AuthorRepository;
import peaksoft.repository.BookRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

//    public Book create(Long authorId,BookRequest bookRequest){
//        Book book = new Book();
//        book.setName(bookRequest.getName());
//        book.setDescription(book.getDescription());
//        book.setBookYear(Period.between(bookRequest.getPublicationDate(), LocalDate.now()).getYears());
//        book.setPublisher(bookRequest.getPublisher());
//        book.setPublicationDate(bookRequest.getPublicationDate());
//        book.setGenre(bookRequest.getGenre());
//        Author author = authorRepository.findById(authorId).orElseThrow(()->
//                new NotFoundException(String.format("Author with %d id not found",authorId)));
//        book.setAuthor(author);
//        return book;
//    }
//
//    public BookResponse response(Book book){
//        BookResponse bookResponse = new BookResponse();
//        bookResponse.setId(book.getId());
//        bookResponse.setName(book.getName());
//        bookResponse.setPublicationDate(book.getPublicationDate());
//        bookResponse.setDescription(book.getDescription());
//        bookResponse.setGenre(book.getGenre());
//        bookResponse.setPublisher(book.getPublisher());
//        bookResponse.setBookYear(book.getBookYear());
//        return bookResponse;
//    }
//
//    public BookResponse save(Long authorId,BookRequest request){
//        Book book = create(authorId,request);
//        return response(bookRepository.save(book));
//    }

    public BookResponse save(BookRequest bookRequest){
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setDescription(book.getDescription());
        book.setBookYear(Period.between(bookRequest.getPublicationDate(), LocalDate.now()).getYears());
        book.setPublisher(bookRequest.getPublisher());
        book.setPublicationDate(bookRequest.getPublicationDate());
        book.setGenre(bookRequest.getGenre());
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow(()->
                new NotFoundException(String.format("Author with %d id not found",bookRequest.getAuthorId())));
        book.setAuthor(author);
        author.addBook(book);
        Book book1 = bookRepository.save(book);
        return new BookResponse(book1.getId(),book1.getName(),book1.getPublicationDate(),
                book1.getDescription(),book1.getGenre(),book1.getPublisher(),book1.getBookYear());
    }

    public BookResponse getBookById(Long id){
        Book book =  bookRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Book with %d id not found",id)));
        return new BookResponse(book.getId(),book.getName(),book.getPublicationDate(), book.getDescription(),
                book.getGenre(),book.getPublisher(),book.getBookYear());
    }

    public BookResponse updateBook(Long id,BookRequest request){
        Book book = bookRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Book with %d id not found",id)));
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setBookYear(Period.between(request.getPublicationDate(),LocalDate.now()).getYears());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        book.setGenre(request.getGenre());
        Book book1 = bookRepository.save(book);
        return new BookResponse(book1.getId(), book1.getName(), book1.getPublicationDate(), book1.getDescription(),
                book1.getGenre(), book1.getPublisher(), book1.getBookYear());
    }


    public BookResponse deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Book with %d id not found",id)));
        bookRepository.delete(book);
        return new BookResponse(book.getId(), book.getName(), book.getPublicationDate(), book.getDescription(),
                book.getGenre(), book.getPublisher(), book.getBookYear());
    }

    public List<BookResponse> getAllBooks(){
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book: bookRepository.findAll()) {
            bookResponses.add(new BookResponse(book.getId(),
                    book.getName(),
                    book.getPublicationDate(),
                    book.getDescription(),
                    book.getGenre(),
                    book.getPublisher(),
                    book.getBookYear()));
        }
        return bookResponses;
    }

    public List<BookResponse> getAllBookByAuthorId(Long id){
        List<BookResponse> getBooks = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            getBooks.add(new BookResponse(book.getId(),
                    book.getName(),
                    book.getPublicationDate(),
                    book.getDescription(),
                    book.getGenre(),
                    book.getPublisher(),
                    book.getBookYear()));
        }
        return getBooks;
    }

//   "name":"based on true story",
//           "publicationDate":"1970-02-02",
//           "description":"it's book",
//           "genre":"HORROR",
//           "publisher":"Datka",
//           "authorId":2
}
