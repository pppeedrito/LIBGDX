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

public class FloorEntity extends Actor {
    private Texture floor,overfloor;
    private World world;
    private Body body, leftbody;
    private Fixture fixture,leftFixture;

    public FloorEntity(World world,Texture floor,Texture overfloor, float x,float width,float y){
        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        //Colocar el suelo donde corresponde
        BodyDef def = new BodyDef();
        def.position.set(x+width / 2, y-0.5f);
        body = world.createBody(def);

        //Le doy forma la caja

        PolygonShape box = new PolygonShape();
        box.setAsBox(width/2,0.5f);
        fixture = body.createFixture(box,1);
        fixture.setUserData("floor");
        box.dispose();

        BodyDef leftdef = new BodyDef();
        leftdef.position.set(x, y-0.55f);
        leftbody = world.createBody(leftdef);
        PolygonShape leftbox = new PolygonShape();
        leftbox.setAsBox(0.01f,0.45f);
        leftFixture = leftbody.createFixture(leftbox,1);
        leftFixture.setUserData("enemy");
        leftbox.dispose();

        setSize(width*Constante.PIXELS_IN_METER,Constante.PIXELS_IN_METER);
        setPosition(x*Constante.PIXELS_IN_METER
                ,(y-1)*Constante.PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
batch.draw(floor,getX(),getY(),getWidth(),getHeight());
        batch.draw(overfloor,getX(),getY(),getWidth(),getHeight());
    }
    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
