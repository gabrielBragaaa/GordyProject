package io.github.hh_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen {
    final TelaJogo jogo;
    SpriteBatch batch;
    BitmapFont font;

    public Menu(TelaJogo jogo) {
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
        //lIMPA A TELA COM UMA COR
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Desenha o texto
        batch.begin();
        font.draw(batch, "Pressione ENTER para jogar",100,150);
        batch.end();

        //Verifica se o ENTER foi presionado
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER));{
            jogo.setScreen(new TelaJogo(jogo));
        }
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
font.dispose();
    }

}
