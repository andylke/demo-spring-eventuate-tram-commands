
`docker compose up -d`

```
[+] Running 32/34 Pull complete                                                  93.2s 
 - eventuate-cdc-service Pulled                                                  90.7s 
   - 8559a31e96f4 Pull complete                                                  57.9s 
   - 65306eca6b8e Pull complete                                                  60.3s 
   - b8f37a6e9e9b Pull complete                                                  60.8s 
   - 33231475bae0 Pull complete                                                  78.0s 
   - 8e8eb6898b63 Pull complete                                                  78.9s
   - f18e12ae0732 Pull complete                                                  84.9s 
 - mysql Pulled                                                                  99.5s 
   - a4b007099961 Pull complete                                                  66.5s 
   - e2b610d88fd9 Pull complete                                                  68.0s 
   - 38567843b438 Pull complete                                                  69.4s 
   - 5fc423bf9558 Pull complete                                                  70.1s 
   - aa8241dfe828 Pull complete                                                  70.5s 
   - cc662311610e Pull complete                                                  74.7s 
   - 9832d1192cf2 Pull complete                                                  75.1s 
   - 3f242378e320 Pull complete                                                  75.4s 
   - cc65503c0186 Pull complete                                                  92.0s 
   - ce8944d50437 Pull complete                                                  92.3s 
   - 597d59a9a424 Pull complete                                                  92.5s 
 - kafka Pulling                                                                106.0s 
   - 16b78ed2e822 Pull complete                                                  17.4s 
   - e7233e20a08e Pull complete                                                  92.4s 
   - 3a498372ace6 Pull complete                                                  95.3s 
   - 45f1197956c6 Pull complete                                                 100.2s 
   - 1dcef6fb6396 Pull complete                                                 100.4s 
 - zookeeper Pulling                                                            106.0s 
   - 131f1a26eef0 Pull complete                                                  17.0s 
   - 9b9cbc4e490c Pull complete                                                  93.0s 
[+] Running 34/34 Pull complete                                                  93.2s 
 - eventuate-cdc-service Pulled                                                  90.7s 
   - 8559a31e96f4 Pull complete                                                  57.9s 
   - 65306eca6b8e Pull complete                                                  60.3s 
 - Network demo-spring-boot-eventuate-tram-events_default  Created                0.1s
 - Container mysql                                         Started                2.6s
 - Container zookeeper                                     Started                2.5s
 - Container kafka                                         Started                2.9s
 - Container eventuate-cdc-service                         Started                4.0s
```

`docker compose down`

```
[+] Running 5/5
 - Container eventuate-cdc-service                         Removed               11.0s 
 - Container mysql                                         Removed               10.9s
 - Container kafka                                         Removed                6.3s 
 - Container zookeeper                                     Removed                1.2s
 - Network demo-spring-boot-eventuate-tram-events_default  Removed                0.3s
```


`docker image prune --all --force`

```
Deleted Images:
untagged: eventuateio/eventuate-cdc-service:0.12.0.RELEASE
untagged: eventuateio/eventuate-cdc-service@sha256:0bb2657556e68953f50ce9b67ea88ab959efef90fdc1f8f2c1db91a29c616e7e
deleted: sha256:cf8ff3178c45b5bd62d9e46147d5444c7716655cfa4e5164f883776b26111a3d
deleted: sha256:fa120d342e95c1e6723ad03639b835f6c78353fdbcdd751aa91edef7983d742c
deleted: sha256:8194a0f161d54e315aa8fc947ab1c4732c98720a4d815d8f3df578981b766f29
deleted: sha256:7f9ff1c804e9a64904548cf1f76b235c0525c16968ea0a8aef6cb273890495f8
deleted: sha256:aa2da704309500fa4bf99a10d3a21fffbe34e4417d9abd3e8cbbfaa1c8680e92
deleted: sha256:093b2e6fd07228b0ce4dcffdcd1bd5cd5cff4758e7a92f9ef00002c38593a550
deleted: sha256:f43f525e5700c371aca941af470de540c77e9f668d5357a31b9d01e9d3d1e825
deleted: sha256:4e7bd47e4668c217f6eeba3b515d55d485e069ec297e0d1f5b0d643eb0478c72

Total reclaimed space: 1.693GB
```

Start MySQL with 
1. log-bin enabled 
2. init-script to create eventuate database & tables

```
  mysql:
    container_name: mysql
    image: mysql:5.7
    restart: always
    command: --server-id=1
      --log-bin=mysql-bin
    environment:
      MYSQL_USER: mysqluser
      MYSQL_PASSWORD: mysqluserpassword
      MYSQL_ROOT_PASSWORD: mysqlrootpassword
    ports:
      - '3306:3306'
    volumes:
      - ./init-script.sql:/docker-entrypoint-initdb.d/init-script.sql
```



`curl -X POST --header "Content-Type: application/json" -d '{ "customerId": "1", "transactionAmount": 111.11 }' http://localhost:8080/transactions`

```
{"transactionId":1,"customerId":1,"transactionAmount":111.11}
```




