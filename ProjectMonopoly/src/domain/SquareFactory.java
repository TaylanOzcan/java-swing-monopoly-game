package domain;
import java.awt.Color;
import java.util.HashMap;

public class SquareFactory {

	private static Square[] squares = new Square[119];;

	// other square types will be implemented in the future
	public static void createSquares() {
		// REQUIRES : The board.
		// MODIFIES : IS THIS JUST THE INPUT?
		// EFFECTS : Creates squares.
		squares[0] = new MoneyGiverSquare(0, "GO", 200);
		squares[1] = new StreetSquare(1, "MEDITERRANEAN AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(100,10,100));
		squares[2] = new CommunityChestSquare(2, "COMMUNITY CHEST");
		squares[3] = new StreetSquare(3, "BALTIC AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(100,10,100));
		squares[4] = new RegularSquare(4, "4");
		squares[5] = new RegularSquare(5, "5");
		squares[6] = new StreetSquare(6, "ORIENTAL AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(10,20,180));
		squares[7] = new ChanceSquare(7, "CHANCE");
		squares[8] = new StreetSquare(8, "VERMONT AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(10,20,180));
		squares[9] = new StreetSquare(9, "CONNECTICUT AVENUE", 30, 45, 60, 75, 100, 150, 2, 30, new Color(10,20,180));
		squares[10] = new RegularSquare(10, "10");
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
		/*		squares[40] = (40, );
		squares[41] = (41, );
		squares[42] = (42, );
		squares[43] = (43, );
		squares[44] = (44, );
		squares[45] = (45, );
		squares[46] = (46, );
		squares[47] = (47, );
		squares[48] = (48, );
		squares[49] = (49, );
		squares[50] = (50, );
		squares[51] = (51, );
		squares[52] = (52, );
		squares[53] = (53, );
		squares[54] = (54, );
		squares[55] = (55, );
		squares[56] = (56, );
		squares[57] = (57, );
		squares[58] = (58, );
		squares[59] = (59, );
		squares[60] = (60, );
		squares[61] = (61, );
		squares[62] = (62, );
		squares[63] = (63, );
		squares[64] = (64, );
		squares[65] = (65, );
		squares[66] = (66, );
		squares[67] = (67, );
		squares[68] = (68, );
		squares[69] = (69, );
		squares[70] = (70, );
		squares[71] = (71, );
		squares[72] = (72, );
		squares[73] = (73, );
		squares[74] = (74, );
		squares[75] = (75, );
		squares[76] = (76, );
		squares[77] = (77, );
		squares[78] = (78, );
		squares[79] = (79, );
		squares[80] = (80, );
		squares[81] = (81, );
		squares[82] = (82, );
		squares[83] = (83, );
		squares[84] = (84, );
		squares[85] = (85, );
		squares[86] = (86, );
		squares[87] = (87, );
		squares[88] = (88, );
		squares[89] = (89, );
		squares[90] = (90, );
		squares[91] = (91, );
		squares[92] = (92, );
		squares[93] = (93, );
		squares[94] = (94, );
		squares[95] = (95, );
		squares[96] = (96, );
		squares[97] = (97, );
		squares[98] = (98, );
		squares[99] = (99, );
		squares[100] = (100, );
		squares[101] = (101, );
		squares[102] = (102, );
		squares[103] = (103, );
		squares[104] = (104, );
		squares[105] = (105, );
		squares[106] = (106, );
		squares[107] = (107, );
		squares[108] = (108, );
		squares[109] = (109, );
		squares[110] = (110, );
		squares[111] = (111, );
		squares[112] = (112, );
		squares[113] = (113, );
		squares[114] = (114, );
		squares[115] = (115, );
		squares[116] = (116, );
		squares[117] = (117, );
		squares[118] = (118, );
		squares[119] = (119, );*/

	}

	public static Square getSquare(int i){
		return squares[i];
	}

	public static Square[] getSquares(){
		return squares;
	}

}