package com.mygdx.game.PRUEVA1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MainGame;
import com.mygdx.game.MenuPrincipal;

/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class Box2DScreen extends MenuPrincipal {
    public Box2DScreen(MainGame game){
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body barbaroBody, sueloBody, enemigoBody;
    private Fixture barbaroFixture,sueloFixture,enemigoFixture;
    private boolean debesaltar,barbarosaltando,barbarovivo = true;
    @Override
    public void show() {
        world = new World(new Vector2(0,-10),true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(16,9);
        camera.translate(0,1);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor"))||
                        (fixtureA.getUserData().equals("floor")&& fixtureB.getUserData().equals("player"))){
                    if(Gdx.input.isTouched()){
                        debesaltar = true;
                    }
                    barbarosaltando = false;
                }

                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("enemy"))||
                        (fixtureA.getUserData().equals("enemy")&& fixtureB.getUserData().equals("barbaro"))){

                    barbarovivo = false;
                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if(fixtureA == barbaroFixture && fixtureB == sueloFixture){
                    barbarosaltando = true;

                }
                if(fixtureA == sueloFixture && fixtureB == barbaroFixture){
                    barbarosaltando = true;

                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        barbaroBody = world.createBody(createbodydefbarbaro());
        sueloBody = world.createBody(createbodydefsuelo());
        enemigoBody = world.createBody(createbodydefenemigo(5));



        PolygonShape barbaroshape =  new PolygonShape();
        barbaroshape.setAsBox(0.5f,0.5f);
        barbaroFixture = barbaroBody.createFixture(barbaroshape,1);
        barbaroshape.dispose();

        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500,1);
        sueloFixture = sueloBody.createFixture(sueloShape,1);
        sueloShape.dispose();

        enemigoFixture = createbodydefenemigo(enemigoBody);

        barbaroFixture.setUserData("player");
        sueloFixture.setUserData("floor");
        enemigoFixture.setUserData("enemy");
    }
    private BodyDef createbodydefsuelo(){
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        return def;
    }
    private BodyDef createbodydefenemigo(float x){
        BodyDef def = new BodyDef();
        def.position.set(x,0.5f);
        return def;
    }
private BodyDef createbodydefbarbaro(){
    BodyDef def = new BodyDef();
    def.position.set(-7.2f,0.5f);
    def.type = BodyDef.BodyType.DynamicBody;
    return def;
}
private Fixture createbodydefenemigo(Body enemigoBody){
    Vector2[] vertices = new Vector2[3];
    vertices[0] = new Vector2(-0.5f,-0.5f);
    vertices[1] = new Vector2(0.5f,-0.5f);
    vertices[2] = new Vector2(0,0.5f);

    PolygonShape shape = new PolygonShape();
    shape.set(vertices);
    Fixture fix = enemigoBody.createFixture(shape, 1 );
    shape.dispose();
    return fix;
}
    @Override
    public void dispose() {
        enemigoBody.destroyFixture(enemigoFixture);
        sueloBody.destroyFixture(sueloFixture);
        barbaroBody.destroyFixture(barbaroFixture);
        world.destroyBody(barbaroBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        //limpiar pantalla con el color que quiero
        Gdx.gl.glClearColor(0.4f,0.5f,1f,1f);
        //llamar el gdx para corntrolar que la tarjeta vacia.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(debesaltar){
            debesaltar = false;
            saltar();
        }
        if(Gdx.input.justTouched()&& !barbarosaltando){
            debesaltar = true;
        }
        if(barbarovivo) {
            float velocityY = barbaroBody.getLinearVelocity().y;
            barbaroBody.setLinearVelocity(8, velocityY);
        }

        world.step(delta,6,2);


        camera.update();
        renderer.render(world,camera.combined);
    }
    private void saltar(){

        Vector2 position = barbaroBody.getPosition();
        barbaroBody.applyLinearImpulse(0,6,position.x,position.y,true);
    }
}
