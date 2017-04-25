package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Personajes.Constante;
import com.mygdx.game.Personajes.EnemyEntity;
import com.mygdx.game.Personajes.FloorEntity;
import com.mygdx.game.Personajes.PlayerEntity;

import java.util.ArrayList;


/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class GameScreen extends MenuPrincipal {
    private Stage stage;
    private Image gameover;
    private World world;
    private ArrayList<FloorEntity> floorList = new ArrayList<FloorEntity>();
    private PlayerEntity barbaro;
    private ArrayList<EnemyEntity> enemyList = new ArrayList<EnemyEntity>();

    public GameScreen(MainGame game){
        super(game);
        stage = new Stage(new FitViewport(640,360));
        world = new World(new Vector2(0,-10),true);
        world.setContactListener(new ContactListener() {
            private boolean areCollided(Contact contact,Object userA, Object userB){
                return ((contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB)) ||
                        (contact.getFixtureA().getUserData().equals(userB)&& contact.getFixtureA().getUserData().equals(userA)));
            }
            @Override
            public void beginContact(Contact contact) {
                //si han chocado mi player con el suelo
                if (areCollided(contact,"player","floor")){
                    barbaro.setJumping(false) ;
                    if(Gdx.input.isTouched()){
                        barbaro.setMustJump(true);
                    }
                }
                //si as colisionado vivo
                if(areCollided(contact,"player","enemy")){
                    barbaro.setAlive(false);
                    System.out.println("GameOver");


                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("barbaro.jpg");
        Texture floorTexture = game.getManager().get("floor.jpg");
        Texture enemyTexture = game.getManager().get("Enemigo.jpg");
        Texture overfloorTexture = game.getManager().get("floor.png");


        barbaro = new PlayerEntity(world,playerTexture,new Vector2(1,1.5f));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,0,1000,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,10,4,1.7f));
        enemyList.add(new EnemyEntity(world,enemyTexture,6,0.5f));
        enemyList.add(new EnemyEntity(world,enemyTexture,20,0.5f));
        enemyList.add(new EnemyEntity(world,enemyTexture,40,0.5f));
        enemyList.add(new EnemyEntity(world,enemyTexture,80,0.5f));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,100,4,1.7f));

        stage.addActor(barbaro);
        for(FloorEntity floor : floorList){
            stage.addActor(floor);
        }
        for(EnemyEntity enemy:enemyList){
            stage.addActor(enemy);
        }
    }

    @Override
    public void hide() {
        barbaro.detach();
        barbaro.remove();
        for (FloorEntity floor : floorList){
            floor.detach();
            floor.remove();
        }
        for(EnemyEntity enemy: enemyList){
            enemy.detach();
            enemy.remove();
        }
    }

    @Override
    public void render(float delta) {
        //limpiar pantalla con el color que quiero
        Gdx.gl.glClearColor(0.4f,0.5f,1f,1f);
        //llamar el gdx para corntrolar que la tarjeta vacia.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(barbaro.getX() > 150 && barbaro.isAlive()) {
            stage.getCamera().translate(Constante.SPEED_PLAYER * delta * Constante.PIXELS_IN_METER, 0, 0);
        }
        if(!barbaro.isAlive()){

        }
        stage.act();
        world.step(delta,6,2);

        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
