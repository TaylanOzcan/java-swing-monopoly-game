package gui;

import javax.swing.JPanel;

public abstract class ContainerPanel extends JPanel{

	protected char direction;
	public abstract void addIcon();
	public abstract void removeIcon();
	
}
