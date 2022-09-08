package com.goit.javaonline5.user.model;

import com.goit.javaonline5.note.model.NoteModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@RequiredArgsConstructor
@Entity
@Setter
@Getter
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

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

    public UserModel(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
