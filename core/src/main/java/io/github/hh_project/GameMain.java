package io.github.hh_project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends Game {
    public SpriteBatch batch;

    @Override
    public void create(){
        batch = new SpriteBatch();
        setScreen(new Menu(this));
    }
}
