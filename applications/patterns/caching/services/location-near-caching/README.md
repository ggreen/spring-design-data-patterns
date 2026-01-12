
```shell
./deployment/local/postgres/start.sh
```

```shell
./deployment/local/valkey/start.sh
```


location

```shell
./deployment/local/valkey/start-cli.sh
```

```shell
SET location '{"id" : "loc1", "address" : "123 Main St", "cityTown" : "Springfield", "stateProvince" : "IL", "zipPostalCode" : "62701", "countryCode" : "US"}'
DEL location
```

```shell
XADD location * loc1 '{"id" : "loc1", "address" : "123 Main St", "cityTown" : "Springfield", "stateProvince" : "IL", "zipPostalCode" : "62701", "countryCode" : "US"}'
```

```shell
XREAD COUNT 100 BLOCK 300 STREAMS "\xac\xed\x00\x05t\x00\blocation" $
```

```shell
podman run -it --name redis --rm -p 6379:6379  redis:latest
```

```shell
podman exec -it redis redis-cli
```