package gui;

public class DieFace {

	static String[] faceTexts = {
			"<html><center><span style='font-size:10px'>Bus</span></center></html>",
			"<html><center><span style='font-size:10px'>Mr.<br>Monopoly</span></center></html>",
			"<html><center><span style='font-size:10px'>Mr.<br>Monopoly</span></center></html>",

			"<html><span style='font-size:10px'>"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;<br>"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "</span></html>" ,

					"<html><span style='font-size:10px'>"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;<br>"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>"
							+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ "</span></html>" ,

							"<html><span style='font-size:10px'>"
									+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;<br>"
									+ "&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;<br>"
									+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									+ "</span></html>" ,

									"<html><span style='font-size:10px'>"
											+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;<br>"
											+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>"
											+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;"
											+ "</span></html>" ,

											"<html><span style='font-size:10px'>"
													+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;<br>"
													+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;&nbsp;&nbsp;<br>"
													+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;"
													+ "</span></html>" ,

													"<html><span style='font-size:10px'>"
															+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;<br>"
															+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;<br>"
															+ "&nbsp;&#9679;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#9679;&nbsp;"
															+ "</span></html>" ,
	};
	
	public static String getFaceForValue(int val){
		return faceTexts[val+2];
	}
	
}
