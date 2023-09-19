package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretSound;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretSoundExample extends BaseExample {
    SecretSound sound;

    public SecretSoundExample(MainGame game) {
        super(game);
        game.manager.load("Sound/sound.wav", SecretSound.class);
        game.manager.finishLoading();
        sound = game.manager.get("Sound/sound.wav");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        if (Gdx.input.justTouched()) {
            sound.play();
        }
        super.render(delta);
    }

    @Override
    public void dispose() {
        sound.dispose();
    }
}
