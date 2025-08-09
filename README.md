# SpringCase Projesi

YİĞİTCAN ABAY 09.08.2025-13:00 

## Teknolojiler
- Java 17
- Spring Boot 3
- Spring Security (JWT)
- PostgreSQL 15
- Redis 7
- Docker & Docker Compose
- Swagger UI (OpenAPI)


## Kurulum

### Gereksinimler
- Java 17+
- Docker & Docker Compose
- Maven veya Gradle

### Projeyi Çalıştırma

1. Repoyu klonlayın:
   ```bash
   git clone https://github.com/kullaniciadi/springcase.git
   cd springcase
###Docker ile PostgreSQL ve Redis servisini ayağa kaldırın:
  docker-compose up --build
  ###Uygulamayı çalıştırın:
    ./mvnw spring-boot:run
################Docker Komutları
  docker-compose up --build


#########Örnek Kullanıcılar######
Kullanıcı Adı	Rol	Email	Şifre
superadmin	SUPERADMIN	superadmin@domain.com	Pass1234
teacher1	TEACHER	teacher1@domain.com	Pass1234
student1	STUDENT	student1@domain.com	Pass1234

Proje Özellikleri ve Notlar
PostgreSQL veritabanı kullanılmıştır.

Redis cache mekanizması olarak entegre edilmiştir.

Spring Security ve JWT ile güvenlik sağlanmıştır.

API endpoint'leri Swagger UI üzerinden test edilebilir.

Role based access control (RBAC) ile kullanıcı yetkilendirmeleri yapılmaktadır.

Docker Compose ile kolay kurulum ve çalışma ortamı sağlanmıştır.
