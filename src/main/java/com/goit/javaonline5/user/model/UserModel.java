package com.goit.javaonline5.user.model;

import com.goit.javaonline5.note.model.NoteModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "users")
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

    @Pattern(
            regexp = "[A-Za-z\\d!#$%&'*+/=?^_`.{|}~-]+@[a-z\\d]+.[a-z\\d]+",
            message = "Invalid email!")
    @Column(name = "email")
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
