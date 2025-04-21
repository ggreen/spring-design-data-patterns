# Data Filtering

The following demonstrates the power of RabbitMQ AMQP filter expressions for streams.


| Project                 | Notes                                                                     |
|-------------------------|---------------------------------------------------------------------------|
| [web-app](apps/web-app) | Web application that using ValKey to present filter data stored in ValKey |



# Demonstrator

## Getting Started


### Start RabbitMQ

Start in RabbitMQ in Podman

```shell
./deployment/local/rabbitmq/start.sh
```

### Starting ValKey

Use Podman to start a Valkey server for premium

```shell
podman run -p 6379:6379 --name=valkey valkey/valkey:8.1
```

Use Podman to start a Valkey server for standard

```shell
podman run -p 6779:6379 --name=valkey-standard valkey/valkey:8.1
```

Access valkey-cli

```shell
podman exec -it valkey bash 
```


## Start Source App

```shell
java -jar applications/patterns/filtering/sources/account-source/target/account-source-0.0.1-SNAPSHOT.jar --spring.rabbitmq.host=localhost
```


## Premium Consumer App


```shell
java -jar applications/patterns/filtering/sinks/account-sink/target/account-sink-0.0.1-SNAPSHOT.jar --spring.rabbitmq.host=localhost --account.type=premium --spring.data.redis.host=localhost --spring.data.redis.port=6379 
```

## Premium Web App

```shell
java -jar applications/patterns/filtering/apps/web-app/target/web-app-0.1.1-SNAPSHOT.jar --account.type=premium --spring.data.redis.host=localhost --spring.data.redis.port=6379 --server.port=8084 --retail.favorites.refresh.rateSeconds=1
```

```shell
open http://localhost:8084
```

## Standard Consumer App


```shell
java -jar applications/patterns/filtering/sinks/account-sink/target/account-sink-0.0.1-SNAPSHOT.jar --spring.rabbitmq.host=localhost --account.type=standard --spring.data.redis.host=localhost --spring.data.redis.port=6779 
```

## Standard Web App

```shell
java -jar applications/patterns/filtering/apps/web-app/target/web-app-0.1.1-SNAPSHOT.jar --account.type=standard --spring.data.redis.host=localhost --spring.data.redis.port=6779 --server.port=8085 --retail.favorites.refresh.rateSeconds=1
```

```shell
open http://localhost:8085
```

---------------------
# Testing


## Start Consumer for premium accounts

```shell
curl -X 'POST' \
'http://localhost:8095/accounts' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"id": "ht001",
"name": "Howard Bikes",
"accountType": "premium",
"status": "OPEN",
"notes": "The Best bikes in the business",
"location": {
"id": "h01",
"address": "123 Howard Street",
"cityTown": "John Town",
"stateProvince": "John",
"zipPostalCode": "55555",
"countryCode": "HS"
}
}'
```


## Start Consumer for premium accounts

```shell
curl -X 'POST' \
'http://localhost:8095/accounts' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"id": "ht001",
"name": "Green Bikes",
"accountType": "premium",
"status": "OPEN",
"notes": "Good price for bikes",
"location": {
"id": "h01",
"address": "123 Straight Street",
"cityTown": "JC",
"stateProvince": "NC",
"zipPostalCode": "55555",
"countryCode": "US"
}
}'
```



```shell
for i in {1..20}
do
  accountJson='{ "id": "';
  accountJson+=$i;
  accountJson+='", "name": "Howard Bikes';
  accountJson+=$i;
  accountJson+='", "accountType": "standard"';
  accountJson+=', "status": "OPEN", "notes": "Good price for bikes", "location": { "id": "g01", "address": "123 Straight Street", "cityTown": "JC", "stateProvince": "NJ", "zipPostalCode": "55555", "countryCode": "US"} ';
  accountJson+='}';
  
  echo $accountJson;
  
  curl -X 'POST' \
  'http://localhost:8095/accounts' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
  -d $accountJson
  echo;  
done
```

Standard
```shell
for i in {1..20}
do
  accountJson='{ "id": "';
  accountJson+=$i;
  accountJson+='", "name": "Premium Bike';
  accountJson+=$i;
  accountJson+='", "accountType": "premium"';
  accountJson+=', "status": "OPEN", "notes": "The Best bikes in the business", "location": { "id": "h01", "address": "123 Howard Street", "cityTown": "John Town", "stateProvince": "John", "zipPostalCode": "55555", "countryCode": "HS"} ';
  accountJson+='}';
  
  echo $accountJson;
  
  curl -X 'POST' \
  'http://localhost:8095/accounts' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
  -d $accountJson
  echo;  
done
```
