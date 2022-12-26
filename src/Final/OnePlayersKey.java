
package Final;

import static Final.START_GAME.pla;
import static Final.sounds.playMusic;
import com.sun.opengl.util.GLUT;
import java.awt.Component;
import java.awt.event.*;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;
import java.io.IOException;
import java.util.BitSet;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.JOptionPane;

public class OnePlayersKey implements GLEventListener, KeyListener {

    GLUT g = new GLUT();

    int UP_RIGHT = 315;
    int UP_LEFT = 45;
    int DOWN_LEFT = 135;
    int DOWN_RIGHT = 225;
    int UP = 0;
    int DOWN = 180;
    int LEFT = 90;
    int RIGHT = 270;
    int changing_angel_Player1 = UP;
    int changing_angel_Player2 = DOWN;
/////////////////////////

    float X0ball = 0;         //x0 axis
    float Y0ball = 0;        //y0 axis
    float slope = 0;        // slpe betwen ball and playr
    float Xball = X0ball;       //holds the new 'Xball' position of ball
    float Yball = Y0ball;     //holds the new 'Yball' position
    boolean movingRight = true;  // check ball x1 increase or decrease
    boolean movingUp = true;    // check ball will crash up or down
    boolean verticle = false;  // check slope not define is vertical if (x1-x0 =0) 
    boolean up = false;        // if slope not define do it if y1>y0
    boolean down = false;     // if slope not define do it if y1<y0
    boolean play = false;    //  begin play if one player touch ball & to be false if game finished
    boolean started = true, paused = false, ended = false, exited = false;

    ////////
    int maxWidth = 100;
    int maxHeight = 100;
    int XforPlayer1 = 0, YforPlayer1 = -80, scoreplayer1;
    int XforPlayer2 = 0, YforPlayer2 = 80, scoreplayer2;
    
    String name = (String) JOptionPane.showInputDialog(null, "Enter Name","Ex: Ahmed");
    Score f1 = new Score(0);
    Score s2 = new Score(0);
    String textureNames[] = {"24.png", "4.png", "123.png", "Back.jpg"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    private boolean moveLeft, moveDown;
    private GLCanvas glc;
    private double slopeX = 0.5, slopeY = 0.5, speed = 1;

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture("Assets" + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        gl.glLoadIdentity();
        gl.glOrtho(-maxWidth, maxWidth, -maxHeight, maxHeight, -1, 1);
    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

        DrawSprite(gl, 0, 0, 100, 3);
        handleKeyPress();

        DrawSpritePlayer1(gl, XforPlayer1, YforPlayer1, 1, 15, changing_angel_Player1);
        //////////////////////////////////////

        if (moveDown) {
            YforPlayer2 -= slopeY * speed;
        } else {
            YforPlayer2 += slopeY * speed;
        }

        if (!moveDown && YforPlayer2 > maxHeight) {
            changeSlope();
            moveDown = true;
        }
        if (moveDown && YforPlayer2 < 5) {
            changeSlope();
            moveDown = false;
        }
        

        DrawSpritePlayer1(gl, XforPlayer2, YforPlayer2, 0, 15, changing_angel_Player2);

        winner(gl);
        gl.glRasterPos2i(-90, 90);
        g.glutBitmapString(5, "Score");
        gl.glRasterPos2i(-90, 80);
        g.glutBitmapString(5, Integer.toString(scoreplayer2));
        gl.glRasterPos2i(-90, -80);
        g.glutBitmapString(5, "Score");
        gl.glRasterPos2i(-90, -90);
        g.glutBitmapString(5, Integer.toString(scoreplayer1));

    }

    void stupidAi() {
        if (XforPlayer2 > Xball) {
            XforPlayer2--;
        }
        if (XforPlayer2 < Xball) {
            XforPlayer2++;
        }

    }

    public void drawball(GL gl) {
        // computer player

        // player 1
        if ((int) Math.sqrt(Math.pow(Xball - XforPlayer1, 2) + Math.pow(Yball - YforPlayer1, 2)) <= 15) {
            X0ball = XforPlayer1;
            Y0ball = YforPlayer1;
            play = true;
            verticle = (Xball - XforPlayer1 == 0);
            if (verticle) {
                if (Y0ball > Yball) {
                    down = true;
                    System.out.println("up.." + up);
                } else {
                    up = true;
                    System.out.println("down.." + down);
                }
            } else {
                down = up = false;
            }
            slope = (Yball - YforPlayer1) / (Xball - XforPlayer1);
            if (YforPlayer1 > Yball && slope < 0) {
                movingUp = false;
                movingRight = true;
                Xball += 10;
            }
            if (YforPlayer1 > Yball && slope > 0) {
                movingUp = false;
                movingRight = false;
                Xball -= 10;
            }

            if (YforPlayer1 < Yball && slope < 0) {
                movingUp = true;
                movingRight = false;
                Xball -= 10;
            }

            if (YforPlayer1 < Yball && slope > 0) {
                movingUp = true;
                movingRight = true;
                Xball += 10;
            }
        }
///// Computer
        if ((int) Math.sqrt(Math.pow(Xball - XforPlayer2, 2) + Math.pow(Yball - YforPlayer2, 2)) <= 15 && movingUp) {
            if(movingUp && movingRight) {
                movingUp = movingRight = false;
            } else if(movingUp && !movingRight) {
                movingRight = true;
                movingUp = false;
            } else if(!movingUp && movingRight){
                movingUp = true;
                movingRight = false;
            } else if(!movingUp && !movingRight){
                movingRight = true; movingUp = false; 
            }
            
            
//            if(movingUp) movingUp = false; 
            
//            System.exit(0);

//            X0ball = XforPlayer2;
//            Y0ball = YforPlayer2;
//            play = true;
//            verticle = (Xball - XforPlayer2 == 0);
//            if (verticle) {
//                if (Y0ball > Yball) {
//                    down = true;
//                } else {
//                    up = true;
//                }
//            } else {
//                down = up = false;
//            }
//            slope = (Yball - YforPlayer2) / (Xball - XforPlayer2);
//            if (YforPlayer1 > Yball && slope < 0) {
//                movingUp = false;
//                movingRight = true;
//                Xball += 10;
//            }
//            if (YforPlayer2 > Yball && slope > 0) {
//                movingUp = false;
//                movingRight = false;
//                Xball -= 10;
//            }
//
//            if (YforPlayer2 < Yball && slope < 0) {
//                movingUp = true;
//                movingRight = false;
//                Xball -= 10;
//            }
//
//            if (YforPlayer2 < Yball && slope > 0) {
//                movingUp = true;
//                movingRight = true;
//                Xball += 10;
//            }
        }
///////// the movement
        if (play) {
            if (!verticle) {
                Yball = (slope * (Xball - X0ball) + Y0ball);
            }

            if (movingRight) {
                if (Xball < 80) {
                    Xball += 1;
                } else {
                    movingRight = false;
                    slope *= -1;
                    X0ball = Xball;
                    Y0ball = Yball;
                }
            }
            if (!movingRight) {
                if (Xball > -80) {
                    Xball -= 1;
                } else {
                    movingRight = true;
                    slope *= -1;
                    X0ball = Xball;
                    Y0ball = Yball;
                }
            }

            if (movingUp) {
                if (!(Yball < 90)) {
                    slope *= -1;
                    X0ball = Xball;
                    Y0ball = Yball;
                    movingUp = false;
                }
            }
            if (!movingUp) {
                if (!(Yball > -90)) {
                    slope *= -1;
                    X0ball = Xball;
                    Y0ball = Yball;
                    movingUp = true;
                }
            }

            if (down) {
                Yball--;
                if (Yball <= -100) {
                    up = true;
                }
                down = false;
            }

            if (up) {
                Yball++;
                if (Yball >= 100) {
                    down = true;
                }
                up = false;
            }
        }

        DrawSprite(gl, Xball, Yball, 10, 2);

    }

    public void winner(GL gl) {
        if ((Xball > -30 && Xball < 30) && Yball <= -90 && play) {
            reset();
            scoreplayer2 = f1.getScore();
            scoreplayer2++;
            f1.setScore(scoreplayer2);
        }
        if ((Xball > -30 && Xball < 30) && Yball >= 90 && play) {
            reset();
            scoreplayer1 = s2.getScore();

            scoreplayer1++;
            s2.setScore(scoreplayer1);
        }
        
        
        if (f1.getScore() >= 2) {
            paused = true;
            started = false;
            ended = true;
            f1.setScore(0);
            s2.setScore(0);

            new Lost().setVisible(true);
            String filepath = "src\\Audio\\challenge-lose-By-Tuna.wav";
            playMusic(filepath);
            pla.clip.start();
            JOptionPane.showMessageDialog(null, " Loser " + name + " ;D");

        } else if (s2.getScore() >= 2) {
            paused = true;
            ended = true;
            started = false;
            f1.setScore(0);
            s2.setScore(0);
            new win().setVisible(true);
            String filepath = "src\\Audio\\win-By-Tuna.wav";
            playMusic(filepath);
            pla.clip.start();
            JOptionPane.showMessageDialog(null, " Good Job " + name + " :O");

        } else {
            ended = false;
        }
        drawball(gl);
    }

    public void reset() {
        XforPlayer1 = 0;
        YforPlayer1 = -80;
        XforPlayer2 = 0;
        YforPlayer2 = 80;
        X0ball = 0;
        Y0ball = 0;
        slope = 0;
        Xball = X0ball;
        Yball = Y0ball;
        movingRight = true;
        movingUp = true;
        verticle = false;
        up = false;
        down = false;
        play = false;
    }

    void changeSlope() {
        double cita = Math.random() * (Math.PI / 2);
        slopeX = Math.cos(cita);
        slopeY = Math.sin(cita);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, float x, float y, int r, int index) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glScaled(r, r, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawSpritePlayer1(GL gl, int x, int y, int index, float scale, int angle) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glScaled(scale, scale, 1);
        gl.glRotatef(angle, 0, 0, 1); //Dir
        //System.out.println(XforPlayer1 +" " + YforPlayer1);
        gl.glBegin(GL.GL_QUADS);

        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
        stupidAi();
    }

    public void handleKeyPress() {

        if (isKeyPressed(VK_LEFT)) {
            if (XforPlayer1 > -maxWidth + 25) {
                XforPlayer1--;
            }
        }

        if (isKeyPressed(VK_RIGHT)) {
            if (XforPlayer1 < maxWidth - 25) {
                XforPlayer1++;
            }
        }

        if (isKeyPressed(VK_DOWN)) {
            if (YforPlayer1 > -(maxHeight / 4 )- 60) {
                YforPlayer1--;
            }
        }

        if (isKeyPressed(VK_UP)) {
            if (YforPlayer1 < (maxHeight / 4) - 30) {
                YforPlayer1++;
            }
        }
        //Directions Player1
        if (isKeyPressed(VK_RIGHT) && isKeyPressed(VK_UP)) {
            changing_angel_Player1 = UP_RIGHT;
        } else if (isKeyPressed(VK_LEFT) && isKeyPressed(VK_UP)) {
            changing_angel_Player1 = UP_LEFT;
        } else if (isKeyPressed(VK_LEFT) && isKeyPressed(VK_DOWN)) {
            changing_angel_Player1 = DOWN_LEFT;
        } else if (isKeyPressed(VK_RIGHT) && isKeyPressed(VK_DOWN)) {
            changing_angel_Player1 = DOWN_RIGHT;
        } else if (isKeyPressed(VK_UP)) {
            changing_angel_Player1 = UP;
        } else if (isKeyPressed(VK_RIGHT)) {
            changing_angel_Player1 = RIGHT;
        } else if (isKeyPressed(VK_LEFT)) {
            changing_angel_Player1 = LEFT;
        } else if (isKeyPressed(VK_DOWN)) {
            changing_angel_Player1 = DOWN;
        }
//////////////////////////////////////////////////////////
        if (isKeyPressed(VK_A)) {
            if (XforPlayer2 > -maxWidth + 5) {
                XforPlayer2--;
            }
        }

        if (isKeyPressed(VK_D)) {
            if (XforPlayer2 < maxWidth - 10) {
                XforPlayer2++;
            }
        }

        if (isKeyPressed(VK_S)) {
            if (YforPlayer2 > maxHeight / 4 - 15) {
                YforPlayer2--;
            }
        }

        if (isKeyPressed(VK_W)) {
            if (YforPlayer2 < maxHeight - 20) {
                YforPlayer2++;
            }
        }
        //Directions Player1
        if (isKeyPressed(VK_D) && isKeyPressed(VK_W)) {
            changing_angel_Player2 = UP_RIGHT;
        } else if (isKeyPressed(VK_A) && isKeyPressed(VK_W)) {
            changing_angel_Player2 = UP_LEFT;
        } else if (isKeyPressed(VK_A) && isKeyPressed(VK_S)) {
            changing_angel_Player2 = DOWN_LEFT;
        } else if (isKeyPressed(VK_D) && isKeyPressed(VK_S)) {
            changing_angel_Player2 = DOWN_RIGHT;
        } else if (isKeyPressed(VK_W)) {
            changing_angel_Player2 = UP;
        } else if (isKeyPressed(VK_D)) {
            changing_angel_Player2 = RIGHT;
        } else if (isKeyPressed(VK_A)) {
            changing_angel_Player2 = LEFT;
        } else if (isKeyPressed(VK_S)) {
            changing_angel_Player2 = DOWN;
        }

    }

    void setCanvas(GLCanvas glcanvas) {
        this.glc = glcanvas;
    }
    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
        
        
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}