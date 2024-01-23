git init
git add .
git commit -m "Test"
#git remote add origin https://github.com/MUZIStudio/Gdx-Assets-Protector-Examples.git
git push -u origin master

git config --global http.proxy http://127.0.0.1:10809
git config --global https.proxy http://127.0.0.1:10809