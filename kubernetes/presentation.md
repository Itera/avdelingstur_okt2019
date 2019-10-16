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