package csmiles.webclientperf;

import csmiles.webclientperf.client.ClientApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(
		classes = ClientApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {
				"SERVER_SVC_SERVICE_HOST=localhost",
				"SERVER_SVC_SERVICE_PORT=8080"
		})
class ClientApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void perfTest() {
		int n = 8_000;
		doPerfTest("/client/pooled", n);
		doPerfTest("/client/unpooled", n);
	}

	private void doPerfTest(String urlPath, int n) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			restTemplate.getForObject(urlPath, String.class);
		}
		long end = System.currentTimeMillis();
		long duration = end - start;
		double avg = duration * 1.0 / n;

		System.out.printf("--- Perf results for %s: Total duration=%d, Avg. time=%f%n", urlPath, duration, avg);
	}

}
