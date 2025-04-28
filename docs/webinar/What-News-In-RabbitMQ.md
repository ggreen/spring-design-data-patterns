Install ValKey

```shell
./deployment/cloud/k8/data-services/valkey/install-valkey.sh
```

```shell
./deployment/cloud/k8/data-services/valkey/install-premium-valkey.sh
```

Install RabbitMQ Cluster Operator

```shell
./deployment/cloud/k8/data-services/rabbitmq/rabbit-k8-setup.sh
```


create 5 nodes

```shell
kubectl apply -f deployment/cloud/k8/data-services/rabbitmq/rabbitmq-5-node.yml
```

Get Credentials 

```shell
export dash_url=`kubectl get service rabbitmq -o jsonpath="{.status.loadBalancer.ingress[0].ip}"`
export ruser=`kubectl get secret rabbitmq-default-user -o jsonpath="{.data.username}"| base64 --decode`
export rpwd=`kubectl get secret rabbitmq-default-user -o jsonpath="{.data.password}"| base64 --decode`

echo ""
echo "open " http://$dash_url:15672
echo "USER:" $ruser
echo "PASSWORD:" $rpwd

```


Upgrade nodes


```shell
kubectl apply -f ./deployment/cloud/k8/data-services/rabbitmq/rabbitmq-5-node-upgrade.yml
```

```shell
kubectl exec rabbitmq-server-0 -- rabbitmqctl enable_feature_flag all
```

------------

## Apps

Deploy Webs

```shell
kubectl apply -f deployment/cloud/k8/apps/filtering/filtering-standard-web-app.yml
kubectl apply -f deployment/cloud/k8/apps/filtering/filtering-premium-web-app.yml
```

```shell
kubectl apply -f deployment/cloud/k8/apps/filtering/filtering-account-source.yml
```

```shell
kubectl apply -f deployment/cloud/k8/apps/filtering/filtering-standard-account-sink.yml
kubectl apply -f deployment/cloud/k8/apps/filtering/filtering-premium-account-sink.yml
```

Testing



```json
{
  "id": "standard1",
  "name": "Standard Account 01",
  "accountType": "standard",
  "status": "OPEN",
  "notes": "This account is standard",
  "location": {
    "id": "string",
    "address": "12 Straight Street",
    "cityTown": "Standard",
    "stateProvince": "Stand",
    "zipPostalCode": "55555",
    "countryCode": "Standard"
  }
}
```


```json
{
  "id": "premium02",
  "name": "Howard Account 02",
  "accountType": "premium",
  "status": "OPEN",
  "notes": "This account is premium",
  "location": {
    "id": "string",
    "address": "12 Howard Street",
    "cityTown": "Howard",
    "stateProvince": "Howard",
    "zipPostalCode": "55555",
    "countryCode": "London"
  }
}
```