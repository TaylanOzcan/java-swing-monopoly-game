package gui;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domain.ActionHandler;
import domain.AuctionSquare;
import domain.GamePlay;
import domain.Player;
import domain.PropertyListener;
import domain.SaveAndLoad;
import domain.Square;
import domain.StreetSquare;

public class Gui implements ActionListener, PropertyListener , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int INNER_LAYER = 0;
	private static final int MIDDLE_LAYER = 1;
	private static final int OUTER_LAYER = 2;

	private JFrame mainFrame;
	private JPanel mainPanel, boardPanel, menuPanel, menuButtonsPanel,
	gameButtonsPanel, dicePanel, infoPanel;
	private JButton newGameButton, loadButton, saveButton, rollButton,
	buyTitleDeedsButton, endTurnButton, buildHouseButton, exitButton;
	private JLabel[] tokenLabels, playerInfoLabels, diceLabels;
	private JLabel currentPlayerLabel;

	private ArrayList<String> playerNames;
	private GamePlay gamePlay;

	private JPanel[] tokenPlacementPanels;
	private BuildingContainerPanel[] buildingContainerPanels;

	class MyBoardPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private BufferedImage image;
		public MyBoardPanel() {
			try{
				image = ImageIO.read(new File("lol.png"));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 1000, 1000, this);
		}
	}

	class MyMainPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private BufferedImage image;
		public MyMainPanel() {
			try{
				image = ImageIO.read(new File("bg_mainPanel.png"));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public Gui(){
		this.gamePlay = new GamePlay();
		initializeGui();
		addActionListeners();
	}

	private void addActionListeners() {
		newGameButton.addActionListener(this);
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);
		rollButton.addActionListener(this);
		endTurnButton.addActionListener(this);
		buyTitleDeedsButton.addActionListener(this);
		buildHouseButton.addActionListener(this);
		exitButton.addActionListener(this);
	}

	private void addPropertyListeners() {
		gamePlay.getBoard().getActionHandler().addPropertyListener(this);
		((AuctionSquare) gamePlay.getSquare(79)).addPropertyListener(this);
	}

	public void initializeGui(){
		mainFrame = new JFrame("Monopoly");
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});

		mainPanel = new MyMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		//mainPanel.setBackground(Color.GRAY);

		initializeBoard();
		initializeMenu();

		//mainPanel.setPreferredSize(mainPanel.getPreferredSize());
		//mainPanel.setBackground(new Color (192,226,202));

		mainFrame.add(mainPanel);
		//mainFrame.pack();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setUndecorated(true);
		mainFrame.setVisible(true);
	}

	public void initializeBoard(){
		boardPanel = new MyBoardPanel();
		boardPanel.setLayout(null);
		mainPanel.add(boardPanel);
		boardPanel.setPreferredSize(new Dimension(1000,1000));
		Color boardColor = new Color (192,226,202);
		boardPanel.setBackground(boardColor);

		tokenPlacementPanels = new JPanel[120];
		buildingContainerPanels = new BuildingContainerPanel[120];
		addPanelsIntoBoard(MIDDLE_LAYER);
		addPanelsIntoBoard(INNER_LAYER);
		addPanelsIntoBoard(OUTER_LAYER);
	}

	public void addPanelsIntoBoard(int layer) {
		int step=0, startIndex=0, endIndex=0, startLoc=0, height=114, width=57;

		switch(layer) {
		case INNER_LAYER: startIndex=40;endIndex=64;startLoc=638;height=108;width=55;break;
		case MIDDLE_LAYER: startIndex=0;endIndex=40;startLoc=756;break;
		case OUTER_LAYER: startIndex=64;endIndex=120;startLoc=877;height=116;width=58;break;
		}

		step = (endIndex - startIndex)/4;

		for(int i=startIndex; i<endIndex; i++){
			JPanel currentPanel = new JPanel();
			currentPanel.setOpaque(false);
			//currentPanel.setBorder(BorderFactory.createLineBorder(Color.red));

			char direction = '0';
			int stepDistance = (startIndex==0 ? (i%step)*width : ((i%startIndex)%step)*width);

			if(i == startIndex){
				currentPanel.setSize(width*2, height);
				currentPanel.setLayout(new GridLayout(4,0));
				currentPanel.setLocation(startLoc, startLoc);
			}else if(i < startIndex + step){
				currentPanel.setSize(width, height);
				currentPanel.setLayout(new GridLayout(4,0));
				currentPanel.setLocation(startLoc - stepDistance, startLoc);
				direction = 's';
			}else if(i == startIndex + step){
				currentPanel.setSize(height, width*2);
				currentPanel.setLayout(new GridLayout(4,0));
				currentPanel.setLocation(startLoc - ((step+1) * width), startLoc);
			}else if(i < startIndex + 2*step){
				currentPanel.setSize(height, width);
				currentPanel.setLayout(new GridLayout(0,4));
				currentPanel.setLocation(startLoc - ((step+1) * width), startLoc - stepDistance);
				direction = 'e';
			}else if(i == startIndex + 2*step){
				currentPanel.setSize(width*2, height);
				currentPanel.setLayout(new GridLayout(4,0));
				currentPanel.setLocation(startLoc - ((step+1) * width), startLoc - ((step+1) * width));
			}else if(i < startIndex + 3*step){
				currentPanel.setSize(width, height);
				currentPanel.setLayout(new GridLayout(4,0));
				currentPanel.setLocation(startLoc - ((step+1) * width) + width + stepDistance, startLoc - ((step+1) * width));
				direction = 'n';
			}else if(i == startIndex + 3*step){
				currentPanel.setSize(height, width*2);
				currentPanel.setLayout(new GridLayout(4,0));
				currentPanel.setLocation(startLoc, startLoc - ((step+1) * width));
			}else if(i < endIndex){
				currentPanel.setSize(height, width);
				currentPanel.setLayout(new GridLayout(0,4));
				currentPanel.setLocation(startLoc, startLoc - ((step+1) * width) + width + stepDistance);
				direction = 'w';
			}

			JPanel tokenPlacementPanel = new JPanel();
			tokenPlacementPanel.setOpaque(false);
			tokenPlacementPanels[i] = tokenPlacementPanel;

			BuildingContainerPanel buildingContainerPanel = new BuildingContainerPanel(direction);
			buildingContainerPanels[i] = buildingContainerPanel;

			if(direction=='e' || direction=='n'){
				currentPanel.add(new JLabel());
				currentPanel.add(tokenPlacementPanel);
				currentPanel.add(new JLabel());
				currentPanel.add(buildingContainerPanel);
			}else{
				currentPanel.add(buildingContainerPanel);
				currentPanel.add(new JLabel());
				currentPanel.add(tokenPlacementPanel);
				currentPanel.add(new JLabel());
			}
			boardPanel.add(currentPanel);
		}
	}

	public void initializeMenu(){
		menuPanel = new JPanel();
		menuButtonsPanel = new JPanel();
		dicePanel = new JPanel();
		infoPanel = new JPanel();
		gameButtonsPanel = new JPanel();
		currentPlayerLabel = new JLabel("<html><center><span style='font-size:40px'>" 
				+ "Ultimate Monopoly</span>"
				/*+ "<span style='font-size:11px'><br>prepared by team RND3 "
				+ "for COMP 302 term project</span>"*/
				+ "</center></html>", SwingConstants.CENTER);
		currentPlayerLabel.setForeground(Color.WHITE);

		JPanel topMenuPanel = new JPanel();
		topMenuPanel.setLayout(new GridLayout(4, 0, 0, 40));
		JPanel bottomMenuPanel = new JPanel();

		diceLabels = new JLabel[3];
		diceLabels[0] = new JLabel("", SwingConstants.CENTER);
		diceLabels[0].setBackground(Color.WHITE);
		diceLabels[0].setOpaque(true);
		diceLabels[0].setBorder(LineBorder.createGrayLineBorder());
		diceLabels[1] = new JLabel("", SwingConstants.CENTER);
		diceLabels[1].setBackground(Color.WHITE);
		diceLabels[1].setOpaque(true);
		diceLabels[1].setBorder(LineBorder.createGrayLineBorder());
		diceLabels[2] = new JLabel("", SwingConstants.CENTER);
		diceLabels[2].setBackground(Color.WHITE);
		diceLabels[2].setOpaque(true);
		diceLabels[2].setBorder(LineBorder.createGrayLineBorder());

		newGameButton = new JButton("New Game");
		loadButton = new JButton("Load");
		saveButton = new JButton("Save");
		exitButton = new JButton("Exit");
		rollButton = new JButton("Play Turn");
		endTurnButton = new JButton("End Turn");
		buyTitleDeedsButton = new JButton("Buy Title Deeds");
		buildHouseButton = new JButton("Build House");
		saveButton.setEnabled(false);
		endTurnButton.setEnabled(false);
		buyTitleDeedsButton.setEnabled(false);
		buildHouseButton.setEnabled(false);
		rollButton.setEnabled(false);

		menuButtonsPanel.setLayout(new GridLayout(0, 4, 20, 0));
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(saveButton);
		menuButtonsPanel.add(loadButton);
		menuButtonsPanel.add(exitButton);
		menuButtonsPanel.setOpaque(false);

		gameButtonsPanel.setLayout(new GridLayout(0, 2, 20, 0));
		gameButtonsPanel.add(buyTitleDeedsButton);
		gameButtonsPanel.add(buildHouseButton);
		gameButtonsPanel.setOpaque(false);

		dicePanel.setLayout(new GridLayout(0, 5, 20, 20));
		dicePanel.add(rollButton);
		dicePanel.add(diceLabels[0]); // regular die 1
		dicePanel.add(diceLabels[1]); // regular die 2
		dicePanel.add(diceLabels[2]); // speed die
		dicePanel.add(endTurnButton);
		dicePanel.setOpaque(false);

		menuPanel.setLayout(new GridLayout(2, 0, 0, 20));
		menuPanel.setOpaque(false);

		topMenuPanel.add(menuButtonsPanel);
		topMenuPanel.add(new JPanel().add(currentPlayerLabel));
		topMenuPanel.add(gameButtonsPanel);
		topMenuPanel.add(dicePanel);
		bottomMenuPanel.add(infoPanel);
		topMenuPanel.setOpaque(false);
		bottomMenuPanel.setOpaque(false);
		menuPanel.add(topMenuPanel);
		menuPanel.add(bottomMenuPanel);
		menuPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

		JLabel emptyLabel = new JLabel();//
		emptyLabel.setPreferredSize(new Dimension(50,1000));//
		mainPanel.add(emptyLabel);//

		mainPanel.add(menuPanel);
		menuPanel.setPreferredSize(new Dimension(600,1000));
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == newGameButton){
			String input = JOptionPane.showInputDialog(
					null, "Select the number of players");

			int inputNumPlayers;
			try{
				inputNumPlayers = Integer.parseInt(input);
				if(inputNumPlayers<2 || inputNumPlayers>8) {
					JOptionPane.showMessageDialog(
							null, "Number of players must be between 2 and 8.");
					return;
				}
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(
						null, "You must enter a number.");
				return;
			}

			playerInfoLabels = new JLabel[inputNumPlayers];
			tokenLabels = new JLabel[inputNumPlayers];
			playerNames = new ArrayList<String>(inputNumPlayers);

			for (int i = 0; i < inputNumPlayers; i++) {
				String inputName = JOptionPane.showInputDialog(
						null, "Player " + (i+1) + " name?");
				playerNames.add(inputName);
			}

			for (int i = 0; i < inputNumPlayers ; i++) {
				playerInfoLabels[i] = new JLabel("", SwingConstants.CENTER);
				playerInfoLabels[i].setBackground(Color.WHITE);
				playerInfoLabels[i].setOpaque(true);
				playerInfoLabels[i].setPreferredSize(new Dimension((600 / inputNumPlayers) - 20, 400));
				infoPanel.add(playerInfoLabels[i]);

				tokenLabels[i] = new JLabel("" + (i+1), SwingConstants.CENTER);
				tokenLabels[i].setBackground(Color.CYAN);
				tokenLabels[i].setOpaque(true);
				tokenLabels[i].setBorder(LineBorder.createGrayLineBorder());

				int tokenSize = 114/inputNumPlayers - 5;
				if(tokenSize > 20) tokenSize = 20;
				tokenLabels[i].setPreferredSize(new Dimension(tokenSize, tokenSize));
			}
			currentPlayerLabel.setText("<html><center><span style='font-size:20px'>" 
					+ "Current Turn: <br></span></center></html>");
			newGameButton.setEnabled(false);
			rollButton.setEnabled(true);
			saveButton.setEnabled(true);
			buildHouseButton.setEnabled(true);
			gamePlay.playGame(playerNames);
			addPropertyListeners();
		}else if(e.getSource() == rollButton){
			gamePlay.rollDiceAndMove();
			endTurnButton.setEnabled(true);
			rollButton.setEnabled(false);
			refreshDice();
		}else if(e.getSource() == endTurnButton){
			endTurnButton.setEnabled(false);
			rollButton.setEnabled(true);
			buildHouseButton.setEnabled(true);
			if(buyTitleDeedsButton.isEnabled()) {
				gamePlay.checkAuction();
			}
			gamePlay.endTurn();
			refreshDice();
		}else if(e.getSource() == loadButton){
			SaveAndLoad.load(this);
			gamePlay.playGame(playerNames);
			loadButton.setEnabled(false);
		}else if(e.getSource() == saveButton){
			SaveAndLoad.save(this);
			loadButton.setEnabled(true);
		}else if(e.getSource() == exitButton) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to exit the game?", "Exit",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				System.exit(0);
			}else {
				return;
			}
		}else if(e.getSource() == buyTitleDeedsButton){
			if(gamePlay.buy()){
				JOptionPane.showMessageDialog(
						null, "You have successfully bought the square.");
			}else{
				JOptionPane.showMessageDialog(
						null, "You cannot buy this square.");
				return;
			}
		}else if(e.getSource() == buildHouseButton){
			ArrayList<String> squareNames = gamePlay.getOwnedSquareNames();

			JPanel panel = new JPanel();
			panel.add(new JLabel("Select a square to build house:"));
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

			for(int i=0; i < squareNames.size(); i++){
				model.addElement(squareNames.get(i));
			}

			JComboBox<String> comboBox = new JComboBox<String>(model);
			panel.add(comboBox);

			int result = JOptionPane.showConfirmDialog(
					null, panel, "Squares", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			switch (result) {
			case JOptionPane.OK_OPTION:
				if(comboBox.getSelectedIndex() < 0){
					JOptionPane.showMessageDialog(
							null, "No squares selected.");	
					return;
				}else{
					int index = gamePlay.buildHouse(comboBox.getSelectedIndex());
					if(index < 0){
						JOptionPane.showMessageDialog(
								null, "You have already built a skyscraper.\nYou cannot build more.");
						return;
					}else{
						buildingContainerPanels[index].addIcon();
						JOptionPane.showMessageDialog(
								null, "You have successfully built a " 
										+ buildingContainerPanels[index].getBuildingName() + ".");
					}
				}
				break;
			}
		}

		refreshInfo();
		refreshTokenLocations();
		refreshButtons();
		mainPanel.updateUI();

	}

	public void refreshDice(){
		int[] values = gamePlay.getDiceValues();
		for(int i=0 ; i<3; i++){
			diceLabels[i].setText(DieFace.getFaceForValue(values[i]));
		}
	}

	public void refreshInfo(){
		currentPlayerLabel.setText("<html><center><span style='font-size:20px'>" 
				+ "Current Turn: <br> "
				+ gamePlay.getCurrentPlayerName()
				+ "</span></center></html>");

		ArrayList<String> infoList = gamePlay.getPlayerInfo();
		for(int i=0; i< infoList.size(); i++){
			this.playerInfoLabels[i].setText(infoList.get(i));
		}
	}

	public void refreshTokenLocations(){
		ArrayList<Integer> positions = gamePlay.getPlayerPositions();
		for(int i=0; i < tokenPlacementPanels.length; i++){
			for(int j=0; j < positions.size(); j++){
				if(i == positions.get(j)){
					tokenPlacementPanels[i].add(tokenLabels[j]);
				}
			}
		}
	}

	public void refreshButtons(){
		boolean buyable = gamePlay.isBuyable();
		buyTitleDeedsButton.setEnabled(buyable);
	}

	@Override
	public void onPropertyEvent(Object source, String name, Object value) {
		if(source.getClass()==ActionHandler.class) {
			if(name.equals("startAuction")) {
				int highestBid = 0;
				ArrayList<Player> players = new ArrayList<Player>(8);
				StreetSquare auctedSquare = (StreetSquare) value;

				JOptionPane.showMessageDialog(
						null, "Auction is starting for " + auctedSquare.getName());
				
				for(Player p: gamePlay.getPlayers()) {
					players.add(p);
				}
				
				int counter = players.size();
				Player winner=players.get(counter-1);
				
				while(counter>1) {
					for(int i=0; i<counter; i++) {
						Player p = players.get(i);
						int balance = p.getBalance();

						while(true) {
							String input = JOptionPane.showInputDialog(
									null, p.getName() + ", place your bid for " + auctedSquare.getName() +
											(highestBid>0 ? ("\n(Current highest bid is " + highestBid + ")") : ""));
							int currentBid;
							try{
								currentBid = Integer.parseInt(input);
								if(currentBid>highestBid) {
									if(balance<currentBid) {
										JOptionPane.showMessageDialog(
												null, "You do not have enough balance");
									}else {
										highestBid = currentBid;
										winner = p;
										break;
									}
								}else {
									JOptionPane.showMessageDialog(
											null, "You must place a higher bet, or click cancel to withdraw");
								}
							}catch(NumberFormatException nfe) {
								if(null==input) {
									players.remove(p);
									i--;
									counter--;
									break;
								}else {
									JOptionPane.showMessageDialog(
											null, "You must enter a valid amount");
								}
							}
						}	
						if(players.size()==1) break;
					}
				}
				JOptionPane.showMessageDialog(
						null, "Winner is " + winner.getName() + " with " + highestBid + " $");
				gamePlay.endAuction(winner, highestBid, auctedSquare);
			}
		}else if(source.getClass()==AuctionSquare.class) {
			if(name.equals("auctionDialog")) {
				ArrayList<Square> squares = gamePlay.getUnownedStreetSquares();
				JPanel panel = new JPanel();
				panel.add(new JLabel(((Player) value).getName() + ", select an unowned square for auction"));
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

				for(int i=0; i < squares.size(); i++){
					model.addElement(squares.get(i).getName());
				}

				JComboBox<String> comboBox = new JComboBox<String>(model);
				panel.add(comboBox);

				int result = JOptionPane.showConfirmDialog(
						null, panel, "Squares", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

				while(true) {
					if(result == JOptionPane.OK_OPTION) {
						int index = comboBox.getSelectedIndex();
						if(index < 0){
							JOptionPane.showMessageDialog(
									null, "No squares selected.");	
						}else{
							gamePlay.startAuction((StreetSquare)squares.get(index));
							break;
						}
					}
				}
			}
		}
	}
	
}