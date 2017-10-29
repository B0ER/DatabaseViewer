package ua.b0er;

import javax.swing.*;
import java.awt.event.ActionListener;

public class IconAndButton extends JButton {
    private Icon icon;

    public IconAndButton(String pathIcon, String description, ActionListener actionListener) {
        icon = createImageIcon(pathIcon, description);
        setText(description);
        setIcon(icon);
        addActionListener(actionListener);
    }

    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
