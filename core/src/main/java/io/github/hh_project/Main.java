package io.github.hh_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Vector;

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
    Sprite gordySprite;
    Vector2 touchPos;

    @Override//Este metodod inicia imediatamente qundo o jogo inicia
    public void create() {
        batch = new SpriteBatch();//Important
        backgroundTexture = new Texture("Hamburgueria.png");
        gordyTexture = new Texture("Gordy.png");
        dropTexture = new Texture("Hambuguer.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("Ziv Moran - Patisserie.mp3"));

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        gordySprite = new Sprite(gordyTexture);
        gordySprite.setSize(1, 1);

        touchPos = new Vector2();
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

    //Entrada de dados
    private void input() {
        float speed = .90f;//Termina a velocidade
        float delta = Gdx.graphics.getDeltaTime();// Retrive the current Delta

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gordySprite.translateX(speed * delta); //Move the Gordy right
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gordySprite.translateX(-speed * delta);
        }

        if (Gdx.input.isTouched()) { //If the user has clicked or tapped the screen
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            gordySprite.setCenterX(touchPos.x);
        }
    }

    //Logica do jogo
    private void logic() {

    float worldWidth = viewport.getWorldWidth();
    float worlHeight = viewport.getWorldHeight();

    float gordyWidth = gordySprite.getWidth();
    float gordyHeight = gordySprite.getHeight();

    gordySprite.setX(MathUtils.clamp(gordySprite.getX(),0,worldWidth - gordyWidth));
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);//Limpa a tela
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float worldwidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(backgroundTexture, 0, 0, worldwidth, worldHeight); //draw background / e faz com que a imagem fique toda preenchida
        gordySprite.draw(spriteBatch);
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
