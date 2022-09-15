package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.BookRequest;
import peaksoft.dto.BookResponse;
import peaksoft.entity.Book;
import peaksoft.service.BookService;

import java.util.List;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookApi {

    private final BookService bookService;

    @PostMapping()
    public BookResponse saveBook(@RequestBody BookRequest request){
     return bookService.save(request);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id,@RequestBody BookRequest bookRequest){
        return bookService.updateBook(id,bookRequest);
    }

    @DeleteMapping("/{id}")
    public BookResponse delete(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/byAuthorId/{id}")
    public List<BookResponse>getAllByAuthorId(@PathVariable Long id){
        return bookService.getAllBookByAuthorId(id);
    }
}
