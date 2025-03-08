package model;

import java.util.HashMap;

public class Page {
	
	private int id;
	private String titolo;
	private String creationDate;
	private String lastModifiedDate;
	private int index;
	private HashMap<Integer, Note> notes;
	
	public Page(int id, String titolo, String creationDate, String lastModifiedDate, int index) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.index = index;
	}

	public int getId() {
		return id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getCreationDate() {
		return creationDate;
	}
	
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public HashMap<Integer, Note> getNotes() {
		return notes;
	}
	
	public void addNote(Note note) {
		
	}
	
	public void removeNote(Note note) {
		
	}
}
