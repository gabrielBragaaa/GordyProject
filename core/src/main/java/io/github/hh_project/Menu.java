package io.github.hh_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen {
    final Main jogo;
    SpriteBatch batch;
    BitmapFont font;

    public Menu(Main jogo) {
        this.jogo = jogo;
        this.batch = jogo.batch;
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font.getData().setScale(2);//Tamanho fonte
    }

    @Override
    public void show() {
        System.out.println("Tela de Menu");

    }

    @Override
    public void render(float delta) {
        
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
