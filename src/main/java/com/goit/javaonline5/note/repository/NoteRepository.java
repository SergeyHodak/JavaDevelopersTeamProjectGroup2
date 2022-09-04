package com.goit.javaonline5.note.repository;

import com.goit.javaonline5.note.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Long> {
}
