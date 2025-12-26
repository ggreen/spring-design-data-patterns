# Source RabbitMQ


Start Rabbitmq

```shell
podman run -it --rm \
  --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=guest \
  -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:4.2-management
```


Start Source

```shell
java -jar applications/services/event/source/rabbit/event-source-rabbit-service/target/event-source-rabbit-service-0.0.1-SNAPSHOT.jar
```


open RabbitMQ
```shell
open http://localhost:15672
```


Submit Post

```shell
curl -X 'POST' \
  'http://localhost:8080/employees' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "01",
  "empName": "Josiah John",
  "jobName": "Agent",
  "hireDate": "2025-10-20T11:51:37.758Z",
  "salary": 50000,
  "commission": 1000,
  "department": "Agency",
  "managerId": "01"
}'
```