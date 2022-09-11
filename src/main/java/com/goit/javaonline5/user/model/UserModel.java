package com.goit.javaonline5.user.model;

import com.goit.javaonline5.note.model.NoteModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Entity
@Setter
@Getter
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email",unique=true)
    @Email(message = "Please enter a valid e-mail address")
    @NotBlank
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<NoteModel> notes;
}
