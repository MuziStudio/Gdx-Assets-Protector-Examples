package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretTiledMap;
import com.muzi.assetsprotector.SecretLoader.SecretTmxMapLoader;
import org.muzi.gdxassetsprotectordemo.MainGame;

// 由于Api修改，此类在旧版本报错，请使用 libGDX 1.12.0 版本
public class SecretTmxMapExample extends BaseExample {
    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;

    public SecretTmxMapExample(MainGame game) {
        super(game);
        game.manager.setLoader(SecretTiledMap.class, new SecretTmxMapLoader());
        game.manager.load("TmxMap/map.tmx", SecretTiledMap.class);
        game.manager.finishLoading();
        map = game.manager.get("TmxMap/map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 2f);
        mapRenderer.setView(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()),
                0, 0, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        mapRenderer.render();
        super.render(delta);
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }
}
