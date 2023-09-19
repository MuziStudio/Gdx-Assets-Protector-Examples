package org.muzi.gdxassetsprotectordemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.muzi.assetsprotector.SecretClass.SecretSkin;

public class MainScreen implements Screen {
    Stage stage;
    Skin skin;
    MainGame game;

    public MainScreen(final MainGame game) {
        this.game = game;
        stage = new Stage(new FitViewport(800, 480));
        // 加载资源文件，注意第二个参数需要对应的拥有Secret前缀的类
        game.manager.load("Skin/cloud-form-ui.json", SecretSkin.class);
        game.manager.finishLoading();
        skin = game.manager.get("Skin/cloud-form-ui.json");
        final List<String> list = new List<>(skin);
        list.getStyle().font.getData().setScale(2f);
        list.getStyle().font.getData().padLeft = -10f;
        ScrollPane scrollPane = new ScrollPane(list, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setBounds(0, 0, 800, 420);
        list.setItems("SecretBitmapFont", "SecretFileHandle", "SecretI18NBundle", "SecretMusic", "SecretParticleEffect", "SecretPixmap", "SecretPolygonRegion", "SecretShaderProgram", "SecretSkin", "SecretSound", "SecretTexture", "SecretTextureAtlas", "SecretTiledMap(*.tide)", "SecretTiledMap(*.tmx)");
        list.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String i = list.getItemAt(y);
                if (i != null) {
                    game.switchExample(i);
                }
                super.clicked(event, x, y);
            }
        });
        Label label = new Label("Select example: ", skin);
        label.setFontScale(2.2f);
        label.setBounds(0, 420, 800, 60);
        stage.addActor(scrollPane);
        stage.addActor(label);
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
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

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
