package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretBitmapFont;
import com.muzi.assetsprotector.SecretLoader.SecretBitmapFontLoader.SecretBitmapFontParameter;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretBitmapFontExample extends BaseExample {
    BitmapFont font;
    SpriteBatch batch;

    public SecretBitmapFontExample(MainGame game) {
        super(game);
        SecretBitmapFontParameter parameter = new SecretBitmapFontParameter();
        parameter.fontScale = 2f;
        game.manager.load("BitmapFont/font.fnt", SecretBitmapFont.class, parameter);
        game.manager.finishLoading();
        font = game.manager.get("BitmapFont/font.fnt");
        font.getData().setScale(0.3f);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        font.draw(batch, "I am a secret BitmapFont", 20, 100);
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
