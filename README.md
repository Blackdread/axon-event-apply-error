Repo for https://github.com/AxonFramework/extension-kotlin/issues/286

```bash
docker run -d --name axonserver -p 8024:8024 -p 8124:8124 -e AXONIQ_AXONSERVER_DEVMODE_ENABLED=true axoniq/axonserver
docker run -d --name postgres-axon -p 5432:5432 -e POSTGRES_PASSWORD=password postgres
```

