helm uninstall valkey-premium
helm uninstall valkey

kubectl delete pvc valkey-data-valkey-premium-primary-0
kubectl delete pvc valkey-data-valkey-primary-0
kubectl delete pvc valkey-data-valkey-replicas-0
kubectl delete pvc valkey-data-valkey-replicas-1
kubectl delete pvc valkey-data-valkey-replicas-2

#helm uninstall valkey