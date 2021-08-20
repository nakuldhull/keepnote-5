package com.stackroute.keepnote.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.repository.NoteRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class NoteServiceImpl implements NoteService{

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	NoteRepository noteRepository;
	 

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) {
		
		List<Note> notes = new ArrayList<>();
		NoteUser noteuser = new NoteUser();
		notes.add(note);
		noteuser.setUserId(note.getNoteCreatedBy());
		noteuser.setNotes(notes);
		
		NoteUser noteUser =  noteRepository.insert(noteuser);
		if(noteUser!=null)
		{
			return true;
		}
			return false;
	}
	
	/* This method should be used to delete an existing note. */

	
	public boolean deleteNote(String userId, int noteId) {
		
		boolean flag = false;
		List<Note> notes = new ArrayList<>();
		NoteUser noteUser = new NoteUser();
		noteUser = noteRepository.findById(userId).get();
        notes=noteUser.getNotes();
		if (!notes.isEmpty()) {
			Iterator iterator = notes.listIterator();
			while (iterator.hasNext()) {
				Note note = (Note) iterator.next();
				if (note.getNoteId() == noteId) {
					iterator.remove();
					flag=true;
				}		
			}
			noteUser.setUserId(userId);
			noteUser.setNotes(notes);
			noteRepository.save(noteUser);
			
		}

		return flag;
	}
	
	/* This method should be used to delete all notes with specific userId. */

	
	public boolean deleteAllNotes(String userId) {
		
		boolean flag = false;
		NoteUser noteUser = new NoteUser();
		noteUser = noteRepository.findById(userId).get();
        if(noteUser!=null) {
        	noteRepository.delete(noteUser);
        	flag=true;
        }
		
		return flag;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note, int id, String userId) throws NoteNotFoundExeption {
		
		NoteUser noteUser = new NoteUser();
		List<Note> notes = new ArrayList<>();
		try {
		noteUser = noteRepository.findById(userId).get();
		notes=noteUser.getNotes();
		if (!notes.isEmpty()) {
			Iterator iterator = notes.listIterator();
			while (iterator.hasNext()) {
				Note note1 = (Note) iterator.next();
				if (note1.getNoteId() == id) {
					note1.setNoteId(note1.getNoteId());
					note1.setNoteTitle(note.getNoteTitle());
					note1.setNoteContent(note.getNoteContent());
					note1.setNoteCreationDate(note1.getNoteCreationDate());
					note1.setNoteCreatedBy(userId);
					note1.setCategory(note.getCategory());
					note1.setReminders(note.getReminders());	
					break;
				}
				
			}
			noteUser.setUserId(userId);
			noteUser.setNotes(notes);
			noteRepository.save(noteUser);	
		}
		}catch(NoSuchElementException e) {
        	throw new NoteNotFoundExeption("note not found");
        }
	   return note;
	}

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
	public Note getNoteByNoteId(String userId, int noteId) throws NoteNotFoundExeption {
		
		Note note=new Note();
        List<Note> notes = new ArrayList<>();
        try {
        notes=getAllNoteByUserId(userId);
        Iterator iterator = notes.listIterator();
		while (iterator.hasNext()) {

			note = (Note) iterator.next();
			if (note.getNoteId() == noteId)
				break;

		}
        }catch(NoSuchElementException e) {
        	throw new NoteNotFoundExeption("note not found");
        }
        
        return note;
		
	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Note> getAllNoteByUserId(String userId) {
		
		NoteUser noteUser = noteRepository.findById(userId).get();
		if(noteUser!=null) {
			return noteUser.getNotes();
		}
		return null;
	}

}
