package controller;

import App.Application;
import model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseController implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 80) {
            Model.pause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
