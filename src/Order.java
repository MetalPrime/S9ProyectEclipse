
import processing.core.PApplet;
import processing.core.PImage;

public class Order {
	
	private String number;
	private PImage img;
	private String date;
	private PApplet app;
	private String ip;
	private int posX,posY;
	private int sizeX,sizeY;

	public Order(PApplet app,String number,PImage img,String date, String ip) {
		this.app = app;
		this.number = number;
		this.img = img;
		this.date = date;
		this.ip = ip;
		
	}
	
	public void paint(int posX, int posY) {
		this.setPosX(posX);
		this.setPosY(posY);
		sizeX = 200;
		sizeY = 70;
		app.fill(255);
		app.stroke(0);
		app.strokeWeight(3);
		app.rect(posX-10,posY-10,sizeX,sizeY);
		app.image(img,posX , posY,50,50);
		app.fill(0);
		app.text("Pedido #"+ number, posX+60, posY+20);
		app.text(date+"",posX+60,posY+40);
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PImage getImg() {
		return img;
	}

	public void setImg(PImage img) {
		this.img = img;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
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
