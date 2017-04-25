package com.mygdx.game.Personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class EnemyEntity extends Actor {
    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;

    public EnemyEntity(World world, Texture texture, float x, float y){
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(x,y+0.5f);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f,0.5f);
        fixture = body.createFixture(box,1);
        fixture.setUserData("enemy");
        box.dispose();
        setSize(Constante.PIXELS_IN_METER,Constante.PIXELS_IN_METER);
        setPosition((body.getPosition().x-0.5f)*Constante.PIXELS_IN_METER
                ,(body.getPosition().y)*Constante.PIXELS_IN_METER);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }
    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
