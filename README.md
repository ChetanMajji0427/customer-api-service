# File: README.md

# Customer API Service
This is a Spring Boot application that exposes a RESTful API for managing customer data. It includes observability features, container support, CI/CD pipeline, and a CLI client.

## Features
- CRUD operations for customer records
- H2 in-memory database
- Unit and integration tests
- Observability using Micrometer and Prometheus
- Dockerized
- Deployable to Kubernetes
- GitHub Actions pipeline
- Python CLI client

## Prerequisites
- Java 17
- Maven
- Docker (optional)
- Python 3
- Kubernetes and Minikube (optional)

## How to Build and Run
```bash
./mvnw clean package
java -jar target/customer-api-service-*.jar
```
API will be available at: `http://localhost:8080/api/customers`

## Running Tests
```bash
./mvnw test
```

## Docker Usage
```bash
docker build -t customer-api .
docker run -p 8080:8080 customer-api
```

## Kubernetes Deployment
```bash
minikube start
eval $(minikube docker-env)
docker build -t customer-api .
kubectl apply -f k8s-deployment.yaml
minikube service customer-api-service
```

## CI/CD with GitHub Actions
GitHub Actions will trigger on pushes and pull requests to the `main` branch. It runs build, tests, and an optional Docker build.
Config file: `.github/workflows/ci.yml`

## Python CLI Client
```bash
python cli-client.py list
python cli-client.py create
python cli-client.py get --id <UUID>
python cli-client.py delete --id <UUID>
```

## Notes
- The application uses H2 in-memory DB; data will be reset on restart.
- Prometheus metrics: `http://localhost:8080/actuator/prometheus`
- H2 console: `http://localhost:8080/h2-console` (username: `sa`, password is empty)

## Project Layout (Overview)
```
├── src/main/java/com/example/customer
├── cli-client.py
├── Dockerfile
├── k8s-deployment.yaml
├── .github/workflows/ci.yml
├── application.properties
└── README.md
```

## Submission Guidelines
- Push to a **public GitHub repository**
- Do not mention the company name in code or README
- Email the recruiter with your repository link
