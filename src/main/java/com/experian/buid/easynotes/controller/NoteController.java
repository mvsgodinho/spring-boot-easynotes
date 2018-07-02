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
import com.experian.buid.easynotes.exception.ResourceNotFoundException;
import com.experian.buid.easynotes.model.Note;
import com.experian.buid.easynotes.repository.NoteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping(AppConstants.Notes.PATH)
@Api(value = AppConstants.Notes.NAME)
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a note")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @GetMapping(value = "/notes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search a note by ID")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping(value = "/notes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a note")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping(value = "/notes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a note by ID")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
