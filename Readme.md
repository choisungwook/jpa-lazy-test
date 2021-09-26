# 개요
* 유투브 실습을 진행을 위한 브랜치입니다.

<br>

# nginx 설정
```conf
upstream Frontend {
    # 변경 -> front svc nodeport
    server 127.0.0.1:31023;
}

server {
    listen       80;
    # <변경> -> 도메인
    server_name  http://youtubetest.choicloudlab.com;

    location / {
        proxy_hide_header Access-Control-Allow-Origin;
        proxy_redirect off;
        add_header 'Access-Control-Allow-Origin' '*';
        proxy_set_header Host $host:$server_port;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

        # <변경> -> frontend nodeport
        proxy_pass http://Frontend;
    }
}
```