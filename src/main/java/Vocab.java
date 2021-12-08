import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;

class Vocab {
    private String mode;
    private int sizeList;
    private ArrayList<String> vocabList = new ArrayList<String>();
    private Random random = new Random();

    public Vocab() {}

    public Vocab(String m, ArrayList<String> li) {
        mode = m;
        vocabList = li;
        sizeList = vocabList.size();
        System.out.println("VOcab complete");
    }

    public String getMode() {
        return mode;
    }

    public String randomWord() { // Random word
        int ran = random.nextInt(sizeList);
        return vocabList.get(ran);
    }

    public void printFileWord() { // check Reading File
        System.out.printf("====== Mode %-9s reading... =====\n", mode);
        for (int i = 0; i < vocabList.size(); i++) {
            System.out.printf("\t [%8s] %-15s \n", mode, vocabList.get(i));
        }
        System.out.println("");
    }
}
