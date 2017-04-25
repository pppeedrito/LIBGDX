package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.PRUEVA1.Barbaro;
import com.mygdx.game.PRUEVA1.Enemigo;

/**
 * Created by Sr_Pedro on 31/03/2017.
 */

public class MaingGameScreen extends MenuPrincipal {


    public MaingGameScreen(MainGame game) {
        super(game);
         texturaBarbaro = new Texture("barbaro.jpg");
        texturaenemigo = new Texture("Enemigo.jpg");
        regionenemigo = new TextureRegion(texturaenemigo,0,64,128,64);
    }
    private Stage stage;
    private Barbaro jugador;
    private Enemigo enemigo;
    private Texture texturaBarbaro,texturaenemigo;
    private TextureRegion regionenemigo;

    @Override
    public void show() {
        stage = new Stage();
        stage.setDebugAll(true);

        jugador = new Barbaro(texturaBarbaro);
        enemigo = new Enemigo(regionenemigo);
        stage.addActor(jugador);
        stage.addActor(enemigo);
        jugador.setPosition(20,100);
        enemigo.setPosition(400,100);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        //limpiar pantalla con el color que quiero
        Gdx.gl.glClearColor(0.4f,0.5f,1f,1f);
        //llamar el gdx para corntrolar que la tarjeta vacia.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        comprobarColisiones();
        stage.draw();
    }
    private void comprobarColisiones(){
        if(jugador.isAlive() && jugador.getX() + jugador.getY()>enemigo.getX()){
            System.out.println("Colision");
            jugador.setAlive(false);
        }
    }

    @Override
    public void dispose() {
        texturaBarbaro.dispose();
    }
}
