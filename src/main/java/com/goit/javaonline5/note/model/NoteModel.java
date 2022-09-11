package com.goit.javaonline5.note.model;

import com.goit.javaonline5.note.enums.AccessType;
import com.goit.javaonline5.user.model.UserModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min = 5, max = 100, message = "Імʼя рядка повинно бути між 5 та 100 символами")
    private String name;

    @Column(name = "content")
    @Size(min = 5, max = 10000, message = "Текст замітки повинен бути між 5 та 10000 символами")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type")
    private AccessType accessType;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private UserModel userModel;
}
