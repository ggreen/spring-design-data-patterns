

## DOCKER

```shell
mvn install
cd  applications/patterns/integration/filtering/sources/filtering-account-source
docker build  --platform linux/amd64,linux/arm64 -t filtering-account-source:0.0.1-SNAPSHOT .
```

Push
```shell
docker tag filtering-account-source:0.0.1-SNAPSHOT cloudnativedata/filtering-account-source:0.0.1-SNAPSHOT
docker push cloudnativedata/filtering-account-source:0.0.1-SNAPSHOT
```
