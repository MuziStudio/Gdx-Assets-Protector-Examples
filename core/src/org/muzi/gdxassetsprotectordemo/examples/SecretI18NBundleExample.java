package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretBitmapFont;
import com.muzi.assetsprotector.SecretClass.SecretI18NBundle;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretI18NBundleExample extends BaseExample {
    BitmapFont font;
    SpriteBatch batch;
    SecretI18NBundle bundle;
    String i18NString;

    public SecretI18NBundleExample(MainGame game) {
        super(game);
        game.manager.load("BitmapFont/font.fnt", SecretBitmapFont.class);
        // 可以为 load 方法传递参数
        // Locale locale = new Locale("en");
        // I18NBundleParameter parameter = new I18NBundleParameter(locale);
        game.manager.load("I18NBundle/bundle", SecretI18NBundle.class);
        game.manager.finishLoading();
        font = game.manager.get("BitmapFont/font.fnt");
        font.getData().setScale(0.3f);
        batch = new SpriteBatch();
        bundle = game.manager.get("I18NBundle/bundle");
        i18NString = bundle.get("game");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        font.draw(batch, "Your language is " + i18NString, 20, 100);
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
