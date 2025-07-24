package io.github.hh_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {

    Texture backgroundTexture;
    Texture gordyTexture;
    Texture dropTexture;
    Music music;
    private SpriteBatch batch;
    private Texture image;
    SpriteBatch spriteBatch;
    FitViewport viewport;

    @Override//Este metodod inicia imediatamente qundo o jogo inicia
    public void create() {
        batch = new SpriteBatch();//Important
        backgroundTexture = new Texture("Hamburgueria.png");
        gordyTexture = new Texture("Gordy.png");
        dropTexture = new Texture("Hambuguer.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("Ziv Moran - Patisserie.mp3"));

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(backgroundTexture, -20, -20);
        batch.end();

        input();
        logic();
        draw();
    }

    private void input() {

    }

    private void logic() {

    }
    private void draw() {
        ScreenUtils.clear(Color.BLACK);//Limpa a tela
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float worldwidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(gordyTexture,0,0,1,1);// draw gordy
        spriteBatch.draw(backgroundTexture,0,0,worldwidth,worldHeight); //draw background

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); //true centers the camera
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
    }
}
