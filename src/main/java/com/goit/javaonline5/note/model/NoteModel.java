package com.goit.javaonline5.note.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "note")
@Data
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type")
    private AccessType accessType;
}
