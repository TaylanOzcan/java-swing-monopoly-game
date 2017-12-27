package gui;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class BuildingContainerPanel extends ContainerPanel {

	private int nHouses;
	private boolean hasHotel;
	private boolean hasSkyscraper;

	public BuildingContainerPanel(char direction){
		this.direction = direction;
		initializeLayout(direction);
		this.setOpaque(false);
		nHouses = 0;
		hasHotel = false;
		hasSkyscraper = false;
	}

	public void initializeLayout(char direction){
		if(direction == 's'){
			this.setLayout(new GridLayout(0,4, 2, 2));
		}else if(direction == 'n'){
			this.setLayout(new GridLayout(0,4, 2, 2));
		}else if(direction == 'e'){
			this.setLayout(new GridLayout(4,0, 2, 2));
		}else if(direction == 'w'){
			this.setLayout(new GridLayout(4,0, 2, 2));
		}else{
			return;
		}
	}
	
	public void arrangeLayout(){
		if(direction == 's'){
			this.setLayout(new GridLayout(0,3, 2, 2));
		}else if(direction == 'n'){
			this.setLayout(new GridLayout(0,3, 2, 2));
		}else if(direction == 'e'){
			this.setLayout(new GridLayout(3,0, 2, 2));
		}else if(direction == 'w'){
			this.setLayout(new GridLayout(3,0, 2, 2));
		}else{
			return;
		}
		JPanel emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
		this.add(emptyPanel);
	}

	public void addIcon(){
		if(hasSkyscraper){
			return;
		}else if(hasHotel){
			this.removeAll();
			this.arrangeLayout();
			this.add(IconFactory.newSkyscraperLabel());
			hasSkyscraper = true;
			hasHotel = false;
		}else if(nHouses < 4){
			this.add(IconFactory.newHouseLabel());
			nHouses++;
		}else{
			this.removeAll();
			this.arrangeLayout();
			this.add(IconFactory.newHotelLabel());
			nHouses=0;
			hasHotel = true;
		}
	}
	
	public void removeIcon(){
		if(hasSkyscraper){
			this.removeAll();
			this.arrangeLayout();
			this.add(IconFactory.newHotelLabel());
			hasSkyscraper = false;
			hasHotel = true;
		}else if(hasHotel){
			this.removeAll();
			this.initializeLayout(direction);
			this.add(IconFactory.newHouseLabel());
			this.add(IconFactory.newHouseLabel());
			this.add(IconFactory.newHouseLabel());
			this.add(IconFactory.newHouseLabel());
			hasHotel = false;
			nHouses = 4;
		}else if(nHouses > 0){
			this.remove(0);
			nHouses--;
		}
	}

	public String getBuildingName(){
		if(hasSkyscraper)
			return "Skyscraper";
		else if(hasHotel)
			return "Hotel";
		else if(nHouses>0)
			return "House";
		else return null;
	}
}
