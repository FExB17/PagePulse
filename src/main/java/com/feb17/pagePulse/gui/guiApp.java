package com.feb17.pagePulse.gui;

import com.feb17.pagePulse.ScreenshotService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class guiApp {

    // https://www.zalando.de
    public static void main(String[] args) {

        // Das Fenster erstellen
        JFrame frame = new JFrame("PagePulse");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Das Suchfeld erstellen
        JTextField searchField = new JTextField();
        searchField.setSize(800, 30);


        // Den Button erstellen
        JButton searchButton = new JButton("Search");


        // Ein Panel zur Anordnung
        BorderLayout layout = new BorderLayout();
        JPanel panel = new JPanel(layout);
        panel.add(searchField, BorderLayout.NORTH);
        panel.add(searchButton, BorderLayout.SOUTH);


        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel,BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        Runnable triggerScreenshot = () -> handleScreenshot(searchField, imageLabel, panel);
        searchButton.addActionListener(e -> triggerScreenshot.run());
        searchField.addActionListener(e -> triggerScreenshot.run());
    }

    private static void handleScreenshot(JTextField searchField, JLabel label, JPanel panel){
        ScreenshotService ss = new ScreenshotService();
        String url = searchField.getText();
        System.out.println("Browsing... for: " + searchField.getText());

        BufferedImage image = ss.captureScreenshotBuffered(url);
            label.setIcon(new ImageIcon(image));
            panel.revalidate();
            panel.repaint();
    }
}
