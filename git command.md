Git手册
======

* git基本提交
```
来源：github创建项目文档
…or create a new repository on the command line

echo "# doc" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/lylandroid/doc.git
git push -u origin master

…or push an existing repository from the command line

git remote add origin https://github.com/lylandroid/doc.git
git push -u origin master
```