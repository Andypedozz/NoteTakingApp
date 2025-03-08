package model;

public class Note {
	private int id;
	private String text;
	private String creationDate;
	private String lastModifiedDate;
	
	private int positionX;
	private int positionY;
	private int sizeX;
	private int sizeY;
	
	public Note(int id, int positionX, int positionY, int sizeX, int sizeY) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
}
