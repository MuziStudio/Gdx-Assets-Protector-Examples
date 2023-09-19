package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretMusic;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretMusicExample extends BaseExample {
    SecretMusic music;

    public SecretMusicExample(MainGame game) {
        super(game);
        game.manager.load("Music/music.wav", SecretMusic.class);
        game.manager.finishLoading();
        music = game.manager.get("Music/music.wav");
    }

    @Override
    public void show() {
        music.play();
        super.show();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        super.render(delta);
    }

    @Override
    public void hide() {
        music.stop();
        super.hide();
    }

    @Override
    public void dispose() {
        music.dispose();
    }
}
