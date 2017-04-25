package com.mygdx.game.PRUEVA1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class Barbaro extends Actor {
    private Texture barbagris;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private boolean alive;
    public Barbaro(Texture barbagris){
        this.alive = true;
        this.barbagris = barbagris;
        setSize(barbagris.getWidth(),barbagris.getHeight());
    }
    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(barbagris,getX(),getY(),120,120);
    }
}
