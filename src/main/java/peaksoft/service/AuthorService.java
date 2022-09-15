package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.AuthorRequest;
import peaksoft.dto.AuthorResponse;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.AuthorRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponse saveAuthor(AuthorRequest authorRequest){
        Author author = new Author();
        author.setFullName(authorRequest.getFirstName()+" "+authorRequest.getLastName());
        author.setNationality(authorRequest.getNationality());
        author.setGender(authorRequest.getGender());
        author.setDateOfBirth(authorRequest.getDateOfBirth());
        Author author1 = authorRepository.save(author);
        String[] name = author1.getFullName().split(" ",2);
        return new AuthorResponse(author1.getId(),name[0],name[1],author1.getFullName(),
                author1.getNationality(),author1.getGender(),
                (Period.between(author1.getDateOfBirth(),LocalDate.now()).getYears()));
    }


    public AuthorResponse getById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Author with %d id not found",id)));
        String[] array = author.getFullName().split(" ",2);

        return new AuthorResponse(author.getId(),array[0],array[1],author.getFullName(),
                author.getNationality(),author.getGender(),
                Period.between(author.getDateOfBirth(),LocalDate.now()).getYears());
    }

    public AuthorResponse updateAuthor(Long id,AuthorRequest authorRequest){
        Author author  = authorRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Author with %d id not found",id)));
        Author author1 = saveUpdate(author,authorRequest);
        authorRepository.save(author1);
        String[] name = author.getFullName().split(" ",2);
        return new AuthorResponse(author.getId(),name[0],name[1],author.getFullName(),
                author.getNationality(),author.getGender(),
                (Period.between(author.getDateOfBirth(),LocalDate.now()).getYears()));
    }

    public Author saveUpdate(Author author, AuthorRequest request){
        author.setFullName(request.getFirstName()+" "+request.getLastName());
        author.setNationality(request.getNationality());
        author.setGender(request.getGender());
        author.setDateOfBirth(request.getDateOfBirth());
        return authorRepository.save(author);
    }


    public AuthorResponse deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Author with %d id not found",id)));
        for (Book book: author.getBooks()) {
            book.setAuthor(null);
        }
        authorRepository.delete(author);
        String[] name = author.getFullName().split(" ",2);
        return new AuthorResponse(author.getId(),name[0],name[1],author.getFullName(),
                author.getNationality(),author.getGender(),
                (Period.between(author.getDateOfBirth(),LocalDate.now()).getYears()));
    }

    public List<AuthorResponse> getAll(){
        List<AuthorResponse> getAuthorResponse = new ArrayList<>();

        for (Author a :authorRepository.findAll()) {
            String[] name = a.getFullName().split(" ",2);
            getAuthorResponse.add(new AuthorResponse(a.getId(),
                    name[0],name[1],
                    a.getFullName(),a.getNationality(),
                    a.getGender(),(Period.between(a.getDateOfBirth(),LocalDate.now()).getYears())));
        }
        return getAuthorResponse;
    }
}
