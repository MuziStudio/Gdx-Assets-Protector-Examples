package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretTextureAtlas;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretTextureAtlasExample extends BaseExample {
    SpriteBatch batch;
    TextureAtlas atlas;
    Array<AtlasRegion> regions;
    Array<Animation<TextureRegion>> animations;
    float time = 0f;

    public SecretTextureAtlasExample(MainGame game) {
        super(game);
        batch = new SpriteBatch();
        game.manager.load("TextureAtlas/face.atlas", SecretTextureAtlas.class);
        game.manager.finishLoading();
        atlas = game.manager.get("TextureAtlas/face.atlas");
        regions = atlas.getRegions();
        animations = new Array<>();
        for (AtlasRegion region : regions) {
            TextureRegion[][] frames = region.split(32, 32);
            Animation<TextureRegion> animation = new Animation<>(0.5f, frames[0]);
            animation.setPlayMode(PlayMode.LOOP_PINGPONG);
            animations.add(animation);
        }
    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        for (int i = 0; i < animations.size; i++) {
            batch.draw(animations.get(i).getKeyFrame(time), 20 + 66 * i, 100, 64, 64);
        }
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
        regions.clear();
        animations.clear();
    }
}
