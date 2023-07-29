package main.java.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.java.utils.LoadSave;

import static main.java.utils.Constants.PlayerConstants.*;

public class Player extends Entity {
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false;
    private BufferedImage[][] animations;
    private float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 64, 40, null);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void updatePos() {
        moving = false;
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void setAnimation() {
        int startAni = playerAction;
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if (attacking) {
            playerAction = ATTACK_1;
        }
        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    public boolean getLeft() {
        return this.left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getUp() {
        return this.up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean getRight() {
        return this.right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getDown() {
        return this.down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
