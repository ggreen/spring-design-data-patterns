# Running web-app



## DOCKER

```shell
mvn install
cd applications/patterns/integration/filtering/apps/filtering-web-app
mvn clean package
docker build  --platform linux/amd64,linux/arm64 -t filtering-web-app:0.0.1-SNAPSHOT .

```


## Podman building image (not working)

```shell
podman login hub.docker.com -u $DOCKER_USER -p $DOCKER_PASSWORD
mvn install
cd applications/patterns/integration/filtering/apps/filtering-web-app
podman build   --platform linux/amd64,linux/arm64 -t filtering-web-app:0.0.1-SNAPSHOT .

podman build   --platform linux/amd64 -t filtering-web-app:0.0.1-SNAPSHOT .

podman manifest create filtering-web-app:0.0.1-SNAPSHOT
podman build --platform linux/amd64,linux/arm64  --manifest filtering-web-app:0.0.1-SNAPSHOT  .
podman manifest push filtering-web-app:0.0.1-SNAPSHOT
```

```shell
podman tag filtering-web-app:0.0.1-SNAPSHOT cloudnativedata/filtering-web-app:0.0.1-SNAPSHOT
podman push cloudnativedata/filtering-web-app:0.0.1-SNAPSHOT
```