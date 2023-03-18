# ipservice

사설 Ip를 보여주는 간단한 서비스

<img width="414" alt="image" src="https://user-images.githubusercontent.com/84070816/224537040-c5c2cc21-afce-4a52-99c9-2bb69a88f021.png">

X-Forwarded-For 헤더를 임의로 조작해도 진짜 ip를 보여줌

## 사용법

### Docker 🐳
```dockerfile
# 이미지 풀
docker pull janpll0421/ipservice

# 이미지 실행
docker run -dp 8080:8080 janpll0421/ipservice

# 환경 변수옵션 사용
docker run -dp 8080:8080 -e Forwarded=1 janpll0421/ipservice
```
