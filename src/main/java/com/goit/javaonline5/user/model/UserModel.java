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
    protected UUID id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "email",unique=true)
    @Email(message = "Please enter a valid e-mail address")
    @NotBlank
    protected String email;

    @Column(name = "password")
    protected String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_note",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    protected Set<NoteModel> notes;


}
