# WebClient-Perf

## Performance Results

ClientApplicationTests::perfTest compares the performance of a pooled and unpooled netty HttpClient.

For N=8,000, the results are:

--- Perf results for /client/pooled: Total duration=30318, Avg. time=3.789750
--- Perf results for /client/unpooled: Total duration=18800, Avg. time=2.350000

Showing a difference of about 1.5ms

For N=1, the results are:

--- Perf results for /client/pooled: Total duration=547, Avg. time=547.000000
--- Perf results for /client/unpooled: Total duration=22, Avg. time=22.000000

Showing a difference of about 525ms or about half a second.

The results do show that a pooled connection is slower (at least for its first call) but does not account for a 10 
seconds timeout.

To run the performance test:
1. Run the ServerApplication's main method and make sure it is accessible from http://localhost:8080/server/success
2. Run the unit test: ClientApplicationTest::perfTest

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