package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretPixmap;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretPixmapExample extends BaseExample {
    SpriteBatch batch;
    Pixmap pixmap;
    Texture texture;

    public SecretPixmapExample(MainGame game) {
        super(game);
        batch = new SpriteBatch();
        game.manager.load("Pixmap/gdx.png", SecretPixmap.class);
        game.manager.finishLoading();
        pixmap = game.manager.get("Pixmap/gdx.png");
        pixmap.setColor(Color.RED);
        pixmap.fillCircle(1024 / 2, 1024 / 2, 400);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(texture, 100, 100, 400, 400);
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
