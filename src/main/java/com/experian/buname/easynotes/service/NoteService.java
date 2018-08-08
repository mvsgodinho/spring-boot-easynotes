package com.experian.buname.easynotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experian.buname.easynotes.exception.ResourceNotFoundException;
import com.experian.buname.easynotes.model.Note;
import com.experian.buname.easynotes.repository.NoteRepository;

/**
 * Created by Marcos Godinho.
 */
@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	public Note createNote(Note note) {
		return noteRepository.save(note);
	}

	public Note getNoteById(Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}

	public Note updateNote(Long noteId, Note noteDetails) {

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}

	public void deleteNote(Long noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		noteRepository.delete(note);
	}
}
