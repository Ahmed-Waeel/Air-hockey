package Final;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;

public class RunForTwoPlayer extends JFrame {

    public static void main(String[] args) {
        new RunForTwoPlayer();
    }


    public RunForTwoPlayer() {
        GLCanvas glcanvas;
        Animator animator;
        
        TwoPlayers listener = new TwoPlayers();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        listener.setCanvas(glcanvas);
        
        add(glcanvas, BorderLayout.CENTER);
        
        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();

        setTitle("Air Hockey");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
