# 개요
* 유투브 실습을 진행을 위한 브랜치입니다.

<br>

# nginx 설정
```conf
server {
    listen       80;
    # <변경> -> 도메인
    server_name  myproject.choicloudlab.com/lazy;

    # SSL 설정
    ssl on;
    # <변경> -> cert 인증서 위치
    ssl_certificate /etc/lets_encrypt/cert.pem;
    # <변경> -> 인증서 key 위치
    ssl_certificate_key /etc/lets_encrypt/cert_key.pem;
    ssl_prefer_server_ciphers on;
    ssl_session_timeout 5m;

    location / {
        return 301 https://$server_name$request_uri;
    }
}

server {
    listen       443;
    # <변경> -> 도메인
    server_name  myproject.choicloudlab.com;

    # SSL 설정
    ssl on;
    # <변경> -> cert 인증서 위치
    ssl_certificate /etc/lets_encrypt/cert.pem;
    # <변경> -> 인증서 key 위치
    ssl_certificate_key /etc/lets_encrypt/cert_key.pem;
    ssl_prefer_server_ciphers on;
    ssl_session_timeout 5m;

    location / {
        proxy_hide_header Access-Control-Allow-Origin;
        proxy_redirect off;
        add_header 'Access-Control-Allow-Origin' '*';
        proxy_set_header Host $host:$server_port;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

        # <변경> -> frontend nodeport
        proxy_pass "http://127.0.0.1:31023";
    }
}

```