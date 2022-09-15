package peaksoft.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.AuthorRequest;
import peaksoft.dto.AuthorResponse;
import peaksoft.entity.Author;
import peaksoft.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorApi {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable Long id){
        return authorService.getById(id);
    }

    @PostMapping
    public AuthorResponse saveAuthor(@RequestBody AuthorRequest authorRequest){
        return authorService.saveAuthor(authorRequest);
    }

    @PutMapping("/update/{id}")
    public AuthorResponse update(@PathVariable Long id,@RequestBody AuthorRequest authorRequest){
        return authorService.updateAuthor(id,authorRequest);
    }

    @DeleteMapping("/delete/{id}")
    public AuthorResponse delete(@PathVariable Long id){
        return authorService.deleteAuthor(id);
    }

    @GetMapping
    public List<AuthorResponse> getAllAuthors(){
        return authorService.getAll();
    }
}
