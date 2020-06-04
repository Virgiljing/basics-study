## 将代码同时上传到github和码云

代码放到GitHub上，但是偶尔从GitHub拉去代码时服务器在国外网速不好可能很难把代码拉去下来。就将GitHub上的代码同步到了gitee上了，然后修改代码时就需要同时提交到GitHub和gitee。
之前都是用github存储代码，后来接触到码云之后感觉也挺好用的。但是忽然发现上传代码的时候混掉了，想往github上上传结果传到码云上了，不知道怎么切换。终究还是git技能不过关。网上查询之后整理下，以免忘掉。

因为git本身是分布式版本控制系统，可以同步到另外一个远程库，当然也可以同步到另外两个远程库，所以一个本地库可以既关联GitHub，又关联码云！
使用多个远程库时，要注意git给远程库起的默认名称是origin，如果有多个远程库，我们需要用不同的名称来标识不同的远程库。仍然以learngit本地库为例，先删除已关联的名为origin的远程库：
```gitexclude
git remote rm origin
```
然后，先关联GitHub的远程库：
```gitexclude
git remote add github git@github.com:xxx/LearnGit.git
```
注意，远程库的名称叫github，不叫origin了。
接着，再关联码云的远程库：
```gitexclude
git remote add gitee git@gitee.com:xxx/LearnGit.git
```
同样注意，远程库的名称叫gitee，不叫origin。
现在，我们用git remote -v查看远程库信息，可以看到两个远程库：
```gitexclude
gitee   git@gitee.com:xxx/LearnGit.git (fetch)
gitee   git@gitee.com:xxx/LearnGit.git (push)
github  git@github.com:xxx/LearnGit.git (fetch)
github  git@github.com:xxx/LearnGit.git (push)
```
如果要推送到GitHub，使用命令：
git push github master
如果要推送到码云，使用命令：
git push gitee master
这样一来，本地库就可以同时与多个远程库互相同步