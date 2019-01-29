# cors
springboot整合cors-filter自定义实现可外部化配置的web跨域配置，当配置文件中不包含web.cors.allowOrigins字段时，拦截器不会注册。

```
配置web.cors.allowOrigins=http://super.net ,web.cors.allowSubdomains=true 
url=max.super.ent,min.super.net 都允许跨域。
```
application.properties配置文件内容

```
# 指定允许跨域的url
web.cors.allowOrigins=http://super.net,http://super.com
# 指定是否允许子域名跨域
web.cors.allowSubdomains=true
```
