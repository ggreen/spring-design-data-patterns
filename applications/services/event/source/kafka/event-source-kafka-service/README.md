
Start Kafka


```shell
podman run -p 9092:9092 apache/kafka-native:4.1.0
```

```shell
$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic employees --from-beginning
```

Start Application

```shell
java -jar applications/services/event/source/kafka/event-source-service/target/event-source-service-0.0.1-SNAPSHOT.jar
```

Post


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