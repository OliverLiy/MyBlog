耗时大约三个星期不到的时间，把这个论坛项目基本上算是完成了，做这个项目最主要的目的是熟悉SpringBoot的使用，然后通过整个项目了解了BootStrap、Thymeleaf、editor.md等等工具的使用
项目的一些东西我都放在了我的csdn博客上，大家可以去看看，包括如何跑起来等等
https://blog.csdn.net/qq_41973594/article/details/103288287

## 项目介绍
首先进入登陆页面，输入用户名和密码进入首页，也可以点击右下角注册新用户：
![image](https://github.com/OliverLiy/MyBlog/blob/master/20191128101046625.png)
登陆成功后进入首页，可以看到用户发的各种问题：
![image](https://github.com/OliverLiy/MyBlog/blob/master/20191128101055266.png)
点击第一个我自己发的问题：
![image](https://github.com/OliverLiy/MyBlog/blob/master/20191128101104235.png)
问题标题，详细内容，标签都存在，右侧相关问题是根据发布问题时所选标签进行匹配，底部可以留言：
![image](https://github.com/OliverLiy/MyBlog/blob/master/20191128101113662.png)
进入个人中心可以看到我的问题和我的消息
![image](https://github.com/OliverLiy/MyBlog/blob/master/20191128101126870.png)
点击消息后未读标志消失，问题发布使用editor.md文本框，可输入富文本
![image](https://github.com/OliverLiy/MyBlog/blob/master/20191128101137875.png)


## 数据库建表语句：
#### user
```$user建表语句
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `token` varchar(45) NOT NULL,
  `headpic` varchar(1024) NOT NULL DEFAULT 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574701469761&di=0e1045169debf1aed834fc97ff9b2439&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F31%2F87%2F96573b585a7c9c4.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8
```

#### question
```$question建表语句
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `createid` int(11) NOT NULL,
  `comment_count` int(11) NOT NULL DEFAULT '0',
  `view_count` int(11) NOT NULL DEFAULT '0',
  `like_count` int(11) NOT NULL DEFAULT '0',
  `tag` varchar(250) NOT NULL,
  `createtime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8
```
#### comment
```$user建表语句
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `commentor` int(11) DEFAULT NULL,
  `createtime` bigint(20) DEFAULT NULL,
  `like_count` int(11) DEFAULT '0',
  `content` varchar(200) NOT NULL,
  `commentcount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8
```
#### notification
```$notification建表语句
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `outerid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `createtime` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8
```

## thymeleaf官方文档
```
https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html
```
## BootStrap官方文档
```
https://v3.bootcss.com/components/
```
## Editor.md官网
```
http://editor.md.ipandao.com/
```
