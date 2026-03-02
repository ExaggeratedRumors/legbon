# Legbon Server

![](https://shields.io/badge/Ktor-3.3.3-violet) ![](https://shields.io/badge/v1.0-purple)

Application backend.

## Release

`
version 1.0
`

## Technologies

- Kotlin 2.2.20
- Ktor 3.3.3
- Exposed 1.1.1

## Configuration

### application.conf
Application uses `src/main/resources/application.conf` file to configure itself. 
Add your own configuration following this example:

```yaml
ktor {
    deployment {
      port = 8080
    }
    
    application {
      modules = [ com.er.legbon.ApplicationKt.module ]
    }
    
    jwt {
      issuer = "com.er.legbon"
      audience = "com.er.legbon"
    }
}
```

### Environments

Application uses `.env` file to load database data and jwt secrets.

1. Create `.env` file and fill by cloning `.env.example` file content.
2. Replace data with your own values.
3. Run server using command line:
```bash
env $(cat .env | xargs) ./gradlew run 
```
or by adding environment variables in IDE configuration.


## Docker

Create fat jar:
```bash
./gradlew shadowJar
```

Create image:
```bash
docker build -t legbon-server-img .
```

Create container:
```bash
docker run -p 8080:8080 --name legbon-server legbon-server-img
```


## Public-API Endpoints

```http request
### New user
POST http://127.0.0.1:8083/register
Content-Type: application/json

{
  "name": "test1",
  "login": "login1",
  "password": "password1"
}
```



