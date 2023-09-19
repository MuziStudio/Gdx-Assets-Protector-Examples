package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretParticleEffect;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretParticleEffectExample extends BaseExample {
    SpriteBatch batch;
    ParticleEffect effect;

    public SecretParticleEffectExample(MainGame game) {
        super(game);
        game.manager.load("ParticleEffect/fire.p", SecretParticleEffect.class);
        game.manager.finishLoading();
        batch = new SpriteBatch();
        effect = game.manager.get("ParticleEffect/fire.p");
        effect.setPosition(100, 100);
        effect.setDuration(10000);
    }

    @Override
    public void show() {
        effect.start();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        effect.draw(batch, delta);
        batch.end();
        super.render(delta);
    }

    @Override
    public void dispose() {
        batch.dispose();
        effect.dispose();
    }
}
