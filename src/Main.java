import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.GapContent;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Main.class.getName());
	}
	
    private UDPConnection udp;
    private ArrayList<Order> orders;
    private int numberProduct;

	public void settings() {
		size(700,700);
	}
	
	public void setup() {
		
        udp = new UDPConnection();
        udp.setObserver(this);
        udp.start();
        
        orders = new ArrayList<Order>();
	}
	
	public void draw() {
		background(180);
		fill(0);
		for (int i = 0; i<orders.size(); i++) {
			orders.get(i).paint(20, i*80+60);
		}
	}
	
	 @Override
	public void mousePressed() {
		// TODO Auto-generated method stub
		 for (int i = 0; i<orders.size(); i++) {
			 if(mouseX>orders.get(i).getPosX() && mouseX<orders.get(i).getSizeX()+orders.get(i).getPosX()
					 && mouseY>orders.get(i).getPosY() && mouseY<orders.get(i).getSizeY()+orders.get(i).getPosY() ) {
				
				 udp.sendMessages("Orden Lista", orders.get(i).getIp());
				 orders.remove(orders.get(i));
				 
			 }
			}
	}

	@Override
	public void statusOrder(boolean onDisplay) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newOrder(String product,String ip) {
		// TODO Auto-generated method stub
		
		Date objDate = new Date();
		String strDateFormat = "hh: mm: ss";
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		
		if(orders.size()<8) {
			switch(product) {
			case "Pizza":
				orders.add(new Order(this, numberProduct+"", loadImage("./img/pizza.jpg"), objSDF.format(objDate) ,ip));
				numberProduct++;
				break;
			case "Burger":
				orders.add(new Order(this, numberProduct+"", loadImage("./img/burger.jpg"), objSDF.format(objDate) ,ip));
				numberProduct++;
				break;
			case "Fries":
				orders.add(new Order(this, numberProduct+"", loadImage("./img/frenchfries.jpg"), objSDF.format(objDate) ,ip));
				numberProduct++;
				break;
			case "Malteada":
				orders.add(new Order(this, numberProduct+"", loadImage("./img/batidos.jpg"), objSDF.format(objDate) ,ip));
				numberProduct++;
				break;
			}
		}
		
	}
	
	
}
