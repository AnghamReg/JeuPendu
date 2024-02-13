package arbre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Classification {
    public static List<String> easyWords = new ArrayList<String>();
    public static List<String> mediumWords = new ArrayList<String>();
    public static List<String> hardWords = new ArrayList<String>();

    public Classification() {
    }

    public Classification(List<String> easyWords, List<String> mediumWords, List<String> hardWords) {
        Classification.easyWords = easyWords;
        Classification.mediumWords = mediumWords;
        Classification.hardWords = hardWords;
    }

    public List<String> getEasyWords() {
        return easyWords;
    }

    public List<String> getHardWords() {
        return hardWords;
    }

    public List<String> getMediumWords() {
        return mediumWords;
    }

    public void setEasyWords(List<String> easyWords) {
        Classification.easyWords = easyWords;
    }

    public void setHardWords(List<String> hardWords) {
        Classification.hardWords = hardWords;
    }

    public void setMediumWords(List<String> mediumWords) {
        Classification.mediumWords = mediumWords;
    }

    public void classify(Map<String, Float> words) {
        for (Map.Entry<String, Float> entry : words.entrySet()) {
            if (entry.getValue() <= 1)
                this.getEasyWords().add(entry.getKey());
            else if (entry.getValue() <= 1.7)
                this.getMediumWords().add(entry.getKey());
            else
                this.getHardWords().add(entry.getKey());
        }
    }

    public void listsLength() {
        System.out.println("You have " + this.getEasyWords().size() + " easy words");
        System.out.println("You have " + this.getMediumWords().size() + " medium words");
        System.out.println("You have " + this.getHardWords().size() + " hard words");
    }

    public String randomWord(int difficulty) {
        Random random = new Random();
        int randomIndex = 0;
        switch (difficulty) {
            case 1:
                randomIndex = random.nextInt(this.getEasyWords().size());
                return this.getEasyWords().get(randomIndex);
            case 2:
                randomIndex = random.nextInt(this.getMediumWords().size());
                return this.getMediumWords().get(randomIndex);
            case 3:
                randomIndex = random.nextInt(this.getHardWords().size());
                return this.getHardWords().get(randomIndex);
            default:
                break;
        }
        return "";
    }

}
