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

copy files to the app directory. Create an input directory in hadoop an copy the `tmdb24.csv` file to the input directory

```bash
hadoop fs -mkdir /input_dir
hadoop fs -put ./app/tmdb24.csv /input_dir
```

execute the jar with hadoop. The next command is an exmple with the `ParcialC1.jar`

```bash
hadoop jar ./app/ParcialC1.jar /input_dir /temp_dir /output_dir
```

get the output as a txt file

```bash
hadoop fs -cat /output_dir/* > ./app/output1.txt
```

## Comandos utiles

Get into docker container shell

```bash
sudo docker exec -it container-name bash
```

create directory in hadoop file system

```bash
hadoop fs -mkdir /name_dir
```

copy file to haddop file system directory

```bash
hadoop fs -put ./app/file.name /name_dest_dir
```

execute jar with hadoop

```bash
hadoop jar ./app/name.jar /name_1_dir /name_2_dir /name_3_dir ...
```

get output as a txt file

```bash
hadoop fs -cat /output_dir/* > ./app/output_file.txt
```

Copy file from host to docker container

```bash
docker cp /path/to/source/directory container-name:/path/to/destination
```

Copy file from docker container to host

```bash
docker cp container-name:/file/path/within/container /host/path/target
```
