package com.goit.javaonline5.note.model;

import com.goit.javaonline5.authorization.UserModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "note")
@Getter
@Setter
@ToString
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contents")
    private String content;

    @Column(name = "contents_short")
    private String contentsShort;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccessType accessTypeStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel users;
}
