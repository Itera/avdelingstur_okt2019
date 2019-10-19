# Kubernetes

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=0)

---

## Recap

* First we have an app

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Recap

* First we have an app
* Then we dockerize it

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Recap

* First we have an app
* Then we dockerize it
* Now we need to orchestrate it

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Overview

* Concepts
* Simple static site
* Exercises
  * Single pod networking
  * Multi pod networking

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Labels

```yaml
metadata:
  labels: 
    key1: value1
    key2: value2
```

---

## Selectors

```yaml
selector:
  matchLabels:
    component: redis
  matchExpressions:
    - {key: tier, operator: In, values: [cache]}
    - {key: environment, operator: NotIn, values: [dev]}
```

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Pod

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: pod-demo
spec:
  containers:
  - name: demo-container
    image: itera/demo
```

---

## Replica Set

```yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: demo-rs
  labels:
    app: demo
spec:
  replicas: 3
  selector:
    matchLabels:
      app: demo
  template:
    metadata:
      labels:
        app: demo
    spec:
      containers:
      - name: demo-container
        image: itera/demo
```

---

## Deployment

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo-deploy
  labels:
    application: demo
spec:
  replicas: 3
  selector:
    matchLabels:
      application: demo
  template:
    metadata:
      labels:
        application: demo
    spec:
      containers:
        - name: demo
          image: itera/demo
          ports:
            - containerPort: 80
```

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Services

* ClusterIP
  * cluster internal IP
* NodePort
  * nodes external IP on a static port
* LoadBalancer
  * external using the platforms load balancer

---

## Ingress

```yaml
apiVersion: networking.k8s.io/v1beta1
kind: Ingress
metadata:
  name: demo-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - http:
        paths:
          - path: /demo
            backend:
              serviceName: demo-service
              servicePort: 80
```

---

## Ingress Controllers

> Kubernetes supports a high level abstraction called **Ingress**, which allows simple host or URL based HTTP routing. An **ingress** is a core concept (in beta) of Kubernetes, but is always implemented by a third party proxy. These implementations are known as **ingress controllers**
-- getambassador.io[^1]

[^1]: https://blog.getambassador.io/kubernetes-ingress-nodeport-load-balancers-and-ingress-controllers-6e29f1c44f2d

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Namespaces

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: demo-dev
  labels:
    environment: development
    application: demo

```

---

## Networking

* Between containers in a pod
  * localhost:port
* Between pods
  * service:port

---

## Simple Static Site

* On-screen run through

---

## Scaling

* Manual scaling
* Auto scaling (HPA)
* Auto scaling (custom metrics)

^ Horizontal Pod Autoscaler

^ Stack Driver / Prometheus etc

^ Target CPU - or custom metric name with target value or target average value

---

## Exercises

* Simple (single) pod networking
* Multi pod networking

---

## Config Maps

```shell
kubectl create configmap demo-config \
  --from-literal=demo.key=demo_value
```

---

## Secrets Maps

```shell
kubectl create secret generic demo-secret \
  --from-literal=username=demouser \
  --from-literal=password='demopass'
```

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)

---

## Stateful

* Volumes on k8s
* Call to other cloud services

---

## Cloud Ingress

* Each platform has its own ingress controller setup
* GKE Ingress Controller
  * GCP HTTP(S) Load Balancer
* AWS EKS Ingress Controller
  * AWS Application Load Balancer
* Azure AKS Ingress Controller
  *  HTTP application routing + Nginx Ingress Controller

---

## Istio

* The term service mesh is used to describe the network of microservices that make up such applications and the interactions between them.

---

## Istio

* Automatic load balancing for HTTP, gRPC, WebSocket, and TCP traffic
* Fine-grained control of traffic behavior (routing rules, retries, failovers, fault injection)
* A pluggable policy layer / configuration API (access controls, rate limits, quotas)
* Automatic metrics, logs, and traces for all traffic in a cluster
* Secure service-to-service communication in a cluster with strong identity-based authentication and authorization.

---

## The Children's Illustrated Guide to Kubernetes

https://www.cncf.io/the-childrens-illustrated-guide-to-kubernetes/
https://www.youtube.com/watch?v=3I9PkvZ80BQ

---

![](https://www.youtube.com/watch?v=3I9PkvZ80BQ?t=)