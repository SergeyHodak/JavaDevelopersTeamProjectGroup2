package com.goit.javaonline5.note.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import com.goit.javaonline5.note.enums.AccessType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "note")
@Data
public class NoteModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type")
    private AccessType accessType;
}
