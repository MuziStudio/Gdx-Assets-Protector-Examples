package org.muzi.gdxassetsprotectordemo.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.muzi.assetsprotector.SecretClass.SecretSkin;
import org.muzi.gdxassetsprotectordemo.MainGame;

public class SecretSkinExample extends BaseExample {
    Stage stage;
    Skin skin;

    public SecretSkinExample(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(800, 480));
        game.manager.load("Skin/cloud-form-ui.json", SecretSkin.class);
        game.manager.finishLoading();
        skin = game.manager.get("Skin/cloud-form-ui.json");
        final TextButton button = new TextButton("On", skin);
        button.setPosition(100, 100);
        button.setTransform(true);
        button.setSize(200, 100);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.setText(button.getText().toString().equals("Off") ? "On" : "Off");
                super.clicked(event, x, y);
            }
        });
        stage.addActor(button);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        stage.act();
        stage.draw();
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
