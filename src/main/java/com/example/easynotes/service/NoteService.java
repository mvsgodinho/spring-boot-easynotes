package com.example.easynotes.service;

import java.util.List;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.model.Note;

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
