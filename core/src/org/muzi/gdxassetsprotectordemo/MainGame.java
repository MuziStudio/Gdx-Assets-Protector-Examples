package org.muzi.gdxassetsprotectordemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.muzi.assetsprotector.SecretAssetManager;
import org.muzi.gdxassetsprotectordemo.examples.*;

public class MainGame extends Game {
    // 使用 SecretAssetManager 代替 AssetManager
    public SecretAssetManager manager;
    MainScreen mainScreen;
    Screen secretBitmapFontExample, secretFileHandleExample, secretI18NBundleExample, secretMusicExample,
            secretParticleEffectExample, secretPixmapExample, secretPolygonRegionExample, secretShaderProgramExample,
            secretSkinExample, secretSoundExample, secretTextureExample, secretTextureAtlasExample, secretTideMapExample,
            secretTmxMapExample;

    @Override
    public void create() {
        manager = new SecretAssetManager();
        // 加载 Internal 路径下的 data.msf 文件，该文件由加密工具打包生成，包含加密后的资源文件
        // 第二个参数是加密时使用的字符串密码，另可选择提供 byte[] 密钥数组或 FileHandle 密钥文件
        manager.loadPackage(Gdx.files.internal("data.msf"), "muzistudio");
        mainScreen = new MainScreen(this);
        setScreen(mainScreen);
        Gdx.input.setCatchKey(Keys.BACK, true);
    }

    public void switchExample(String example) {
        switch (example) {
            case "SecretBitmapFont":
                if (secretBitmapFontExample == null) {
                    secretBitmapFontExample = new SecretBitmapFontExample(this);
                }
                setScreen(secretBitmapFontExample);
                break;
            case "SecretFileHandle":
                if (secretFileHandleExample == null) {
                    secretFileHandleExample = new SecretFileHandleExample(this);
                }
                setScreen(secretFileHandleExample);
                break;
            case "SecretI18NBundle":
                if (secretI18NBundleExample == null) {
                    secretI18NBundleExample = new SecretI18NBundleExample(this);
                }
                setScreen(secretI18NBundleExample);
                break;
            case "SecretMusic":
                if (secretMusicExample == null) {
                    secretMusicExample = new SecretMusicExample(this);
                }
                setScreen(secretMusicExample);
                break;
            case "SecretParticleEffect":
                if (secretParticleEffectExample == null) {
                    secretParticleEffectExample = new SecretParticleEffectExample(this);
                }
                setScreen(secretParticleEffectExample);
                break;
            case "SecretPixmap":
                if (secretPixmapExample == null) {
                    secretPixmapExample = new SecretPixmapExample(this);
                }
                setScreen(secretPixmapExample);
                break;
            case "SecretPolygonRegion":
                if (secretPolygonRegionExample == null) {
                    secretPolygonRegionExample = new SecretPolygonRegionExample(this);
                }
                setScreen(secretPolygonRegionExample);
                break;
            case "SecretShaderProgram":
                if (secretShaderProgramExample == null) {
                    secretShaderProgramExample = new SecretShaderProgramExample(this);
                }
                setScreen(secretShaderProgramExample);
                break;
            case "SecretSkin":
                if (secretSkinExample == null) {
                    secretSkinExample = new SecretSkinExample(this);
                }
                setScreen(secretSkinExample);
                break;
            case "SecretSound":
                if (secretSoundExample == null) {
                    secretSoundExample = new SecretSoundExample(this);
                }
                setScreen(secretSoundExample);
                break;
            case "SecretTexture":
                if (secretTextureExample == null) {
                    secretTextureExample = new SecretTextureExample(this);
                }
                setScreen(secretTextureExample);
                break;
            case "SecretTextureAtlas":
                if (secretTextureAtlasExample == null) {
                    secretTextureAtlasExample = new SecretTextureAtlasExample(this);
                }
                setScreen(secretTextureAtlasExample);
                break;
            case "SecretTiledMap(*.tide)":
                if (secretTideMapExample == null) {
                    secretTideMapExample = new SecretTideMapExample(this);
                }
                setScreen(secretTideMapExample);
                break;
            case "SecretTiledMap(*.tmx)":
                if (secretTmxMapExample == null) {
                    secretTmxMapExample = new SecretTmxMapExample(this);
                }
                setScreen(secretTmxMapExample);
                break;
        }
    }

    public void switchMainScreen() {
        setScreen(mainScreen);
    }
}
