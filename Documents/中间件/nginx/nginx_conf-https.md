# Nginx-1.26.2-nginx.conf-https-linux
### [官网地址](https://nginx.org/en/)
***

## https配置，http所有请求转发https
```
http {

  include       mime.types;
  default_type  application/octet-stream;
  
  #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
  #                  '$status $body_bytes_sent "$http_referer" '
  #                  '"$http_user_agent" "$http_x_forwarded_for"';
  
  #access_log  logs/access.log  main;
  
  sendfile        on;
  #tcp_nopush     on;
  
  #keepalive_timeout  0;
  keepalive_timeout  65;

  gzip  on;  #开启gzip
  gzip_min_length 1k; #低于1kb的资源不压缩
  gzip_comp_level 9;  #压缩级别1-9，越大压缩率越高，同时消耗cpu资源也越多，建议设置在5左右。
  gzip_types text/plain application/javascript application/x-javascript text/javascript text/xml text/css;  #需要压缩哪些响应类型的资源，多个空格隔开。不建议压缩图片.
  gzip_disable "MSIE [1-6]\.";  #配置禁用gzip条件，支持正则。此处表示ie6及以下不启用gzip（因为ie低版本不支持）
  gzip_vary on; #是否添加Vary: Accept-Encoding响应头
    
  # http
  server {
    listen       80;
    server_name  127.0.0.1;
   
    #将所有HTTP请求通过rewrite指令重定向到HTTPS。
    rewrite ^(.*)$ https://$host$1;
  } 
  
  # https
  server {
    listen       443 ssl;
    server_name  localhost;

    ssl_certificate      certs/证书名.pem;
    ssl_certificate_key  certs/证书名.key;
    
    ssl_session_cache    shared:SSL:1m;
    ssl_session_timeout  5m;
    ssl_protocols TLSv1.2 TLSv1.3;
    
    ssl_ciphers  HIGH:!aNULL:!MD5;
    ssl_prefer_server_ciphers  on;


    #charset koi8-r;

    #access_log  logs/host.access.log  main;

    location /xxx {
         proxy_pass   http://ip或者域名:端口;
    }

    location / {
         proxy_pass   http://ip或者域名:端口;
    }

    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   html;
    }

    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
    #
    #location ~ \.php$ {
    #    proxy_pass   http://127.0.0.1;
    #}

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    #location ~ \.php$ {
    #    root           html;
    #    fastcgi_pass   127.0.0.1:9000;
    #    fastcgi_index  index.php;
    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
    #    include        fastcgi_params;
    #}

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    #
    #location ~ /\.ht {
    #    deny  all;
    #}
  }
}
```