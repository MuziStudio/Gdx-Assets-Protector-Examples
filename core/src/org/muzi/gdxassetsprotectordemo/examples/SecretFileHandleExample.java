package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretBitmapFont;
import com.muzi.assetsprotector.SecretClass.SecretFileHandle;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretFileHandleExample extends BaseExample {
    BitmapFont font;
    SpriteBatch batch;
    String fileString;

    public SecretFileHandleExample(MainGame game) {
        super(game);
        game.manager.load("BitmapFont/font.fnt", SecretBitmapFont.class);
        game.manager.load("FileHandle/secret.txt", SecretFileHandle.class);
        game.manager.finishLoading();
        font = game.manager.get("BitmapFont/font.fnt");
        font.getData().setScale(0.3f);
        batch = new SpriteBatch();
        SecretFileHandle file = game.manager.get("FileHandle/secret.txt");
        fileString = file.readString();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        font.draw(batch, fileString, 20, 100);
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
