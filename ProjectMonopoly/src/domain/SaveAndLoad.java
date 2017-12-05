package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import gui.Gui;

public class SaveAndLoad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void save(Gui g) {
		try {
			FileOutputStream fos = new FileOutputStream("monopolysaved.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(g);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load(Gui g) {
		try {
			FileInputStream fis = new FileInputStream("monopolysaved.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			g = (Gui) ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}