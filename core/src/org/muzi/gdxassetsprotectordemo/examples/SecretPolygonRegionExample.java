package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretPolygonRegion;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretPolygonRegionExample extends BaseExample {
    PolygonSpriteBatch batch;
    PolygonRegion region;

    public SecretPolygonRegionExample(MainGame game) {
        super(game);
        game.manager.load("PolygonRegion/gdx.psh", SecretPolygonRegion.class);
        game.manager.finishLoading();
        region = game.manager.get("PolygonRegion/gdx.psh");
        batch = new PolygonSpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(region, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
