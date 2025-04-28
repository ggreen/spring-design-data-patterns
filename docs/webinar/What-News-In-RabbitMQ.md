Install ValKey

```shell
./deployment/cloud/k8/data-services/valkey/install-valkey.sh
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


------------

```shell
kubectl apply -f deployment/cloud/k8/apps/flitering/filtering-web-app.yml
```