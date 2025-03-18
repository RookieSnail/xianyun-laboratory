# Fnm-1.38.1-安装以及配置-windows
### [官网地址](https://github.com/Schniz/fnm)
***

## 安装，默认安装路径，可以使用 fnm env查看【FNM_DIR】环境变量
```
# Download and install fnm:
winget install Schniz.fnm
```

## 环境变量配置
```
# 执行 fnm -h 查看命令列表
fnm -h
# 执行 fnm env 查看或者设置终端的环境变量
fnm env
```

## 主要进行调整的参数
> * FNM_DIR：安装路径根目录
> * FNM_MULTISHELL_PATH：每个SHELL终端信息目录

## 安装&切换 node
```
# 执行 fnm list-remote 查看版本库中可安装的node版本
fnm list-remote
# 执行 fnm install 版本号（只输入大版本号，则会安装对应大版本的最新版）
fnm install 22 或者 fnm install v23.8.0
# 执行 fnm list 查看本地已安装的node版本
fnm list
# 执行 fnm use 版本号（只输入大版本号，则会使用对应大版本的最新版）
node -v 
# 执行 node -v 查看node版本
node -v
```

## 卸载 node
```
# 执行 fnm uninstall 版本号（只输入大版本号，当本地安装了多个同大版本号的node时，需要更换为具体的版本）（）
fnm uninstall 12
# 执行 fnm install 版本号（只输入大版本号，则会安装对应大版本的最新版）
fnm install 22 或者 fnm install v23.8.0
# 执行 fnm list 查看本地已安装的node版本
fnm list
# 执行 fnm use 版本号（只输入大版本号，则会使用对应大版本的最新版）
node -v 
# 执行 node -v 查看node版本
node -v
```
