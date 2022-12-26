package Final;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static Final.sounds.playMusic;

public class START_GAME extends javax.swing.JFrame {

    public START_GAME() {

        initComponents();
        setSize(1280, 800);
        setLocationRelativeTo(null);
        JButton bu = new JButton();
        JPanel pa = new JPanel();

        getContentPane().add(pa, BorderLayout.CENTER);
        Image icon = new ImageIcon(this.getClass().getResource("icn.jpg")).getImage();
        this.setIconImage(icon);

    }
    
    
     static String filepath = "src\\Audio\\Arcade-background-music-retro-style.wav";
    static sounds pla = sounds.getInstance();
    static long clpos;
    static boolean isp = true;
    static boolean isL = false;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlayBtn = new javax.swing.JButton();
        InstructionBtn = new javax.swing.JButton();
        ExitBtn = new javax.swing.JButton();
        PlayBtn1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        PlayBtn.setBackground(new java.awt.Color(51, 51, 255));
        PlayBtn.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        PlayBtn.setForeground(new java.awt.Color(255, 255, 255));
        PlayBtn.setText("Two Players");
        PlayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayBtnActionPerformed(evt);
            }
        });
        getContentPane().add(PlayBtn);
        PlayBtn.setBounds(50, 260, 210, 40);

        InstructionBtn.setBackground(new java.awt.Color(51, 51, 255));
        InstructionBtn.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        InstructionBtn.setForeground(new java.awt.Color(255, 255, 255));
        InstructionBtn.setText("How to play");
        InstructionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstructionBtnActionPerformed(evt);
            }
        });
        getContentPane().add(InstructionBtn);
        InstructionBtn.setBounds(50, 410, 210, 40);

        ExitBtn.setBackground(new java.awt.Color(255, 51, 51));
        ExitBtn.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        ExitBtn.setForeground(new java.awt.Color(255, 255, 255));
        ExitBtn.setText("Exit");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });
        getContentPane().add(ExitBtn);
        ExitBtn.setBounds(60, 560, 200, 40);

        PlayBtn1.setBackground(new java.awt.Color(51, 51, 255));
        PlayBtn1.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        PlayBtn1.setForeground(new java.awt.Color(255, 255, 255));
        PlayBtn1.setText("Single Player");
        PlayBtn1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PlayBtn1MouseMoved(evt);
            }
        });
        PlayBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayBtn1ActionPerformed(evt);
            }
        });
        getContentPane().add(PlayBtn1);
        PlayBtn1.setBounds(50, 150, 210, 40);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Texture/s1.png"))); // NOI18N
        jButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton1StateChanged(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1200, 30, 50, 50);

        jLabel1.setForeground(new java.awt.Color(51, 255, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Texture/qq.jpg"))); // NOI18N
        jLabel1.setText("hgjghjghj");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -70, 1270, 950);

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(440, 290, 380, 130);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed

        System.exit(0);
    }//GEN-LAST:event_ExitBtnActionPerformed


    private void InstructionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstructionBtnActionPerformed

                String filepath="src\\Audio\\Tv-turn-off-sound-effect.wav";
                playMusic(filepath);
                pla.clip.start();
//        String name=JOptionPane.showInputDialog(null,"Enter Name");
//     JOptionPane.showMessageDialog(null, "Good Job ♥ " + name);
        new HowToPlay().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_InstructionBtnActionPerformed

    private void PlayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayBtnActionPerformed
                String filepath="src\\Audio\\Tv-turn-off-sound-effect.wav";
                playMusic(filepath);
                pla.clip.start();
      //ping pong bots

        new multible().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PlayBtnActionPerformed

    private void PlayBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayBtn1ActionPerformed

        String filepath="src\\Audio\\Tv-turn-off-sound-effect.wav";
                playMusic(filepath);
                pla.clip.start();

        new options().setVisible(true);

        this.dispose();
    }//GEN-LAST:event_PlayBtn1ActionPerformed

    private void PlayBtn1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayBtn1MouseMoved

    }//GEN-LAST:event_PlayBtn1MouseMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (isp) {

            clpos = pla.clip.getMicrosecondPosition();
            pla.clip.stop();

        } else {

            pla.clip.setMicrosecondPosition(clpos);
            pla.clip.start();

        }
        isp = !isp;
        
        
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton1StateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1StateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(START_GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(START_GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(START_GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(START_GAME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new START_GAME().setVisible(true);
                pla.playMusic(filepath);
                pla.clip.setMicrosecondPosition(0);
                clpos=0;
                pla.clip.start();
//                String filepath = "src\\Audio\\Arcade-background-music-retro-style.wav";
//                 playMusic(filepath);
                

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton InstructionBtn;
    private javax.swing.JButton PlayBtn;
    private javax.swing.JButton PlayBtn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
