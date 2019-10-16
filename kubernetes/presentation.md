# Kubernetes

---

## Why Kubernetes

---

## Concepts

We'll work through the concepts by looking at the simple static site example.

---

## Pod

---

## Deployment

---

## Services

* ClusterIP
* NodePort
* LoadBalancer
* (ExternalName)

---

## Namespaces

---

## Labels

---

## Ingress

* Ingress Controllers

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

## Networking

* Within Pod - simple-pod-networking
* Between Pods - multi-pod-networking

---

## Scaling

* Manual scaling
* Auto scaling (HPA)
* Auto scaling (custom metrics)

---

## Cloud Ingress

* Each platform has its own ingress controller setup

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
