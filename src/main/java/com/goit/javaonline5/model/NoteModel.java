package com.goit.javaonline5.model;

import com.goit.javaonline5.enums.AccessType;
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

    @Column(name = "content")
    private String content;

    @Column(name = "access_type")
    private AccessType accessType;
}
