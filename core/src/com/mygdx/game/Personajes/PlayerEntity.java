package com.mygdx.game.Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class PlayerEntity extends Actor {
    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean alive= true;
    private boolean jumping = true;

    public boolean isMustJump() {
        return mustJump;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    private boolean mustJump = true;
    public PlayerEntity(World world,Texture texture,Vector2 position){
        this.world =world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape barbaroshape =  new PolygonShape();
        barbaroshape.setAsBox(0.5f,0.5f);
        fixture = body.createFixture(barbaroshape,1);
        fixture.setUserData("player");
        barbaroshape.dispose();
            setSize(Constante.PIXELS_IN_METER,Constante.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x-0.5f)*Constante.PIXELS_IN_METER
                ,(body.getPosition().y-0.5f)*Constante.PIXELS_IN_METER);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta) {
        //si emos tocado pantalla
        if(Gdx.input.isTouched() || mustJump){
            mustJump = false;
            jump();
        }

        //si esta vivo que avanze
        if(alive){
            float velocityspeedY = body.getLinearVelocity().y;
            body.setLinearVelocity(Constante.SPEED_PLAYER,velocityspeedY);
        }
        if (jumping){
            body.applyForceToCenter(0,-4*1.25f,true);
        }
    }
    public void jump(){
        if(!jumping && alive) {
            jumping = true;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, 8, position.x, position.y, true);
        }
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}

