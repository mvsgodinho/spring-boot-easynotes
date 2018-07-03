package com.experian.buid.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.experian.buid.easynotes.AppConstants;
import com.experian.buid.easynotes.model.Note;
import com.experian.buid.easynotes.service.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Marcos Godinho.
 */
@RestController
@RequestMapping(AppConstants.Notes.PATH)
@Api(value = AppConstants.Notes.NAME)
public class NoteController {

	@Autowired
	private NoteService noteService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all notes")
	public List<Note> getAllNotes() {
		return noteService.getAllNotes();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a note")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteService.createNote(note);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search a note by ID")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteService.getNoteById(noteId);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a note")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {
		return noteService.updateNote(noteId, noteDetails);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a note by ID")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		noteService.deleteNote(noteId);
		return ResponseEntity.ok().build();
	}
}
