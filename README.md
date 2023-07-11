# WebClient-Perf

## Run on AWS

Install [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html) and [kubectl](https://eksctl.io/introduction/#installation)

From the root project directory, run the below:

```shell
eksctl create cluster -r eu-west-2 -n webclientperf -N 1
for f in k8s/*-svc.yml; do kubectl apply -f $f; done
for f in k8s/*-deployment.yml; do kubectl apply -f $f; done
kubectl get service
```

From the last command, taken note of the external IP and visit http://${EXTERNAL_IP}/client/retrieve