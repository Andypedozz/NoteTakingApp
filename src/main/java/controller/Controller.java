package controller;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Model;
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
		this.view = new View();
		this.view.launchFrame();
	}
	
	private void createDatabase() {
		try {
			File dbFile = new File("database.db");
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
		for(Map<String, Object> page : pages) {
			int id = Integer.parseInt(String.valueOf(page.get("id")));
			String titolo = String.valueOf(page.get("titolo"));
			String creationDate = String.valueOf(page.get("creationDate"));
			String lastModifiedDate = String.valueOf(page.get("lastModifiedDate"));
			int index = Integer.parseInt(String.valueOf(page.get("index")));
			Page page1 = new Page(id, titolo, creationDate, lastModifiedDate, index);
			this.model.getPages().put(page1.getId(), page1);
		}
		
	}
	
	private String getPath(String str) {
		String prefix = "src//main/resources/";
		prefix += str;
		return prefix;
	}
	
}
