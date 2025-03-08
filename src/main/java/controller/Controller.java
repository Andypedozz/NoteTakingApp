package controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import model.Model;
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
	
	public void start() {
		createDatabase();
	}
	
	private void createDatabase() {
		try {
			File dbFile = new File("database.db");
			if(!dbFile.exists()) {
				SQLiteHelper.connect();
				String name = "ddlQueries.sql";
				File queriesFile = new File(getPath(name));
				List<String> queries = Files.readAllLines(queriesFile.toPath());
				queries.forEach(query -> {
					SQLiteHelper.executeQuery(query);
				});
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getPath(String str) {
		String prefix = "src//main/resources/";
		prefix += str;
		return prefix;
	}
	
}
