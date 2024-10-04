<h1 align="center">开发时</h1>

> 替换sky.sql中的图片路径为自己阿里云OSS村的图片路径
> 替换application-dev.yml中阿里云OSS的key和secret、微信小程序的key和secret

# 1. MySQL

```bash
# 启动mysql

mysql -u root -p
# 输入密码
# ...

# 替换为sky.sql所在路径
source xx/xx/mysql/sky.sql
# 待初始化完毕
```

# 2. Redis

```bash
# 挂载redis.conf，换成实际路径，密码设置了123456
docker run --name redis_practice -p 6379:6379 -d -it -v .:/usr/local/etc/redis redis redis-server /usr/local/etc/redis/redis.conf
```

# 3. 启动 SpringBoot

# 4. 启动前端
