package domain;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SquareFactory{

	private Square[] squares = new Square[120];
	private static SquareFactory instance;

	private SquareFactory() {
		// EFFECTS : Creates squares.
		squares[0] = new RegularSquare(0, "Go");
		squares[1] = new StreetSquare(1, "MEDITERRANEAN AVENUE", 10, 30, 90, 160, 250, 750, 2, 60, "Dark Purple");
		squares[2] = new CommunityChestSquare(2, "COMMUNITY CHEST");
		squares[3] = new StreetSquare(3, "BALTIC AVENUE", 20, 60, 180, 320, 450, 900, 4, 60, "Dark Purple");
		squares[4] = new RegularSquare(4, "4");
		squares[5] = new RegularSquare(5, "5");
		squares[6] = new StreetSquare(6, "ORIENTAL AVENUE", 30, 90, 270, 400, 550, 1050, 6, 100, "Light Blue");
		squares[7] = new ChanceSquare(7, "CHANCE");
		squares[8] = new StreetSquare(8, "VERMONT AVENUE", 30, 90, 270, 400, 550, 1050, 6, 100, "Light Blue");
		squares[9] = new StreetSquare(9, "CONNECTICUT AVENUE", 40, 100, 300, 450, 600, 1100, 8, 120, "Light Blue");
		squares[10] = new RegularSquare(10, "10");
		squares[11] = new StreetSquare(11, "ST. CHARLES PLACE", 50, 150, 450, 625, 750, 1250, 10, 140, "Light Purple");
		squares[12] = new RegularSquare(12, "12");
		squares[13] = new StreetSquare(13, "STATES AVENUE", 50, 150, 450, 625, 750, 1250, 10, 140, "Light Purple");
		squares[14] = new StreetSquare(14, "VIRGINIA AVENUE", 60, 180, 500, 700, 900, 1400, 12, 160, "Light Purple");
		squares[15] = new RegularSquare(15, "15");
		squares[16] = new StreetSquare(16, "ST. JAMES PLACE", 70, 200, 550, 750, 950, 1450, 14, 180, "Light Orange");
		squares[17] = new CommunityChestSquare(17, "COMMUNITY CHEST");
		squares[18] = new StreetSquare(18, "TENNESEE AVENUE", 70, 200, 550, 750, 950, 1450, 14, 180, "Light Orange");
		squares[19] = new StreetSquare(19, "NEW YORK AVENUE", 80, 220, 600, 800, 1000, 1500, 16, 200, "Light Orange");
		squares[20] = new RegularSquare(20, "20");
		squares[21] = new StreetSquare(21, "KENTUCKY AVENUE", 90, 250, 700, 875, 1050, 2050, 18, 220, "Red");
		squares[22] = new ChanceSquare(22, "CHANCE");
		squares[23] = new StreetSquare(23, "INDIANA AVENUE", 90, 250, 700, 875, 1050, 2050, 18, 220, "Red");
		squares[24] = new StreetSquare(24, "ILLINOIS AVENUE", 100, 300, 750, 925, 1100, 2100, 20, 240, "Red");
		squares[25] = new RegularSquare(25, "25");
		squares[26] = new StreetSquare(26, "ATLANTIC AVENUE",110, 330, 800, 975, 1150, 2150, 22, 260,"Yellow");
		squares[27] = new StreetSquare(27, "VENTNOR AVENUE",110, 330, 800, 975, 1150, 2150, 22, 260,"Yellow");
		squares[28] = new RegularSquare(28, "28");
		squares[29] = new StreetSquare(29, "MARVIN GARDENS",120, 350, 850, 1025, 1200, 2200, 24, 280,"Yellow");
		squares[30] = new RegularSquare(30, "30");
		squares[31] = new StreetSquare(31, "PACIFIC AVENUE",130, 390, 900, 1100, 1275, 2275, 26, 300,"Dark Green");
		squares[32] = new StreetSquare(32, "NORTH CAROLINA AVENUE",130, 390, 900, 1100, 1275, 2275, 26, 300,"Dark Green");
		squares[33] = new CommunityChestSquare(33, "COMMUNITY CHEST");
		squares[34] = new StreetSquare(34, "PENNSYLVANIA AVENUE",150, 450, 1000, 1200, 1400, 2400, 28, 320,"Dark Green");
		squares[35] = new RegularSquare(35, "34");
		squares[36] = new ChanceSquare(36, "CHANCE");
		squares[37] = new StreetSquare(37, "PARK PLACE",175, 500, 1100, 1300, 1500, 2500, 35, 350,"Dark Blue");
		squares[38] = new RegularSquare(38, "37");
		squares[39] = new StreetSquare(39, "BOARDWALK",200, 600, 1400, 1700, 2000, 3000, 50, 400,"Dark Blue");
		squares[40] = new TestSquare(40, "Squeeze Play");
		squares[41] = new StreetSquare(41, "THE EMBARCADERO",85, 240, 475, 670, 1025, 1525, 17, 210,"White");
		squares[42] = new StreetSquare(42, "FISHERMAN'S WHARF",85, 240, 475, 670, 1025, 1525, 17, 210,"White");
		squares[43] = new TestSquare(43, "TELEPHONE COMPANY");
		squares[44] = new CommunityChestSquare(44, "COMMUNITY CHEST");
		squares[45] = new StreetSquare(45, "BEACON STREET",160, 470, 1050, 1250, 1500, 2500, 30, 330,"Black");
		squares[46] = new BonusSquare(46, "Bonus");
		squares[47] = new StreetSquare(47, "BOYLSTON STREET",160, 470, 1050, 1250, 1500, 2500, 30, 330,"Black");
		squares[48] = new StreetSquare(48, "NEWBURY STREET",185, 550, 1200, 1500, 1700, 2700, 40, 380,"Black");
		squares[49] = new TestSquare(49, "RAILROAD");
		squares[50] = new StreetSquare(50, "FIFTH AVENUE",220, 650, 1500, 1800, 2100, 3600, 60, 430,"Grey");
		squares[51] = new StreetSquare(51, "MADISON AVENUE",220, 650, 1500, 1800, 2100, 3600, 60, 430,"Grey");
		squares[52] = new TestSquare(52, "STOCK EXCHANGE");
		squares[53] = new StreetSquare(53, "WALL STREET",220, 650, 1500, 1800, 2100, 3600, 60, 430,"Grey");
		squares[54] = new TaxRefundSquare(54, "Tax Refund");
		squares[55] = new TestSquare(55, "GAS COMPANY");
		squares[56] = new ChanceSquare(56, "CHANCE");
		squares[57] = new StreetSquare(57, "FLORIDA AVENUE",45, 120, 350, 500, 700, 1200, 9, 130,"Brown");
		squares[58] = new TunnelSquare(58, "Holland Tunnel 1");
		squares[59] = new StreetSquare(59, "MIAMI AVENUE",45, 120, 350, 500, 700, 1200, 9, 130,"Brown");
		squares[60] = new StreetSquare(60, "BISCAYNE AVENUE",55, 160, 475, 650, 800, 1300, 11, 150,"Brown");
		squares[61] = new TestSquare(61, "RAILROAD");
		squares[62] = new ReverseDirectionSquare(62, "Reverse Direction");
		squares[63] = new StreetSquare(63, "LOMBARD STREET",85, 240, 475, 670, 1025, 1525, 17, 210,"White");
		squares[64] = new SubwaySquare(64, "Subway");
		squares[65] = new StreetSquare(65, "LAKE STREET",5, 15, 45, 80, 125, 625, 1, 30,"Light Pink");
		squares[66] = new CommunityChestSquare(66, "COMMUNITY CHEST");
		squares[67] = new StreetSquare(67, "NICOLLET AVENUE",5, 15, 45, 80, 125, 625, 1, 30,"Light Pink");
		squares[68] = new StreetSquare(68, "HENNEPIN AVENUE",15, 45, 120, 240, 350, 850, 3, 60,"Light Pink");
		squares[69] = new TestSquare(69, "BUS TICKET");
		squares[70] = new TestSquare(70, "CHECKER CAB COMPANY");
		squares[71] = new TestSquare(71, "RAILROAD");
		squares[72] = new StreetSquare(72, "ESPLANADE AVENUE",25, 80, 225, 360, 600, 1000, 5, 90,"Light Green");
		squares[73] = new StreetSquare(73, "CANAL STREET",25, 80, 225, 360, 600, 1000, 5, 90,"Light Green");
		squares[74] = new ChanceSquare(74, "CHANCE");
		squares[75] = new TestSquare(75, "CABLE COMPANY");
		squares[76] = new StreetSquare(76, "MAGAZINE STREET",40, 100, 300, 450, 600, 1100, 8, 120,"Light Green");
		squares[77] = new StreetSquare(77, "BOURBON STREET",40, 100, 300, 450, 600, 1100, 8, 120,"Light Green");
		squares[78] = new TunnelSquare(78, "Holland Tunnel 2");
		squares[79] = new AuctionSquare(79, "Auction");
		squares[80] = new StreetSquare(80, "KATY FREEWAY",55, 160, 475, 650, 800, 1300, 11, 150,"Light Yellow");
		squares[81] = new StreetSquare(81, "WESTHEIMER ROAD",55, 160, 475, 650, 800, 1300, 11, 150,"Light Yellow");
		squares[82] = new TestSquare(82, "INTERNET SERVICE PROVIDER");
		squares[83] = new StreetSquare(83, "KIRBY DRIVE",70, 200, 550, 750, 950, 1450, 14, 180,"Light Yellow");
		squares[84] = new StreetSquare(84, "CULLEN BOULEVARD",70, 200, 550, 750, 950, 1450, 14, 180,"Light Yellow");
		squares[85] = new ChanceSquare(85, "CHANCE");
		squares[86] = new TestSquare(86, "BLACK WHITE CAB COMPANY");
		squares[87] = new StreetSquare(87, "DEKALB AVENUE",85, 240, 670, 840, 1025, 1525, 17, 210,"Green");
		squares[88] = new CommunityChestSquare(88, "COMMUNITY CHEST");
		squares[89] = new StreetSquare(89, "YOUNG INT'L BOULEVARD",85, 240, 670, 840, 1025, 1525, 17, 210,"Green");
		squares[90] = new StreetSquare(90, "DECATUR STREET",100, 300, 750, 925, 1100, 1600, 20, 240,"Green");
		squares[91] = new StreetSquare(91, "PEACHTREE STREET",100, 300, 750, 925, 1100, 1600, 20, 240,"Green");
		squares[92] = new RegularSquare(92, "Payday");
		squares[93] = new StreetSquare(93, "RANDOLPH STREET",115, 345, 825, 1010, 1180, 2180, 23, 270,"Blood Red");
		squares[94] = new ChanceSquare(94, "CHANCE");
		squares[95] = new StreetSquare(95, "LAKE SHORE DRIVE",115, 345, 825, 1010, 1180, 2180, 23, 270,"Blood Red");
		squares[96] = new StreetSquare(96, "WACKER DRIVE",130, 390, 900, 1100, 1275, 2275, 26, 300,"Blood Red");
		squares[97] = new StreetSquare(97, "MICHIGAN AVENUE",130, 390, 900, 1100, 1275, 2275, 26, 300,"Blood Red");
		squares[98] = new TestSquare(98, "YELLOW CAB COMPAANY");
		squares[99] = new TestSquare(99, "RAILROAD");
		squares[100] = new CommunityChestSquare(100, "COMMUNITY CHEST");
		squares[101] = new StreetSquare(101, "SOUTH TEMPLE",160, 470, 1050, 1250, 1500, 2500, 32, 330,"Dark Yellow");
		squares[102] = new StreetSquare(102, "WEST TEMPLE",160, 470, 1050, 1250, 1500, 2500, 32, 330,"Dark Yellow");
		squares[103] = new TestSquare(103, "TRASH COLLECTOR");
		squares[104] = new StreetSquare(104, "NORTH TEMPLE",170, 520, 1125, 1425, 1275, 1650, 32, 360,"Dark Yellow");
		squares[105] = new StreetSquare(105, "TEMPLE SQUARE",170, 520, 1125, 1425, 1275, 1650, 32, 360,"Dark Yellow");
		squares[106] = new GoToJailSquare(106, "Go To Jail");
		squares[107] = new StreetSquare(107, "SOUTH STREET",210, 575, 1300, 1600, 1800, 3300, 45, 390,"Light Orange");
		squares[108] = new StreetSquare(108, "BROAD STREET",210, 575, 1300, 1600, 1800, 3300, 45, 390,"Light Orange");
		squares[109] = new StreetSquare(109, "WALLNUT STREET",225, 630, 1450, 1750, 2050, 3550, 55, 420,"Light Orange");
		squares[110] = new CommunityChestSquare(110, "COMMUNITY CHEST");
		squares[111] = new StreetSquare(111, "MARKET STREET",225, 630, 1450, 1750, 2050, 3550, 55, 420,"Light Orange");
		squares[112] = new TestSquare(112, "BUS TICKET");
		squares[113] = new TestSquare(113, "SEWAGE SYSTEM");
		squares[114] = new TestSquare(114, "UTE CAB COMPANY");
		squares[115] = new BirthdayGiftSquare(115, "Birthday Gift");
		squares[116] = new StreetSquare(116, "MULLHOLLAND DRIVE",350, 750, 1600, 1850, 2100, 3600, 70, 450,"Dark Brown");
		squares[117] = new StreetSquare(117, "VENTURA BOULEVARD",400, 825, 1800, 2175, 2550, 4050, 80, 480,"Dark Brown");
		squares[118] = new ChanceSquare(118, "CHANCE");
		squares[119] = new StreetSquare(119, "RODEO DRIVE",450, 900, 2000, 2500, 3000, 4500, 90, 510,"Dark Brown");
	}

	public static SquareFactory getInstance() {
		if(instance == null) instance = new SquareFactory();
		return instance;
	}

	public Square getSquare(int i){
		return squares[i];
	}

	public Square[] getSquares(){
		return squares;
	}
	/**
	 * @requires:Nothing.
	 * @modifies:unownedStreetSquares
	 * @effects:Adds unowned Squares to the list unownedStreetSquares.
	 */
	public ArrayList<Square> getUnownedStreetSquares() {
		ArrayList<Square> unownedStreetSquares = new ArrayList<Square>();
		for(int i=0; i<squares.length; i++) {
			if(squares[i].getClass() == StreetSquare.class && !((StreetSquare)squares[i]).isOwned) {
				unownedStreetSquares.add(squares[i]);
			}
		}
		return unownedStreetSquares;
	}

}