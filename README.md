# ip

사설 Ip를 보여주는 간단한 서비스

![image](https://user-images.githubusercontent.com/84070816/224200780-244afa6b-55d9-421a-8dee-126ab0212b30.png)

## 사용법

----

### Docker 🐳
```dockerfile
# 이미지 빌드
docker build -t ipservice . 
# 이미지 실행
docker run -dp 8080:8080 -e ipservice

# 환경 변수옵션 사용
docker run -dp 8080:8080 -e Forwarded=1 ipservice
```