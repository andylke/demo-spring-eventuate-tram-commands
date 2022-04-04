
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



`curl -X POST --header "Content-Type: application/json" -d '{ "customerId": "1", "orderAmount": 111.11 }' http://localhost:8080/orders`

```
{"orderId":1,"customerId":1,"orderAmount":111.11}
```






