package domain;
import java.awt.Color;
import java.util.HashMap;

public class SquareFactory {

	private Square[] squares = new Square[120];
	private static SquareFactory instance;

	private SquareFactory() {
		// EFFECTS : Creates squares.
		squares[0] = new RegularSquare(0, "GO");
		squares[1] = new StreetSquare(1, "MEDITERRANEAN AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(100,10,100));
		squares[2] = new CommunityChestSquare(2, "COMMUNITY CHEST");
		squares[3] = new StreetSquare(3, "BALTIC AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(100,10,100));
		squares[4] = new RegularSquare(4, "4");
		squares[5] = new RegularSquare(5, "5");
		squares[6] = new StreetSquare(6, "ORIENTAL AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(10,20,180));
		squares[7] = new ChanceSquare(7, "CHANCE");
		squares[8] = new StreetSquare(8, "VERMONT AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(10,20,180));
		squares[9] = new StreetSquare(9, "CONNECTICUT AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(10,20,180));
		squares[10] = new RegularSquare(10, "Jail");
		squares[11] = new StreetSquare(11, "ST. CHARLES PLACE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,10,180));
		squares[12] = new RegularSquare(12, "12");
		squares[13] = new StreetSquare(13, "STATES AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,10,180));
		squares[14] = new StreetSquare(14, "VIRGINIA AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,10,180));
		squares[15] = new RegularSquare(15, "15");
		squares[16] = new StreetSquare(16, "ST. JAMES PLACE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,120,10));
		squares[17] = new CommunityChestSquare(17, "COMMUNITY CHEST");
		squares[18] = new StreetSquare(18, "TENNESEE AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,120,10));
		squares[19] = new StreetSquare(19, "NEW YORK AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,120,10));
		squares[20] = new RegularSquare(20, "20");
		squares[21] = new StreetSquare(21, "KENTUCKY AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,200,10));
		squares[22] = new ChanceSquare(22, "CHANCE");
		squares[23] = new StreetSquare(23, "INDIANA AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,200,10));
		squares[24] = new StreetSquare(24, "ILLINOIS AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(200,200,10));
		squares[25] = new RegularSquare(25, "25");
		squares[26] = new StreetSquare(26, "ATLANTIC AVENUE",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[27] = new StreetSquare(27, "VENTNOR AVENUE",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[28] = new RegularSquare(28, "28");
		squares[29] = new StreetSquare(29, "MARVIN GARDENS",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[30] = new RegularSquare(30, "30");
		squares[31] = new StreetSquare(31, "PACIFIC AVENUE",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[32] = new StreetSquare(32, "NORTH CAROLINA AVENUE",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[33] = new CommunityChestSquare(33, "COMMUNITY CHEST");
		squares[34] = new StreetSquare(34, "PENNSYLVANIA AVENUE",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[35] = new RegularSquare(35, "34");
		squares[36] = new ChanceSquare(36, "CHANCE");
		squares[37] = new StreetSquare(37, "PARK PLACE",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[38] = new RegularSquare(38, "37");
		squares[39] = new StreetSquare(39, "BOARDWALK",30, 45, 60, 75, 100, 150, 2, 30,null);
		squares[40] = new TestSquare(40, "");
		squares[41] = new TestSquare(41, "");
		squares[42] = new TestSquare(42, "");
		squares[43] = new TestSquare(43, "");
		squares[44] = new TestSquare(44, "");
		squares[45] = new TestSquare(45, "");
		squares[46] = new BonusSquare(46, "Bonus");
		squares[47] = new TestSquare(47, "");
		squares[48] = new TestSquare(48, "");
		squares[49] = new TestSquare(49, "");
		squares[50] = new TestSquare(50, "");
		squares[51] = new TestSquare(51, "");
		squares[52] = new TestSquare(52, "");
		squares[53] = new TestSquare(53, "");
		squares[54] = new TaxRefundSquare(54, "Tax Refund");
		squares[55] = new TestSquare(55, "");
		squares[56] = new TestSquare(56, "");
		squares[57] = new TestSquare(57, "");
		squares[58] = new TunnelSquare(58, "Holland Tunnel 1");
		squares[59] = new TestSquare(59, "");
		squares[60] = new TestSquare(60, "");
		squares[61] = new TestSquare(61, "");
		squares[62] = new TestSquare(62, "");
		squares[63] = new TestSquare(63, "");
		squares[64] = new SubwaySquare(64, "Subway");
		squares[65] = new TestSquare(65, "");
		squares[66] = new TestSquare(66, "");
		squares[67] = new TestSquare(67, "");
		squares[68] = new TestSquare(68, "");
		squares[69] = new TestSquare(69, "");
		squares[70] = new TestSquare(70, "");
		squares[71] = new TestSquare(71, "");
		squares[72] = new TestSquare(72, "");
		squares[73] = new TestSquare(73, "");
		squares[74] = new TestSquare(74, "");
		squares[75] = new TestSquare(75, "");
		squares[76] = new TestSquare(76, "");
		squares[77] = new TestSquare(77, "");
		squares[78] = new TunnelSquare(78, "Holland Tunnel 2");
		squares[79] = new TestSquare(79, "");
		squares[80] = new TestSquare(80, "");
		squares[81] = new TestSquare(81, "");
		squares[82] = new TestSquare(82, "");
		squares[83] = new TestSquare(83, "");
		squares[84] = new TestSquare(84, "");
		squares[85] = new TestSquare(85, "");
		squares[86] = new TestSquare(86, "");
		squares[87] = new TestSquare(87, "");
		squares[88] = new TestSquare(88, "");
		squares[89] = new TestSquare(89, "");
		squares[90] = new TestSquare(90, "");
		squares[91] = new TestSquare(91, "");
		squares[92] = new RegularSquare(92, "Payday");
		squares[93] = new TestSquare(93, "");
		squares[94] = new TestSquare(94, "");
		squares[95] = new TestSquare(95, "");
		squares[96] = new TestSquare(96, "");
		squares[97] = new TestSquare(97, "");
		squares[98] = new TestSquare(98, "");
		squares[99] = new TestSquare(99, "");
		squares[100] = new TestSquare(100, "");
		squares[101] = new TestSquare(101, "");
		squares[102] = new TestSquare(102, "");
		squares[103] = new TestSquare(103, "");
		squares[104] = new TestSquare(104, "");
		squares[105] = new TestSquare(105, "");
		squares[106] = new GoToJailSquare(106, "Go To Jail");
		squares[107] = new TestSquare(107, "");
		squares[108] = new TestSquare(108, "");
		squares[109] = new TestSquare(109, "");
		squares[110] = new TestSquare(110, "");
		squares[111] = new TestSquare(111, "");
		squares[112] = new TestSquare(112, "");
		squares[113] = new TestSquare(113, "");
		squares[114] = new TestSquare(114, "");
		squares[115] = new BirthdayGiftSquare(115, "Birthday Gift");
		squares[116] = new TestSquare(116, "");
		squares[117] = new TestSquare(117, "");
		squares[118] = new TestSquare(118, "");
		squares[119] = new TestSquare(119, "");
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

}