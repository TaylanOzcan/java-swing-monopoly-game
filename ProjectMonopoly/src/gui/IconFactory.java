package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class IconFactory {

	public static JLabel newHouseLabel() {
		JLabel houseLabel =  new JLabel(
				"<html><center><span style='font-size:8px'>H</span></center><html>"
				, SwingConstants.CENTER);
		houseLabel.setPreferredSize(new Dimension(10, 10));
		houseLabel.setBackground(new Color(40, 250, 120, 255));
		houseLabel.setOpaque(true);
		houseLabel.setBorder(LineBorder.createGrayLineBorder());
		return houseLabel;
	}

	public static JLabel newHotelLabel() {
		JLabel hotelLabel = new JLabel("H", SwingConstants.CENTER);
		hotelLabel.setPreferredSize(new Dimension(14, 14));
		hotelLabel.setBackground(new Color(250, 80, 60, 255));
		hotelLabel.setOpaque(true);
		hotelLabel.setBorder(LineBorder.createGrayLineBorder());
		return hotelLabel;
	}

	public static JLabel newSkyscraperLabel(){
		JLabel skyscraperLabel = new JLabel("S", SwingConstants.CENTER);
		skyscraperLabel.setPreferredSize(new Dimension(15, 15));
		skyscraperLabel.setBackground(new Color(40, 80, 200, 255));
		skyscraperLabel.setForeground(Color.WHITE);
		skyscraperLabel.setOpaque(true);
		skyscraperLabel.setBorder(LineBorder.createGrayLineBorder());
		return skyscraperLabel;
	}
}
