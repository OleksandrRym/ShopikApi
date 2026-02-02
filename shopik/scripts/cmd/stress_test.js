import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend } from 'k6/metrics';

const responseTimeTrend = new Trend('custom_response_time_ms');

export const options = {
    stages: [
        { duration: '20s', target: 1000 },
        { duration: '1m', target: 10000 },
        { duration: '20s', target: 0 },
    ],
    thresholds: {
        'http_req_duration': ['p(95)<500'],
        'http_req_failed': ['rate<0.01'],
    },
};

const PRODUCT_ID = 'ff3ef779-f062-4eeb-8441-b48cabe0f815';
const BASE_URL = `http://localhost:8080/api/v1/products/${PRODUCT_ID}`;

export default function () {
    const startTime = Date.now();
    const res = http.get(BASE_URL);
    const endTime = Date.now();

    const duration = endTime - startTime;

    responseTimeTrend.add(duration);

    if (duration > 200 || res.status !== 200) {
        console.log(`Request took ${duration}ms. Status: ${res.status}`);
    }

    check(res, {
        'status is 200': (r) => r.status === 200,
        'is json': (r) => r.headers['Content-Type'] && r.headers['Content-Type'].includes('application/json'),
    });

    sleep(Math.random() * 1 + 0.5);
}