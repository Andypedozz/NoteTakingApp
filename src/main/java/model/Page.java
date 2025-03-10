package model;

import java.util.HashMap;
import java.util.Map;

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
		this.notes = new HashMap<Integer, Note>();
	}
	
	public static Page parsePage(Map<String, Object> map) {
		int id = Integer.parseInt(String.valueOf(map.get("id")));
		String titolo = String.valueOf(map.get("titolo"));
		String creationDate = String.valueOf(map.get("creationDate"));
		String lastModifiedDate = String.valueOf(map.get("lastModifiedDate"));
		int index = Integer.parseInt(String.valueOf(map.get("index")));
		Page page = new Page(id, titolo, creationDate, lastModifiedDate, index);
		return page;
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
