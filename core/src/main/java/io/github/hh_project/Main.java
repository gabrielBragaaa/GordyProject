package io.github.hh_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Vector;

public class Main extends Game {
    Array<Sprite> dropSprites;
    Texture backgroundTexture;
    Texture gordyTexture;
    Texture dropTexture;

    Music music;
    Music eatMusic;

    private SpriteBatch batch;
    private Texture image;

    SpriteBatch spriteBatch;
    FitViewport viewport;
    Sprite gordySprite;
    Vector2 touchPos;

    float dropTimer;

    Rectangle gordyRectangle;
    Rectangle dropRectangle;

    @Override//Este metodod inicia imediatamente qundo o jogo inicia
    public void create() {
        setScreen(new Menu(this));
        batch = new SpriteBatch();//Important
        backgroundTexture = new Texture("Hamburgueria.png");
        gordyTexture = new Texture("Gordy.png");
        dropTexture = new Texture("Hambuguer.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("Ziv Moran - Patisserie.mp3"));
        eatMusic = Gdx.audio.newMusic(Gdx.files.internal("eat.mp3"));

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        gordySprite = new Sprite(gordyTexture);
        gordySprite.setSize(1, 1);

        touchPos = new Vector2();

        dropSprites = new Array<>();

        gordyRectangle = new Rectangle();
        dropRectangle = new Rectangle();

        //Trilha sonora
        music.setLooping(true);
        music.setVolume(.5f);
        music.play();

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

        gordySprite.setX(MathUtils.clamp(gordySprite.getX(), 0, worldWidth - gordyWidth));

        float delta = Gdx.graphics.getDeltaTime();

        gordyRectangle.set(gordySprite.getX(), gordySprite.getY(), gordyWidth, gordyHeight);

        // Loop through the sprites backwards to prevent out of bounds errors
        for (int i = dropSprites.size - 1; i >= 0; i--) {
            Sprite dropSprite = dropSprites.get(i);
            float dropWidth = dropSprite.getWidth();
            float dropHeight = dropSprite.getHeight();

            dropSprite.translateY(-2f * delta);

            dropRectangle.set(dropSprite.getX(), dropSprite.getY(), dropWidth, dropHeight);

            if (dropSprite.getY() < -dropHeight) dropSprites.removeIndex(i);
            else if (gordyRectangle.overlaps((dropRectangle))) { // Check if gordy overlaps the drop
                dropSprites.removeIndex(i); //Remove the drop
                eatMusic.play();//Som de comida
            }
        }

        dropTimer += delta;
        if (dropTimer > 1f) {
            dropTimer = -1;//Tempo entre um hamburguer e outro
            createDroplet();
        }

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

        for (Sprite dropSprite : dropSprites) {
            dropSprite.draw(spriteBatch);
        }

        spriteBatch.end();
    }

    private void createDroplet() {
        float dropWidth = 1;
        float dropHeight = 1;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        Sprite dropSprite = new Sprite(dropTexture);
        dropSprite.setSize(dropWidth, dropHeight);
        dropSprite.setX(MathUtils.random(0f, worldWidth - dropHeight));
        dropSprite.setY(worldHeight);
        dropSprites.add(dropSprite);
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
