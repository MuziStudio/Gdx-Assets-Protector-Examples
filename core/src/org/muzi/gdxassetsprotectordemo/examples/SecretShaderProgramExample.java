package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.muzi.assetsprotector.SecretClass.SecretShaderProgram;
import com.muzi.assetsprotector.SecretClass.SecretTexture;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretShaderProgramExample extends BaseExample {
    SpriteBatch batch;
    Texture texture;
    ShaderProgram shader;

    public SecretShaderProgramExample(MainGame game) {
        super(game);
        game.manager.load("ShaderProgram/shader.vert", SecretShaderProgram.class);
        game.manager.load("Texture/gdx.png", SecretTexture.class);
        game.manager.finishLoading();
        shader = game.manager.get("ShaderProgram/shader.vert");
        texture = game.manager.get("Texture/gdx.png");
        batch = new SpriteBatch();
        batch.setShader(shader);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        super.render(delta);
    }

    public void dispose() {
        batch.dispose();
        texture.dispose();
        shader.dispose();
    }
}
