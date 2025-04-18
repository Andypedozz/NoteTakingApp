package controller;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Model;
import model.Note;
import model.Page;
import sqllite.SQLiteHelper;
import view.View;

public class Controller {
	private static Controller INSTANCE;
	private Model model;
	private View view;
	
	private Controller() {}
	
	public static Controller getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}
	
	public HashMap<Integer, Page> getPages() {
		return this.model.getPages();
	}
	
	public void start() {
		createDatabase();
		this.model = new Model();
		loadData();
		this.view = new View();
		this.view.launchFrame();
	}
	
	private void createDatabase() {
		try {
			SQLiteHelper.connect();
			
			// Create Schema
			System.out.println("DDL Queries");
			String ddlName = "ddlQueries.sql";
			File ddlQueriesFile = new File(getPath(ddlName));
			List<String> ddlQueries = Files.readAllLines(ddlQueriesFile.toPath());
			ddlQueries.forEach(query -> {
				System.out.println(query);
				SQLiteHelper.executeQuery(query);
			});
			System.out.println();
			
			// Load Data
			System.out.println("DML Queries");
			String dmlName = "dmlQueries.sql";
			File dmlQueriesFile = new File(getPath(dmlName));
			List<String> dmlQueries = Files.readAllLines(dmlQueriesFile.toPath());
			dmlQueries.forEach(query -> {
				System.out.println(query);
				SQLiteHelper.executeQuery(query);
			});
			System.out.println();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadData() {
		List<Map<String, Object>> pages = SQLiteHelper.executeGetQuery("SELECT * FROM Page");
		
		// Load pages
		for(Map<String, Object> map : pages) {
			Page page = Page.parsePage(map);
			this.model.getPages().put(page.getId(), page);
		}
		
		// Load notes
		HashMap<Integer, Page> pagesList = this.model.getPages();
		pagesList.forEach((pageId, page) -> {
			List<Map<String, Object>> notes = SQLiteHelper.executeGetQuery("SELECT * FROM Note WHERE id IN (SELECT idPage FROM PageNote WHERE idPage = "+pageId+");");
			notes.forEach(map -> {
				Note note = Note.parseNote(map);
				model.getPages().get(pageId).getNotes().put(note.getId(), note);
			});
		});
	}
	
	private String getPath(String str) {
		String prefix = "src//main/resources/";
		prefix += str;
		return prefix;
	}

	public void saveNotes() {
		HashMap<Integer, Page> pages = this.model.getPages();
		pages.forEach((pageId, page) -> {
			HashMap<Integer, Note> notes = page.getNotes();
			notes.forEach((noteId, note) -> {
				String noteQuery = "UPDATE Note SET text = "+note.getText()+" WHERE id = "+note.getId()+";";
				SQLiteHelper.executeQuery(noteQuery);
			});
		});
	}
	
}
