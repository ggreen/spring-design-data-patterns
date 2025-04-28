helm install valkey-premium oci://registry-1.docker.io/bitnamicharts/valkey --set auth.enabled=false --set architecture=standalone --set primary.service.type=LoadBalancer

#helm uninstall valkey