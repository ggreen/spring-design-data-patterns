helm install valkey-premium oci://registry-1.docker.io/bitnamicharts/valkey --set auth.enabled=false --set architecture=standalone


#helm uninstall valkey