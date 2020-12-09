# listen-music
**注意:本项目仅供学习和研究使用,使用本项目造成任何后果本人概不负责**

本项目作为跨平台听歌的后端项目


## 简述
* 项目基于springboot进行开发,与外界交互使用http接口和websocket和外部交互
* 底层直接用redis存储
* 音乐服务均来自第三方
## 灵感来源
### [Jusic-Serve-Houses](https://github.com/JumpAlang/Jusic-Serve-Houses)
学习和研究的过程中,发现不符合本人编码习惯,所以打算重新做一个,重复造轮子什么的最棒了

## 项目结构说明

![](./doc/项目层次.jpg)

## 功能
* 用户(会话)
    * [ ] 设计
    * [ ] 实现
* 房间管理
    * [x] 创建房间
    * [ ] 加入房间
    * [ ] 离开房间
    * [ ] 解散房间
* 歌单管理
    * [x] 创建歌单
    * [x] 添加歌曲
    * [x] 移除歌曲
    * [x] 获取歌单信息
    * [x] 获取歌单中的音乐列表
    * [ ] 排序
* 歌曲
    * NeteaseCloudMusicApi
        * [x] 获取单个歌曲信息
        * [x] 获取单个歌曲播放地址
        * [x] 搜索歌曲
        * [ ] 搜索歌单
        * [ ] 搜索用户
    * QQMusicApi
        * [ ] 获取单个歌曲信息
        * [ ] 获取单个歌曲播放地址
        * [ ] 搜索歌曲
        * [ ] 搜索歌单
        * [ ] 搜索用户
    * MiguMusicApi
        * [ ] 获取单个歌曲信息
        * [ ] 获取单个歌曲播放地址
        * [ ] 搜索歌曲
        * [ ] 搜索歌单
        * [ ] 搜索用户
 
## 项目依赖
* [NeteaseCloudMusicApi](https://github.com/Binaryify/NeteaseCloudMusicApi)
* [QQMusicApi](https://github.com/jsososo/QQMusicApi)
* [MiguMusicApi](https://github.com/jsososo/MiguMusicApi)


