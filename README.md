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
) 
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
)
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
)
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
)
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