TODO: Not working yet

Start App 1

```shell
java -jar applications/services/function/function-service/target/function-service-0.0.1-SNAPSHOT.jar --server.port=8081
```


Start App 2

```shell
java -jar applications/services/function/function-service/target/function-service-0.0.1-SNAPSHOT.jar --server.port=8082
```


Starting the gateway service


```shell
java -jar applications/services/gateway/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar --spring.config.location="file///Users/Projects/solutions/Spring/dev/spring-design-data-patterns/applications/services/gateway/gateway-service/src/main/resources/employees.yaml"
```


```shell
curl -X GET http://localhost:8080
```


Save Employee

```shell
curl -X 'POST' \
  'http://localhost:8080/saveEmployee' \
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

Stop App1


```shell
curl -X 'POST' \
  'http://localhost:8080/saveEmployee' \
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