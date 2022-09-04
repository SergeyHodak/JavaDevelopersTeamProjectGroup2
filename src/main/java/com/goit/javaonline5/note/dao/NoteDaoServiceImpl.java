package com.goit.javaonline5.note.dao;

import com.goit.javaonline5.note.dao.abstraction.NoteDaoService;
import com.goit.javaonline5.note.model.NoteModel;
import com.goit.javaonline5.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteDaoServiceImpl implements NoteDaoService {

    private final NoteRepository noteRepository;

    @Override
    public NoteModel save(final NoteModel entity) {
        return noteRepository.save(entity);
    }

    @Override
    public List<NoteModel> saveAll(final List<NoteModel> entities) {
        return noteRepository.saveAll(entities);
    }

    @Override
    public List<NoteModel> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public NoteModel findById(final Long id) {
        return noteRepository.findById(id).orElse(new NoteModel());
    }

    @Override
    public NoteModel updateById(final NoteModel entity, final Long id) {
        entity.setId(id);

        return noteRepository.save(entity);
    }

    @Override
    public void delete(final Long id) {
        noteRepository.deleteById(id);
    }
}
