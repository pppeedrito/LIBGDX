package com.mygdx.game.PRUEVA1;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class Enemigo extends Actor {
private TextureRegion enemigo;
    public Enemigo(TextureRegion enemigo){
        this.enemigo = enemigo;
        setSize(enemigo.getRegionWidth(),enemigo.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        setX(getX()-250*delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(enemigo,getX(),getY(),120,120);
    }
}
