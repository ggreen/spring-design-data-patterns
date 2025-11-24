# Function Service example

Create a Spring Boot application that exposes REST endpoints to save and retrieve employee data using functional programming style.

Start the application service

app 1
```shell
java -jar applications/services/function/function-service/target/function-service-0.0.1-SNAPSHOT.jar --server.port=8082
```

Open in browser
```shell
open http://localhost:8082
```


Save Employee

```shell
curl -X 'POST' \
  'http://localhost:8082/saveEmployee' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "01",
  "empName": "Imani Josiah",
  "jobName": "eng",
  "hireDate": "2025-11-24T12:33:02.016Z",
  "salary": 260000,
  "commission": 0,
  "department": "physics",
  "managerId": "john"
}'
```


Get Employee by ID

```shell
curl -X 'GET' \
  'http://localhost:8082/getEmployee/01' \
  -H 'accept: application/json'
```
