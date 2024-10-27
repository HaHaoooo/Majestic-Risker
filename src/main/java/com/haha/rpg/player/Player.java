package com.haha.rpg.player;

import com.haha.rpg.Direction;
import com.haha.rpg.gui.Camera;
import com.haha.rpg.util.JsonHelper;
import com.haha.rpg.util.ResourceLocation;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Player {

    private BufferedImage[] walkingUp, walkingDown, walkingLeft, walkingRight;
    private int currentFrame = 0;
    private long lastFrameTime;
    private final int frameWidth = 30;
    private final int frameHeight = 56;

    private int playerX = new Random().nextInt(800);
    private int playerY = new Random().nextInt(600);
    public int speed;
    public double health;
    public double maxHealth;
    public double mana;
    public double maxMana;
    public double stamina;
    public double maxStamina;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Direction direction = Direction.DOWN;

    public Player() {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(ResourceLocation.getLocation("player.png")));
            walkingDown = new BufferedImage[6];
            walkingLeft = new BufferedImage[6];
            walkingRight = new BufferedImage[6];
            walkingUp = new BufferedImage[6];

            for (int i = 0; i < 6; i++) {
                int x = 34;
                int y = 14;
                walkingDown[i] = spriteSheet.getSubimage(i * (frameWidth + x), 0, frameWidth, frameHeight);
                walkingLeft[i] = spriteSheet.getSubimage(i * (frameWidth + x), frameHeight + y, frameWidth, frameHeight);
                walkingRight[i] = spriteSheet.getSubimage(i * (frameWidth + x), 2 * (frameHeight + y) + 2, frameWidth, frameHeight);
                walkingUp[i] = spriteSheet.getSubimage(i * (frameWidth + x), 3 * (frameHeight + y), frameWidth, frameHeight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
        }
    }

    public void updateMovement(int windowWidth, int windowHeight, Camera camera) {
        if (upPressed) {
            move(Direction.UP, windowWidth, windowHeight, camera);
        }
        if (downPressed) {
            move(Direction.DOWN, windowWidth, windowHeight, camera);
        }
        if (leftPressed) {
            move(Direction.LEFT, windowWidth, windowHeight, camera);
        }
        if (rightPressed) {
            move(Direction.RIGHT, windowWidth, windowHeight, camera);
        }
    }

    public void move(Direction direction, int windowWidth, int windowHeight, Camera camera) {
        setDirection(direction);
        int moveSpeed = speed;

        int dx = 0;
        int dy = 0;

        switch (direction) {
            case UP -> dy = -moveSpeed;
            case DOWN -> dy = moveSpeed;
            case LEFT -> dx = -moveSpeed;
            case RIGHT -> dx = moveSpeed;
        }

        playerX += dx;
        playerY += dy;

        int threshold = 20;

        int screenX = playerX - camera.x;
        int screenY = playerY - camera.y;

        int offsetX = 0;
        int offsetY = 0;

        if (screenX < threshold) {
            offsetX = screenX - threshold;
        } else if (screenX > windowWidth - frameWidth - threshold) {
            offsetX = screenX - (windowWidth - frameWidth - threshold);
        }

        if (screenY < threshold) {
            offsetY = screenY - threshold;
        } else if (screenY > windowHeight - frameHeight - threshold) {
            offsetY = screenY - (windowHeight - frameHeight - threshold);
        }

        camera.x += offsetX;
        camera.y += offsetY;
    }

    public void render(Graphics g, int cameraX, int cameraY) {
        JSONObject jsonObject = JsonHelper.read("run/player.json");
        assert jsonObject != null;
        JSONObject playerInfo = jsonObject.getJSONObject("player");
        this.speed = playerInfo.getInt("speed");
        this.health = playerInfo.getDouble("health");
        this.maxHealth = playerInfo.getDouble("maxHealth");
        this.mana = playerInfo.getDouble("mana");
        this.maxMana = playerInfo.getDouble("maxMana");
        this.stamina = playerInfo.getDouble("stamina");
        this.maxStamina = playerInfo.getDouble("maxStamina");
        BufferedImage[] currentAnimation = switch (direction) {
            case DOWN -> walkingDown;
            case LEFT -> walkingLeft;
            case RIGHT -> walkingRight;
            case UP -> walkingUp;
        };

        int screenX = playerX - cameraX;
        int screenY = playerY - cameraY;

        if (downPressed || leftPressed || rightPressed || upPressed){
            g.drawImage(currentAnimation[currentFrame], screenX, screenY, frameWidth, frameHeight, null);
        } else {
            g.drawImage(currentAnimation[4], screenX, screenY, frameWidth, frameHeight, null);
        }

        int frameDelay = 300 - speed * 15;
        if (System.currentTimeMillis() - lastFrameTime > frameDelay) {
            currentFrame = (currentFrame + 1) % currentAnimation.length;
            lastFrameTime = System.currentTimeMillis();
        }
    }

    public boolean isCloseEnough(int x, int y, int radius) {
        int dx = playerX - x;
        int dy = playerY - y;
        if (Math.abs(dx) > radius || Math.abs(dy) > radius) {
            return false;
        }
        return dx * dx + dy * dy <= radius * radius;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return playerX;
    }

    public int getY() {
        return playerY;
    }

    public void setX(int x){
        this.playerX = x;
    }

    public void setY(int y){
        this.playerY = y;
    }
}