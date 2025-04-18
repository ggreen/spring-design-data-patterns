
## Getting Started


```shell
podman run -p 6379:6379 --name=valkey valkey/valkey:8.1
```

```shell
podman exec -it valkey bash 
```


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



```shell
for i in {1..100000}
do
  accountJson='{ "id": "';
  accountJson+=$i;
  accountJson+='", "name": "Howard Bikes';
  accountJson+=$i;
  accountJson+='", "accountType": "premium", "status": "OPEN", "notes": "The Best bikes in the business", "location": { "id": "h01", "address": "123 Howard Street", "cityTown": "John Town", "stateProvince": "John", "zipPostalCode": "55555", "countryCode": "HS"} ';
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

