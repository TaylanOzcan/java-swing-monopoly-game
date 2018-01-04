package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import domain.ActionHandler;
import domain.AnimatedTimer;
import domain.AuctionSquare;
import domain.Board;
import domain.Bot;
import domain.CardActionsHandler;
import domain.GamePlay;
import domain.Player;
import domain.PropertyListener;
import domain.PropertySquare;
import domain.SaveAndLoad;
import domain.Square;
import domain.StreetSquare;

public class Gui extends JFrame implements ActionListener, PropertyListener, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int INNER_LAYER = 0;
	private static final int MIDDLE_LAYER = 1;
	private static final int OUTER_LAYER = 2;

	private JPanel mainPanel, boardPanel, menuPanel, menuButtonsPanel, upperTopMenuPanel,
	gameButtonsPanel, botPanel, infoPanel, infoSelectionPanel, currentPlayerPanel,
	upperGameButtonsPanel, lowerGameButtonsPanel;
	private JButton newGameButton, loadButton, saveButton, rollButton, sellButton,
	buyButton, endTurnButton, buildHouseButton, exitButton, tradeButton, mortgageButton,
	useCardButton;
	private JComboBox<String> playerNamesComboBox;
	private JLabel[] playerInfoLabels, diceLabels;
	private ArrayList<JLabel> tokenLabels;
	private JLabel currentPlayerLabel, botLabel;
	private ArrayList<String> playerNames;
	private GamePlay gamePlay;
	private JPanel[] tokenPlacementPanels;
	private BuildingContainerPanel[] buildingContainerPanels;
	private int currentLocation;
	private boolean gameStarted;
	private AnimatorPanel animatorPanel;

	public void start() {
		this.gamePlay = new GamePlay();
		currentLocation = 0;
		initializeGui();
		addActionListeners();
	}

	private void addActionListeners() {
		newGameButton.addActionListener(this);
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);
		rollButton.addActionListener(this);
		endTurnButton.addActionListener(this);
		buyButton.addActionListener(this);
		buildHouseButton.addActionListener(this);
		exitButton.addActionListener(this);
		sellButton.addActionListener(this);
		mortgageButton.addActionListener(this);
		tradeButton.addActionListener(this);
		useCardButton.addActionListener(this);
	}

	private void addPropertyListeners() {
		gamePlay.getBoard().getActionHandler().addPropertyListener(this);
		((AuctionSquare) gamePlay.getSquare(79)).addPropertyListener(this);
		for(Player p: gamePlay.getPlayers()) {
			p.addPropertyListener(this);
		}
		gamePlay.getBoard().addPropertyListener(this);
		CardActionsHandler.getInstance().addPropertyListener(this);
		for(StreetSquare s: gamePlay.getStreetSquares()) {
			s.addPropertyListener(this);
		}
	}

	public void initializeGui(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});

		mainPanel = new MonopolyMainPanel();
		mainPanel.setLayout(new GridBagLayout());

		initializeBoard();
		initializeMenu();

		this.add(mainPanel);
		//this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	public void initializeBoard(){
		boardPanel = new MonopolyBoardPanel();
		boardPanel.setLayout(null);
		mainPanel.add(boardPanel);
		boardPanel.setPreferredSize(new Dimension(1000,1000));

		tokenPlacementPanels = new JPanel[121];
		buildingContainerPanels = new BuildingContainerPanel[120];
		addPanelsIntoBoard(MIDDLE_LAYER);
		addPanelsIntoBoard(INNER_LAYER);
		addPanelsIntoBoard(OUTER_LAYER);
	}

	public void addPanelsIntoBoard(int layer) {
		int step=0, startIndex=0, endIndex=0, startLoc=0, height=114, width=57;

		switch(layer) {
		case INNER_LAYER: startIndex=40;endIndex=64;startLoc=638;height=111;width=55;break;
		case MIDDLE_LAYER: startIndex=0;endIndex=40;startLoc=756;break;
		case OUTER_LAYER: startIndex=64;endIndex=120;startLoc=877;height=118;width=58;break;
		}

		step = (endIndex - startIndex)/4;

		for(int i=startIndex; i<endIndex; i++){
			JPanel currentPanel = new JPanel();
			currentPanel.setOpaque(false);
			//currentPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
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
				if(i==10) { // if it is jail square, add another panel to place jail visitors
					currentPanel.add(tokenPlacementPanel);
					currentPanel.add(new JLabel());
					tokenPlacementPanels[120] = new JPanel();
					tokenPlacementPanels[120].setOpaque(false); 
					currentPanel.add(tokenPlacementPanels[120]);
				}else {
					currentPanel.add(new JLabel());
					currentPanel.add(tokenPlacementPanel);
					currentPanel.add(new JLabel());
				}
			}
			boardPanel.add(currentPanel);
		}
	}

	public void initializeMenu(){
		menuPanel = new JPanel();
		menuButtonsPanel = new JPanel();
		botPanel = new JPanel();
		infoPanel = new JPanel();
		infoSelectionPanel = new JPanel();
		gameButtonsPanel = new JPanel();
		upperGameButtonsPanel = new JPanel();
		lowerGameButtonsPanel =  new JPanel();
		currentPlayerPanel = new JPanel();
		currentPlayerLabel = new JLabel("", SwingConstants.CENTER);
		currentPlayerLabel.setForeground(Color.WHITE);
		botLabel = new JLabel("", JLabel.CENTER);			
		playerNamesComboBox = new JComboBox<String>();
		upperTopMenuPanel = new JPanel();
		animatorPanel = new AnimatorPanel();
		JPanel topMenuPanel = new JPanel();
		topMenuPanel.setLayout(new GridLayout(2, 0, 0, 25));
		JPanel bottomMenuPanel = new JPanel();
		bottomMenuPanel.setLayout(new BorderLayout());

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
		saveButton = new JButton("Save");saveButton.setEnabled(false);
		exitButton = new JButton("Exit");
		rollButton = new JButton("Play Turn");rollButton.setEnabled(false);
		endTurnButton = new JButton("End Turn");endTurnButton.setEnabled(false);
		buyButton = new JButton("Buy Title Deed");buyButton.setEnabled(false);
		buildHouseButton = new JButton("Build House");buildHouseButton.setEnabled(false);
		sellButton = new JButton("Sell Title Deed");sellButton.setEnabled(false);
		mortgageButton = new JButton("Test / Move Manually");mortgageButton.setEnabled(false);
		tradeButton = new JButton("Test / Get Hurricane Card");tradeButton.setEnabled(false);
		useCardButton = new JButton("Use Card");useCardButton.setEnabled(false);

		menuButtonsPanel.setLayout(new GridLayout(0, 4, 25, 0));
		menuButtonsPanel.add(newGameButton);
		menuButtonsPanel.add(saveButton);
		menuButtonsPanel.add(loadButton);
		menuButtonsPanel.add(exitButton);
		menuButtonsPanel.setOpaque(false);

		//ImageIcon image = new ImageIcon("bot.png");
		//botLabel.setIcon(image);
		//botPanel.setOpaque(false);
		//botPanel.add(botLabel);

		upperTopMenuPanel.setLayout(new BorderLayout(0, 25));
		menuButtonsPanel.setPreferredSize(new Dimension(0,50));
		upperTopMenuPanel.add(menuButtonsPanel, BorderLayout.NORTH);
		upperTopMenuPanel.add(animatorPanel);
		upperTopMenuPanel.setOpaque(false);

		upperGameButtonsPanel.setLayout(new GridLayout(2, 3, 25, 10));
		upperGameButtonsPanel.add(buyButton);
		upperGameButtonsPanel.add(sellButton);
		upperGameButtonsPanel.add(mortgageButton);
		upperGameButtonsPanel.add(buildHouseButton);
		upperGameButtonsPanel.add(useCardButton);
		upperGameButtonsPanel.add(tradeButton);
		upperGameButtonsPanel.setOpaque(false);

		lowerGameButtonsPanel.setLayout(new GridLayout(0, 5, 25, 0));
		lowerGameButtonsPanel.add(rollButton);
		lowerGameButtonsPanel.add(diceLabels[0]); // regular die 1
		lowerGameButtonsPanel.add(diceLabels[1]); // regular die 2
		lowerGameButtonsPanel.add(diceLabels[2]); // speed die
		lowerGameButtonsPanel.add(endTurnButton);
		lowerGameButtonsPanel.setOpaque(false);

		gameButtonsPanel.setLayout(new GridLayout(2, 0, 0, 25));
		gameButtonsPanel.add(upperGameButtonsPanel);
		gameButtonsPanel.add(lowerGameButtonsPanel);
		gameButtonsPanel.setOpaque(false);

		infoSelectionPanel.setLayout(new BorderLayout());
		playerNamesComboBox.setPreferredSize(new Dimension(0,40));
		((JLabel)playerNamesComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		playerNamesComboBox.setFont(playerNamesComboBox.getFont().deriveFont(20.0f));
		infoSelectionPanel.add(playerNamesComboBox, BorderLayout.NORTH);
		infoSelectionPanel.add(infoPanel);
		playerNamesComboBox.setVisible(false);
		infoSelectionPanel.setOpaque(false);
		infoPanel.setOpaque(false);

		currentPlayerLabel.setForeground(Color.BLACK);
		currentPlayerPanel.add(currentPlayerLabel);
		currentPlayerPanel.setPreferredSize(new Dimension(0,40));
		currentPlayerPanel.setOpaque(false);
		bottomMenuPanel.add(currentPlayerPanel, BorderLayout.NORTH);
		bottomMenuPanel.add(infoSelectionPanel);
		bottomMenuPanel.setOpaque(false);

		topMenuPanel.add(upperTopMenuPanel);
		//topMenuPanel.add(botLabel);
		topMenuPanel.add(gameButtonsPanel);
		//topMenuPanel.add(dicePanel);
		topMenuPanel.setOpaque(false);

		menuPanel.setLayout(new GridLayout(2, 0, 0, 25));
		menuPanel.setOpaque(false);
		menuPanel.add(topMenuPanel);
		menuPanel.add(bottomMenuPanel);
		menuPanel.setBorder(new EmptyBorder(0, 20, 0, 25));
		menuPanel.setPreferredSize(new Dimension(650, 1000));

		JLabel emptyLabel = new JLabel();//
		emptyLabel.setPreferredSize(new Dimension(50, 1000));//
		mainPanel.add(emptyLabel);//
		mainPanel.add(menuPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameStarted) gamePlay.notifyBot();
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
			tokenLabels = new ArrayList<JLabel>(inputNumPlayers);
			playerNames = new ArrayList<String>(inputNumPlayers);
			for (int i = 0; i < inputNumPlayers; i++) {
				while(true) {
					String inputName = JOptionPane.showInputDialog(
							null, "Player " + (i+1) + " name?");
					if(inputName==null || inputName.length() < 1) {
						JOptionPane.showMessageDialog(
								null, "You must enter a valid name.");
					}else if(playerNames.contains(inputName)){
						JOptionPane.showMessageDialog(
								null, "You must select a different name.");
					}else{
						playerNames.add(inputName);
						playerNamesComboBox.addItem(""+ (i+1) + ": " + inputName);
						break;
					}
				}
			}
			for (int i = 0; i < inputNumPlayers ; i++) {
				playerInfoLabels[i] = new JLabel("", SwingConstants.CENTER);
				playerInfoLabels[i].setBackground(Color.WHITE);
				playerInfoLabels[i].setVisible(false);
				infoPanel.add(playerInfoLabels[i]);

				tokenLabels.add(i, new JLabel("" + (i+1), SwingConstants.CENTER));
				tokenLabels.get(i).setBackground(Color.CYAN);
				tokenLabels.get(i).setOpaque(true);
				tokenLabels.get(i).setBorder(LineBorder.createGrayLineBorder());

				int tokenSize = 114/inputNumPlayers - 5;
				if(tokenSize > 20) tokenSize = 20;
				tokenLabels.get(i).setPreferredSize(new Dimension(tokenSize, tokenSize));
			}	
			newGameButton.setEnabled(false);
			rollButton.setEnabled(true);
			saveButton.setEnabled(true);
			playerNamesComboBox.setVisible(true);
			playerNamesComboBox.addActionListener(this);
			buildHouseButton.setEnabled(true);
			sellButton.setEnabled(true);
			mortgageButton.setEnabled(true);
			infoPanel.setOpaque(true);
			currentPlayerPanel.setOpaque(true);
			tradeButton.setEnabled(true);
			useCardButton.setEnabled(true);

			gamePlay.playGame(playerNames);
			addPropertyListeners();

			Bot bot = gamePlay.getBot();
			AnimatedBot animatedBot = new AnimatedBot(bot);
			AnimatedTimer animatedTimer = new AnimatedTimer(bot);
			animatorPanel.addDrawable(animatedTimer);
			animatorPanel.addDrawable(animatedBot);
			animatorPanel.setVisible(true);

			gameStarted = true;
		}else if(e.getSource() == playerNamesComboBox) {
			for(Component c : infoPanel.getComponents()) {
				c.setVisible(false);
			}
			playerInfoLabels[playerNamesComboBox.getSelectedIndex()].setVisible(true);
			return;
		}else if(e.getSource() == rollButton){
			gamePlay.rollDiceAndMove();
			endTurnButton.setEnabled(true);
			rollButton.setEnabled(false);
			refreshDice();
		}else if(e.getSource() == endTurnButton){
			endTurnButton.setEnabled(false);
			rollButton.setEnabled(true);
			buildHouseButton.setEnabled(true);
			if(buyButton.isEnabled()) {
				gamePlay.checkAuction();
			}
			gamePlay.endTurn();
			refreshDice();
		}else if(e.getSource() == loadButton){
			SaveAndLoad.load(this);
			return;
		}else if(e.getSource() == saveButton){
			SaveAndLoad.save(this);
			return;
		}else if(e.getSource() == exitButton) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to exit the game?", "Exit",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				System.exit(0);
			}else {
				return;
			}
		}else if(e.getSource() == buyButton){
			if(gamePlay.buy()){
				JOptionPane.showMessageDialog(
						null, "You have successfully bought the square.");
			}else{
				JOptionPane.showMessageDialog(
						null, "You don't have enough balance.");
				return;
			}
		}else if(e.getSource() == sellButton) {
			ArrayList<String> squareNames = gamePlay.getOwnedSquareNames();
			int index = showSelectionBox(squareNames, "Select a title deed to sell:", "Title Deeds");
			if(index == -2) { //Cancel clicked
				return;
			}else if(index == -1){ //No item selected
				JOptionPane.showMessageDialog(
						null, "No title deeds selected.");
				return;
			}else if(index > -1){ // An item selected
				ArrayList<Player> players = gamePlay.getPlayingPlayers();
				ArrayList<String> playerNames = new ArrayList<String>(8);
				Player currentPlayer = gamePlay.getCurrentPlayer();
				players.remove(currentPlayer);
				for(Player p: players) {
					if(p!=currentPlayer) {
						playerNames.add(p.getName());
					}
				}
				int playerIndex = showSelectionBox(playerNames, "Who will buy the deed?", "Players");
				if(index == -2) { //Cancel clicked
					return;
				}else if(index == -1){ //No item selected
					JOptionPane.showMessageDialog(
							null, "No players selected.");	
					return;
				}else if(index > -1){ // An item selected
					String input = JOptionPane.showInputDialog(
							null, "Enter the agreed price:");
					int price = Integer.parseInt(input);
					gamePlay.sell(index, players.get(playerIndex), price);
				}
			}


		}else if(e.getSource() == mortgageButton) {
			showSquarePickBox(gamePlay.getCurrentPlayer());
		}else if(e.getSource() == tradeButton) {
			gamePlay.getCurrentPlayer().addChanceCard("Hurricane");
		}else if(e.getSource() == useCardButton) {			
			ArrayList<String> cards = new ArrayList<String>();
			int selection = 100;

			ArrayList<String> cardTypes = new ArrayList<String>(3);
			cardTypes.add("Chance");cardTypes.add("Community Chest");cardTypes.add("Voucher");
			int index = showSelectionBox(cardTypes, "Select card type:", "Card Types");
			if(index == -2) { //Cancel clicked
				return;
			}else if(index == -1){ //No item selected
				JOptionPane.showMessageDialog(
						null, "No card types selected.");	
				return;
			}else if(index == 0){
				cards = gamePlay.getChanceCards();
			}else if(index == 1){
				cards = gamePlay.getCommunityCards();
				selection = 200;
			}else {
				cards = gamePlay.getVouchers();
				selection = 300;
			}

			index = showSelectionBox(cards, "Select a card to use:", "Cards");	
			if(index == -2) {
				return;
			}else if(index == -1){
				JOptionPane.showMessageDialog(
						null, "No cards selected.");	
				return;
			}else{
				selection += index;
				gamePlay.useCard(selection);
				// show message
			}
		}else if(e.getSource() == buildHouseButton){
			ArrayList<String> squareNames = gamePlay.getOwnedSquareNames();
			int index = showSelectionBox(squareNames, "Select a square to build house:", "Squares");
			if(index == -2) { //Cancel clicked
				return;
			}else if(index == -1){ //No item selected
				JOptionPane.showMessageDialog(
						null, "No squares selected.");	
				return;
			}else if(index > -1){ // An item selected
				gamePlay.buildHouse(index);
			}
		}

		refreshAll();
	}

	public void refreshAll() {
		refreshInfo();
		refreshTokenLocations();
		refreshButtons();
		refreshDice();
		mainPanel.updateUI();
	}

	public void refreshDice(){
		int[] values = gamePlay.getDiceValues();
		for(int i=0 ; i<3; i++){
			diceLabels[i].setText(DieFace.getFaceForValue(values[i]));
		}
	}

	public void refreshInfo(){
		String currentName = gamePlay.getCurrentPlayerName();
		currentPlayerLabel.setText("<html><center><span style='font-size:18px'>"
				+ currentName
				+ " plays the current turn</span></center></html>");

		ArrayList<String> infoList = gamePlay.getPlayerInfo();
		for(int i=0; i< infoList.size(); i++){
			playerInfoLabels[i].setText(infoList.get(i));
		}
		playerNamesComboBox.setSelectedIndex(playerNames.indexOf(currentName));
	}

	public void refreshTokenLocations(){
		ArrayList<Integer> positions = gamePlay.getPlayerPositions();
		for(int i=0; i < positions.size(); i++){
			tokenPlacementPanels[positions.get(i)].add(tokenLabels.get(i));
		}

		((JPanel) boardPanel.getComponent(currentLocation)).setBorder(null);
		currentLocation = gamePlay.getCurrentPlayer().getLocation();
		((JPanel) boardPanel.getComponent(currentLocation)).setBorder(BorderFactory.createLineBorder(Color.red, 3));
	}

	public void refreshButtons(){
		buyButton.setEnabled(gamePlay.isBuyable());
	}

	@Override
	public void onPropertyEvent(Object source, String name, Object value) {
		refreshAll();
		if(source.getClass()==ActionHandler.class) {
			if(name.equals("startAuction")) {
				int highestBid = 0;
				ArrayList<Player> players = new ArrayList<Player>(8);
				StreetSquare auctedSquare = (StreetSquare) value;
				JOptionPane.showMessageDialog(
						null, "Auction is starting for " + auctedSquare.getName());
				for(Player p: gamePlay.getPlayingPlayers()) {
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
						null, "Winner is " + winner.getName() + " with $" + highestBid);
				gamePlay.endAuction(winner, highestBid, auctedSquare);
			}else if(name.equals("mrMonopolyAction")) {
				JOptionPane.showMessageDialog(
						null, ((Player) value).getName() + ", you get an extra move for rolling Mr. Monopoly. \nNow, you will be moved to the nearest unowned street.");
				gamePlay.moveToNearestUnownedStreetSquare((Player) value);
				rollButton.doClick();
			}else if(name.equals("busAction")) {
				JOptionPane.showMessageDialog(
						null, ((Player) value).getName() + ", you get a voucher for rolling Bus");
				rollButton.doClick();
			}else if(name.equals("rollsAgainAction")) {
				JOptionPane.showMessageDialog(
						null, ((Player) value).getName() + ", you get an extra move for rolling doubles.");
			}else if(name.equals("newTurnAction")) {
				//playerNamesComboBox.setSelectedItem(((Player) value).getName());
			}else if(name.equals("landedOnSubwayAction")) {
				JOptionPane.showMessageDialog(
						null, ((Player)value).getName() + ", you landed on Subway square previous turn.\n"
								+ "You can move to any square.");
				showSquarePickBox((Player)value);
			}
		}else if(source.getClass()==AuctionSquare.class) {
			if(name.equals("auctionDialog")) {
				ArrayList<Square> squares = gamePlay.getUnownedStreetSquares();
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(new JLabel(((Player) value).getName() + ", select an unowned square for auction."), BorderLayout.NORTH);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

				for(int i=0; i < squares.size(); i++){
					model.addElement(squares.get(i).getName());
				}

				JComboBox<String> comboBox = new JComboBox<String>(model);
				panel.add(comboBox);

				while(true) {
					int result = JOptionPane.showConfirmDialog(
							null, panel, "Squares", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

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
		}else if(source.getClass()==Player.class) {
			if(name.equals("rentPaid")) {
				PropertySquare square = ((PropertySquare) value);
				JOptionPane.showMessageDialog(
						null, ((Player)source).getName() + " has paid $" + square.getRent()
						+ " to " + square.getOwner().getName() + " for " + square.getName() + " rentals.");
			}else if(name.equals("bankrupted")) {
				Player p = (Player)source;
				JOptionPane.showMessageDialog(
						null, p.getName() + " went bankrupt.");
				playerInfoLabels[p.getId()].setText("Bankrupted");
				tokenLabels.get(p.getId()).setVisible(false);
				endTurnButton.doClick();
			}else if(name.equals("wentToJail")) {
				if((boolean)value) {
					JOptionPane.showMessageDialog(
							null, ((Player)source).getName() + " has been sent to jail for rolling 3 doubles in a row.");
				}else{
					JOptionPane.showMessageDialog(
							null, ((Player)source).getName() + " has been sent to jail.");
				}
			}else if(name.equals("rolledTriples")) {
				JOptionPane.showMessageDialog(
						null, ((Player)source).getName() + ", you rolled triples.\n"
								+ "You can move to any square.");
				showSquarePickBox((Player)source);
			}else if(name.equals("chanceCardAdded")) {
				JOptionPane.showMessageDialog(
						null, ((Player)source).getName() + ", you received the following chance card:\n"
								+ value);
			}else if(name.equals("communityCardAdded")) {
				JOptionPane.showMessageDialog(
						null, ((Player)source).getName() + ", you received the following community chest card:\n"
								+ value);
			}else if(name.equals("voucherAdded")) {
				JOptionPane.showMessageDialog(
						null, ((Player)source).getName() + ", you received the following voucher:\n"
								+ value);
			}
		}else if(source.getClass()==Board.class) {
			if(name.equals("gameOver")) {
				JOptionPane.showMessageDialog(
						null, ((Player)value).getName() + " has won the game!");
				dispose();
			}else if(name.equals("colorGroup")) {
				JOptionPane.showMessageDialog(
						null, ((Player)value).getName() + ", you need to have at least 2 properties for this color group to start building houses.");
			}
		}else if(source.getClass()==CardActionsHandler.class) {
			if(name.equals("hurricane")) {
				ArrayList<String> colors = gamePlay.getColorsOfConstructedStreets();

				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(new JLabel(((Player) value).getName() 
						+ ", select a color group to demolish buildings:"), BorderLayout.NORTH);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
				for(int i=0; i < colors.size(); i++){
					model.addElement(colors.get(i));
				}
				JComboBox<String> comboBox = new JComboBox<String>(model);
				panel.add(comboBox);
				while(true) {
					int result = JOptionPane.showConfirmDialog(
							null, panel, "Colors", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

					if(result == JOptionPane.OK_OPTION) {
						String color = (String) comboBox.getSelectedItem();
						if(color == null){
							JOptionPane.showMessageDialog(
									null, "No colors selected.");
						}else{
							gamePlay.hurricaneCard(color);
							break;
						}
					}else {
						break;
					}
				}
			}
		}else if(source.getClass()==StreetSquare.class) {
			if(name.equals("build")) {
				int index = (int) value;
				if(index == -1){
					JOptionPane.showMessageDialog(
							null, "You have already built a skyscraper.\nYou cannot build more.");
					return;
				}else if(index == -2) {
					JOptionPane.showMessageDialog(
							null, "You don't have enough balance.");
					return;
				}else{
					buildingContainerPanels[index].addIcon();
					JOptionPane.showMessageDialog(
							null, "You have successfully built a " 
									+ buildingContainerPanels[index].getBuildingName() + ".");
				}
			}else if(name.equals("demolish")) {
				int index = (int) value;
				if(index >= 0) {
					buildingContainerPanels[index].removeIcon();
				}
			}
		}
		refreshTokenLocations();
	}

	private void showSquarePickBox(Player value) {
		Square[] squares = gamePlay.getSquares();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("<html>" + ((Player) value).getName()
				+ ", select a square to move on.</html>"), BorderLayout.NORTH);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

		for(int i=0; i < squares.length; i++){
			model.addElement(squares[i].getName());
		}

		JComboBox<String> comboBox = new JComboBox<String>(model);
		panel.add(comboBox);

		while(true) {
			int result = JOptionPane.showConfirmDialog(
					null, panel, "Squares", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

			if(result == JOptionPane.OK_OPTION) {
				int index = comboBox.getSelectedIndex();
				if(index < 0){
					JOptionPane.showMessageDialog(
							null, "No squares selected.");	
				}else{
					gamePlay.moveTo(index);
					break;
				}
			}
		}
	}

	public int showSelectionBox(ArrayList<String> list, String text, String header) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

		for(int i=0; i < list.size(); i++){
			model.addElement(list.get(i));
		}

		JComboBox<String> comboBox = new JComboBox<String>(model);
		panel.add(comboBox);

		int option = JOptionPane.showConfirmDialog(
				null, panel, header, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if(option == JOptionPane.OK_OPTION) {
			return comboBox.getSelectedIndex();
		}else {
			return -2;
		}
	}

	public void refreshAfterLoad() {
		((MonopolyBoardPanel) boardPanel).readImage();
		((MonopolyMainPanel) mainPanel).readImage();
		((JLabel)playerNamesComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		playerNamesComboBox.setFont(playerNamesComboBox.getFont().deriveFont(20.0f));
		setVisible(true);
		JOptionPane.showMessageDialog(
				null, "Game was successfully loaded.");
	}

	public void refreshAfterSave() {
		((JLabel)playerNamesComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		playerNamesComboBox.setFont(playerNamesComboBox.getFont().deriveFont(20.0f));
		JOptionPane.showMessageDialog(
				null, "Game was successfully saved.");
	}

}