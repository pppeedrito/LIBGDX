package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {
	private AssetManager manager;
	public AssetManager getManager(){
		return manager;

	}
	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("floor.png", Texture.class);
		manager.load("Enemigo.jpg",Texture.class);
		manager.load("barbaro.jpg",Texture.class);
		manager.load("gameOver.jpg",Texture.class);
		manager.load("floor.jpg",Texture.class);
		manager.finishLoading();
		setScreen(new GameScreen(this));
	}


	/*private Texture Barbaro;
	private Texture Enemigo;
	private SpriteBatch batch;

	@Override
	public void create() {
		Barbaro = new Texture("barbaro.jpg");
		Enemigo = new Texture("Enemigo.jpg");
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		//metodo que se ejecuta el ultimo para liberar memoria.
		//en este caso liberamos la imagen u el sprite
		Barbaro.dispose();
		batch.dispose();
	}

	@Override
	public void render() {
		//limpiar pantalla con el color que quiero
		Gdx.gl.glClearColor(0, 1, 0, 1);
		//llamar el gdx para corntrolar que la tarjeta vacia.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//render mostrar por pantalla. batck para dibujar
		batch.begin();
		batch.draw(Barbaro, 0, 0, 120, 120);
		batch.draw(Enemigo,100,100,120,120);
		batch.end();

		if (Gdx.input.justTouched()) {
			System.out.println("TOCANDO LA PANTALLA");
		}
	}*/

}
