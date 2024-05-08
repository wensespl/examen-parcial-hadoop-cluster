# examen-parcial-hadoop-cluster

## Iniciar hadoop cluster

start cluster containers

```bash
docker-compose up -d
```

get into hadoop-master container shell

```bash
sudo docker exec -it hadoop-master bash
```

start hadoop

```bash
./start-hadoop.sh
```

## Comandos utiles

Get into docker container shell

```bash
sudo docker exec -it container-name bash
```

Copy file from host to docker container

```bash
docker cp /path/to/source/directory container-name:/path/to/destination
```

Copy file from docker container to host

```bash
docker cp container-name:/file/path/within/container /host/path/target
```
