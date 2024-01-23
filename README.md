# Gdx-Assets-Protector-Examples

### A resource protection tool designed specifically for the libGDX engine.

![](README-Res/libGDX-Protector.png)

[Chinese Documentation](README-zh.md)

---

### Feature Highlights:

- **Protect your libGDX game resources with minimal code modification.**

- **Organize encrypted resources in package format, supporting the simultaneous loading of multiple encrypted packages.**

- **Decryption entirely in memory, minimizing sensitive content exposure to the disk.**

- **Usage similar to libGDX AssetManager, resulting in a very low learning curve.**

- **Decrypt only the necessary resources when needed, minimizing memory footprint for effective protection.**

- **Optimized performance with time-consuming tasks implemented in native code, such as C++.**

- **Support for both Windows and Android platforms.**

- **Encryption based on the ChaCha20-Poly1305 algorithm, offering excellent performance on mobile devices.**

---

### Download

[Resource Encryption Tool (Windows)](README-Res/AssetsProtectorGdx.exe)

[Runtime Library (Includes Windows Native Libraries)](README-Res/gdx-protector.jar)

[Runtime Library (Excludes Windows Native Libraries)](README-Res/gdx-protector-android.jar)

[Android Native Library](README-Res/android-native-libs.zip)

---

### Brief Tutorial

###### Before you begin, make sure you have downloaded the [Resource Encryption Tool (Windows)](README-Res/AssetsProtectorGdx.exe). If you need cross-platform compilation, download the [Runtime Library (Includes Windows Native Libraries)](README-Res/gdx-protector.jar), which includes the necessary dll libraries for Windows projects. If you are compiling only an Android project, download the [Runtime Library (Excludes Windows Native Libraries)](README-Res/gdx-protector-android.jar) to reduce the size of the generated apk file. If you are compiling an Android project, also download the [Android Native Library](README-Res/android-native-libs.zip).

##### 1. Use the Resource Encryption Tool to encrypt resource files

1. Place the Resource Encryption Tool in the same directory as the folder containing all the resources you want to encrypt, and run the tool.
   
   1. In the *Please input your assets path* step, enter your resource path, using a relative path (in this example, it's "assets"). Using an absolute path may result in errors.
   
   2. In the *Use password or random key to crypto assets?* step, press 0 or 1 to choose the encryption method. 0 represents the random key method, and 1 represents the password method. The password method internally calculates the key through hashing. I recommend using the key method for increased security. If you choose the password method, enter your password in the next step.
   
   3. In the *Please input the file path of the encrypted package* step, use a relative path to input the location to save the encrypted file, for example, "secretPackage/package.bin". This will save the encrypted file to the "secretPackage" directory in the current folder with the name "package.bin". Press Enter, and you should see your encrypted file and two additional files with the same name in that directory.

**Additional File Explanation:**

- **.key**: A 32-byte key file used for decryption when using the key method.

- **.log**: An encryption log file containing the key, password (if using the password method), and the paths of all resource files in the encrypted package. This file must not be leaked under any circumstances.

**Note: You can decide the file extension for the encrypted file, but please avoid using .key or .log, as it will result in failure to generate the additional files.**

##### 2. Configure Runtime Library (Gradle Environment)

- Only Windows
  
  Download [Runtime Library (Includes Windows Native Libraries)](README-Res/gdx-protector.jar), create a new 'libs' directory under the 'core' directory, place the runtime library in that directory, add the following statement in 'core/build.gradle', and refresh the Gradle project. This will make the 'core' module reference all the libraries under the 'core/libs' directory.
  
  ```groovy
  dependencies {
      api fileTree(include: ['*.jar'], dir: 'libs')
  }
  ```
  
  Finished, that's all.

- Only Android
  
  Download [Runtime Library (Excludes Windows Native Libraries)](README-Res/gdx-protector-android.jar), create a new 'libs' directory under the 'core' directory, place the runtime library in that directory, add the following statement in 'core/build.gradle', and refresh the Gradle project. This will make the 'core' module reference all the libraries under the 'core/libs' directory.
  
  ```groovy
  dependencies {
      api fileTree(include: ['*.jar'], dir: 'libs')
  }
  ```
  
  Download [Android Native Library](README-Res/android-native-libs.zip) and extract the files from the 'android-native-libs' directory to the 'android/libs' directory.

- Cross-platform Compilation
  
  Simply replace the runtime library in the Android configuration with [Runtime Library (Includes Windows Native Libraries)](README-Res/gdx-protector.jar).

##### 3. Example Code

The core class of the runtime library is `SecretAssetManager`, and its usage is similar to `AssetManager`. However, it has the capability to load resources from encrypted packages.

```java
SecretAssetManager manager = new SecretAssetManager();
```

`loadPackage` is a unique method of `SecretManager`, allowing the loading of encrypted packages.

```java
// Load the data.msf package with the password "muzistudio"
manager.loadPackage(Gdx.files.internal("data.msf"), "muzistudio");

// Load the data.msf package with the key from the data.key file
manager.loadPackage(Gdx.files.internal("data.msf"), Gdx.files.internal("data.key"));

// Load the data.msf package with the provided byte array as the key (32 bytes)
manager.loadPackage(Gdx.files.internal("data.msf"), new byte[]{...});
```

Load resources from an encrypted package. The resource path can be referenced from the *.log file generated by the Resource Encryption Tool. `SecretAssetManager` also supports loading files from regular paths; just remove the "Secret-" prefix from the class.

```java
// Load a resource from the encrypted package, with the type SecretTexture.class
manager.load("Texture/gdx.png", SecretTexture.class);

// Load a resource from a regular path, with the type Texture.class
// manager.load("Texture/gdx.png", Texture.class);

// Wait for the resources to finish loading
manager.finishLoading();

// Get the resource; since SecretTexture extends Texture, they can be automatically converted, 
// but note that some classes might not support this conversion, please refer to the documentation
Texture texture = manager.get("Texture/gdx.png");
```

Now you can use the `Texture` class loaded from the encrypted package.

In comparison to the original `AssetManager`, `SecretAssetManager` supports loading `SecretFileHandle`. With this class, you can load any encrypted file. It is similar to `FileHandle`, but it should never be used as a replacement for `FileHandle` in other Gdx classes. This is because `SecretFileHandle` is a simulated representation of a file in memory, and the actual file does not exist on the disk.

```java
// Load a SecretFileHandle
manager.load("FileHandle/secret.txt", SecretFileHandle.class);

// Wait for the resource to finish loading
manager.finishLoading();

// Get the SecretFileHandle object
SecretFileHandle file = manager.get("FileHandle/secret.txt");

// Print the content of the obtained file
System.out.println(file.readString());
```

Loading resources with parameters, using the same method as the original AssetManager.

```java
// SecretBitmapFont parameters
SecretBitmapFontParameter parameter = new SecretBitmapFontParameter();
// Set the font scale to 2f (this parameter is only supported for encrypted fonts)
parameter.fontScale = 2f;
// Pass the parameters during loading
game.manager.load("BitmapFont/font.fnt", SecretBitmapFont.class, parameter);**其他类的加载方式请自行查看此演示项目源码**
```

---

### Supporting Classes

*Encrypted Class - Original Class*

- **SecretBitmapFont - BitmapFont**

- **SecretFileHandle - FileHandle**

- **SecretI18NBundle - I18NBundle (Not supported for conversion)**

- **SecretMusic - Music (Not supported for conversion)**

- **SecretParticleEffect - ParticleEffect**

- **SecretPixmap - Pixmap**

- **SecretPolygonRegion - PolygonRegion**

- **SecretShaderProgram - ShaderProgram**

- **SecretSkin - Skin**

- **SecretSound - Sound (Not supported for conversion)**

- **SecretTexture - Texture**

- **SecretTextureAtlas - TextureAtlas**

- **SecretTiledMap(.tide) & SecretTiledMap(.tmx) - TiledMap**

- **Other classes (3D API) under development**

---

### Precautions

- This library is best supported for libGDX version 1.12.0; versions below 1.10.0 have not been tested.

- Due to backend reasons in libGDX, `SecretMusic` and `SecretSound` classes on the Android platform currently only decrypt the audio to disk and then play it.

- When releasing on the Android platform, please add the following content to the android/proguard-rules.pro file. This will prevent issues caused by obfuscation of crucial code.
  
  ```groovy
  -keep class com.sun.jna.** { *; }
  -keep class * implements com.sun.jna.** { *; }
  -keep class com.badlogic.** {**[] $VALUES;*;}
  ```
  
  And add the following content to the gradle.properties file to enable AndroidX.
  
  ```groovy
  android.useAndroidX=true
  ```

- The security of encryption primarily depends on how well you protect the key or password. It is recommended to obfuscate the code as much as possible to enhance security. This tool does not guarantee resistance to obfuscation or hardening, so use it with caution.

---

### Declaration

- For inquiries, please join the official libGDX QQ group: 1051955354 [Join Group](https://qm.qq.com/cgi-bin/qm/qr?k=nF1aCeY5JjPLtKLcmZqzISk-qBpXdmhm&jump_from=webapi&authKey=vJ0DKK9jsJCOX8cZhdoKVWG5obuthQHyyqNhlhaNnNjKhXDZkZVJa49xjLua/U4v)
- **If the tool is helpful to you, feel free to give it a star.**
- This tool has not undergone extensive testing and may have significant bugs. If you encounter any bugs, please submit them as issues, and I will try to resolve them as soon as possible.
