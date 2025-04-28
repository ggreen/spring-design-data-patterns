

## DOCKER

```shell
mvn install
cd applications/patterns/integration/filtering/sinks/filtering-account-sink
docker build  --platform linux/amd64,linux/arm64 -t filtering-account-sink:0.0.1-SNAPSHOT .
```

Push
```shell
docker tag filtering-account-sink:0.0.1-SNAPSHOT cloudnativedata/filtering-account-sink:0.0.1-SNAPSHOT
docker push cloudnativedata/filtering-account-sink:0.0.1-SNAPSHOT
```
