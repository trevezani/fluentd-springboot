# springboot-fluentd

The full example is present in [microservices-tutorial](https://github.com/trevezani/microservices-tutorial)

***

## Building and Running

* building:
```
mvn clean package -f api-zipcode
mvn docker:build -f api-zipcode/api-zipcode-infraestructure
```
* running:
```
docker-compose -f compose/docker-compose.yml up
```

Links: [[Elastick Search]](http://localhost:9200) [[Kibana]](http://localhost:5601)

Once running, you can call:
```
curl http://localhost:1401/zipcode/37188
```
* checking the memory
```
docker stats $(docker ps --format={{.Names}})
```

***

## Results

<img src="docs/images/kibana_logs.png" alt="kibana_logs.png">
<img src="docs/images/kibana_discover.png" alt="kibana_discover.png">
<img src="docs/images/console_curl.png" alt="console_curl.png">
<img src="docs/images/console_stats.png" alt="console_stats.png">

***

## Building and Running (Kubernetes mode)

For this test is necessary has a docker registry with login and password set to admin:admin and a minikube installed. 

* building:
```
mvn clean package -f api-zipcode

mvn -Ddocker.registry=localhost:5000 -Ddocker.username=admin -Ddocker.password=admin docker:build docker:push -f api-zipcode/api-zipcode-infraestructure
```
* preparing the environment in the kubernetes:

In the file `deployment.yaml` the image needs be change

```
kubectl create -f kubernates/namespace.json

kubectl create secret docker-registry service-registry --namespace=census --docker-server=$(ipconfig getifaddr en0):5000 --docker-username=admin --docker-password=admin

kubectl apply -f kubernetes/deployment.yaml
kubectl apply -f kubernetes/service.yaml
kubectl apply -f kubernetes/ingress.yaml

kubectl create namespace logging

kubectl apply -f kubernetes/elastic.yaml
kubectl apply -f kubernetes/kibana.yaml
```

Two ways to transport the log to the elasticsearch: Fluentd and Filebeat. Choose one.

```
kubectl apply -f kubernetes/fluentd.yaml
```

```
kubectl apply -f kubernetes/filebeat.yaml
```


Link: [[Kibana]](http://logging.trevezani.com.br/)

Once running, you can call:
```
curl http://census.trevezani.com.br/zipcode/37188
```
