package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretTexture;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretTextureExample extends BaseExample {
    SpriteBatch batch;
    Texture texture;

    public SecretTextureExample(MainGame game) {
        super(game);
        batch = new SpriteBatch();
        game.manager.load("Texture/gdx.png", SecretTexture.class);
        game.manager.finishLoading();
        texture = game.manager.get("Texture/gdx.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
