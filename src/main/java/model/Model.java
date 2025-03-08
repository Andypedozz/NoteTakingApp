package model;

import java.util.HashMap;

public class Model {
	private HashMap<Integer, Page> pages;
	
	public Model() {
		this.pages = new HashMap<Integer, Page>();
	}
	
	public HashMap<Integer, Page> getPages() {
		return pages;
	}
}
