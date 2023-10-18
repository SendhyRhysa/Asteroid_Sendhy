import greenfoot.*;

public class Rocket extends SmoothMover {
    private static final int gunReloadTime = 5;
    private static final int protonReloadTime = 500;
    private int reloadDelayCount;
    private int protonDelayCount;
    private GreenfootImage rocket = new GreenfootImage("ROCKET.png");

    public Rocket() {
        reloadDelayCount = 5;
        protonDelayCount = 500;
        addToVelocity(new Vector(13, 0.7));
    }

    public void act() {
        move();
        checkMouse();
        checkCollision();
        checkKeys();  // Menambahkan pemrosesan tombol keyboard
        reloadDelayCount++;
        protonDelayCount++;
    }

    private void checkMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            setLocation(mouseX, mouseY);
        }
    }

    private void checkCollision() {
        Asteroid a = (Asteroid) getOneIntersectingObject(Asteroid.class);
        if (a != null) {
            Space space = (Space) getWorld();
            space.addObject(new Explosion(), getX(), getY());
            space.removeObject(this);
            space.gameOver();
        }
    }

    private void checkKeys() {
        if (Greenfoot.isKeyDown("space")) {
            fire();
        }
    }

    private void fire() {
        if (reloadDelayCount >= gunReloadTime) {
            Bullet bullet = new Bullet(getVelocity(), getRotation());
            getWorld().addObject(bullet, getX(), getY());
            bullet.move();
            reloadDelayCount = 0;
        }
    }
}
