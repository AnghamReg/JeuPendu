package test;

import arbre.Classification;
import arbre.DicTree;
import interfaceGraphique.MenuWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TestJeu {

	public static void main(String[] args) throws Exception {
		try {
			Clip backgroundMusic;
			URL soundUrl = MenuWindow.class.getResource("/resources/backgroundMusic.wav"); // Adjust the path
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
			backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(audioInputStream);
			backgroundMusic.start();
			backgroundMusic.loop(backgroundMusic.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DicTree arbre = new DicTree("$");

		// test exemple du cahier de charge
		List<String> wordsArrayExemple = new ArrayList<>();
		wordsArrayExemple.add("cas");
		wordsArrayExemple.add("ce");
		wordsArrayExemple.add("ces");
		wordsArrayExemple.add("ci");
		wordsArrayExemple.add("de");
		wordsArrayExemple.add("des");
		wordsArrayExemple.add("do");
		Collections.sort(wordsArrayExemple);
		for (String string : wordsArrayExemple) {
			arbre.addWord(string);

		}
		arbre.drawTree();
		ArrayList<String> arr = arbre.findNodeByValueReturnList("\0");
		System.out.println(arr);
		// ajouter les mots au dictionnaire

		File file = new File("./Dict.txt");
		arbre.setPath(file.getPath());
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> wordsArray = new ArrayList<>();
		String st;
		Classification classification = new Classification();
		while ((st = br.readLine()) != null) {
			if (wordsArray.contains(st.toUpperCase()) || st.equals(""))
				continue;
			wordsArray.add(st.toUpperCase());
		}
		Collections.sort(wordsArray);
		for (String string : wordsArray) {
			arbre.addWord(string);
		}
		Map<String, Float> map = arbre.calculateDifficulty();
		classification.classify(map);
		classification.listsLength();

		// commencer le jeu
		MenuWindow penduPanel = new MenuWindow();
	}
}
