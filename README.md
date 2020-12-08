# listen-music-together
**注意:本项目仅供学习和研究使用,使用本项目造成任何后果本人概不负责**

一起听歌,一起嗨皮
本项目作为一起听歌的后端项目
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
* 房间管理
    * [x] 创建房间
    * [x] 加入房间
    * [x] 离开房间
    * [ ] 解散房间
* 歌单管理
    * [ ] 创建歌单
    * [ ] 添加歌曲
    * [ ] 移除歌曲
    * [ ] 排序
* 点歌
    * [ ] 歌曲搜索
    * [ ] 点歌
    * [ ] 点赞
    * [ ] 移除歌曲
    * [ ] 切歌
 
## 项目依赖
* [NeteaseCloudMusicApi](https://github.com/Binaryify/NeteaseCloudMusicApi)
* [QQMusicApi](https://github.com/jsososo/QQMusicApi)
* [MiguMusicApi](https://github.com/JumpAlang/MiguMusicApi)
* [MiguMusicApi](https://github.com/jsososo/MiguMusicApi)


