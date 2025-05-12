# URL Shortener Service

A robust URL shortening service built with Spring Boot that allows users to create short, memorable URLs from long ones. The service includes features like URL redirection, expiration dates, and a RESTful API.

## Features

- Create short URLs from long URLs
- Automatic redirection to original URLs
- URL expiration management
- RESTful API endpoints
- Unique short code generation
- URL validation
- Error handling

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 3.x
- PostgreSQL (or your preferred database)

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/url-shortener.git
cd url-shortener
```

### 2. Configure Database
Update `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/urlshortener
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### 1. Create Short URL
```http
POST /api/urls
Content-Type: application/json

{
    "originalUrl": "https://www.youtube.com",
    "expiryDays": 30
}
```

Response:
```json
{
    "id": 1,
    "originalUrl": "https://www.youtube.com",
    "shortCode": "https://www.a1b2c3d4.com",
    "createdAt": "2024-03-20T10:00:00",
    "expiresAt": "2024-04-19T10:00:00"
}
```

### 2. Get URL by Short Code
```http
GET /api/urls/{shortCode}
```

Response:
```json
{
    "id": 1,
    "originalUrl": "https://www.youtube.com",
    "shortCode": "https://www.a1b2c3d4.com",
    "createdAt": "2024-03-20T10:00:00",
    "expiresAt": "2024-04-19T10:00:00"
}
```

### 3. Get All URLs
```http
GET /api/urls
```

Response:
```json
[
    {
        "id": 1,
        "originalUrl": "https://www.youtube.com",
        "shortCode": "https://www.a1b2c3d4.com",
        "createdAt": "2024-03-20T10:00:00",
        "expiresAt": "2024-04-19T10:00:00"
    }
]
```

### 4. Delete URL
```http
DELETE /api/urls/{shortCode}
```

Response: 204 No Content

### 5. Redirect to Original URL
```http
GET /api/urls/r/{shortCode}
```

Response: 302 Redirect to original URL

## Usage Examples

### Using cURL

1. Create a short URL:
```bash
curl -X POST http://localhost:8080/api/urls \
-H "Content-Type: application/json" \
-d '{
    "originalUrl": "https://www.youtube.com",
    "expiryDays": 30
}'
```

2. Get URL by short code:
```bash
curl -X GET http://localhost:8080/api/urls/a1b2c3d4
```

3. Get all URLs:
```bash
curl -X GET http://localhost:8080/api/urls
```

4. Delete URL:
```bash
curl -X DELETE http://localhost:8080/api/urls/a1b2c3d4
```

### Using Postman

Import the provided Postman collection from the `postman` directory to test all endpoints.

## Project Structure

```
src/main/java/com/urlshortener/
├── controller/
│   └── UrlController.java
├── service/
│   ├── UrlService.java
│   └── UrlServiceImpl.java
├── repository/
│   └── UrlRepository.java
├── model/
│   └── UrlMapping.java
└── UrlShortenerApplication.java
```

## Error Handling

The API returns appropriate HTTP status codes and error messages:

- 200: Success
- 201: Created
- 400: Bad Request
- 404: Not Found
- 500: Internal Server Error

Error Response Format:
```json
{
    "timestamp": "2024-03-20T10:00:00",
    "status": 404,
    "error": "Not Found",
    "message": "URL not found",
    "path": "/api/urls/invalid-code"
}
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Your Name - your.email@example.com

Project Link: [https://github.com/yourusername/url-shortener](https://github.com/yourusername/url-shortener) 