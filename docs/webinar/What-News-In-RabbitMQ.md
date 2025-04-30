# Install ValKey

```shell
#./deployment/cloud/k8/data-services/valkey/install-valkey.sh
```

```shell
#./deployment/cloud/k8/data-services/valkey/install-premium-valkey.sh
```

create 5 nodes

```shell
kubectl get pvc
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

Deploy Filtering Applications

```shell
kubectl apply -f deployment/cloud/k8/apps/filtering
```


```shell
export filtering_account_source=`kubectl get service filtering-account-source -o jsonpath="{.status.loadBalancer.ingress[0].ip}"`
export filtering_standard_web_app=`kubectl get service filtering-standard-web-app -o jsonpath="{.status.loadBalancer.ingress[0].ip}"`
export filtering_web_premium_app=`kubectl get service filtering-web-premium-app -o jsonpath="{.status.loadBalancer.ingress[0].ip}"`


echo "filtering_account_source open " http://$filtering_account_source:8080
echo "filtering_standard_web_app open " http://$filtering_standard_web_app:8080
echo "filtering_web_premium_app open " http://$filtering_web_premium_app:8080

```

Testing



```json
{
  "id": "standard1",
  "name": "Standard Account 01",
  "accountType": "standard",
  "status": "OPEN",
  "notes": "This account is standard for a test",
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



=======================

## Cleanup 



```shell
kubectl exec -it valkey-premium-primary-0 -- valkey-cli

Del "showcase.streaming.event.account.domain.Account:premium03"
keys "*"
```


```shell
kubectl exec -it valkey-primary-0 -- valkey-cli

Del "showcase.streaming.event.account.domain.Account:standard1"
Del "showcase.streaming.event.account.domain.Account:standard2"
keys "*" 
```

echo DEL "showcase.streaming.event.account.domain.Account:premium02" | valkey-cli
kubectl exec -it valkey-premium-primary-0 -- echo DEL "showcase.streaming.event.account.domain.Account:premium03" | valkey-cli


 
```

```shell
kubectl delete -f deployment/cloud/k8/apps/filtering
kubectl delete RabbitMQCluster rabbitmq
kubectl get pvc
```