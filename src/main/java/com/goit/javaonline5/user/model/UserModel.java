package com.goit.javaonline5.user.model;

import com.goit.javaonline5.note.model.NoteModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@RequiredArgsConstructor
@Entity
@Data
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_note",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<NoteModel> notes;
}
