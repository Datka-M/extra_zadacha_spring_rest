package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Gender;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class AuthorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String nationality;
    private Gender gender;
    private int age;

    public AuthorResponse(Long id, String firstName, String lastName, String fullName, String nationality, Gender gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.nationality = nationality;
        this.gender = gender;
        this.age = age;
    }
}
