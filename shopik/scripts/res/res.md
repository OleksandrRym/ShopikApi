# Basic app
    █ TOTAL RESULTS

    checks_total.......: 186322 1639.030708/s
    checks_succeeded...: 98.16% 182908 out of 186322
    checks_failed......: 1.83%  3414 out of 186322

    ✗ status is 200
      ↳  98% — ✓ 91454 / ✗ 1707
    ✗ is json
      ↳  98% — ✓ 91454 / ✗ 1707

    CUSTOM
    custom_response_time_ms........: avg=3918.870858 min=0        med=3194  max=15889  p(90)=9821   p(95)=11092 

    HTTP
    http_req_duration..............: avg=3.91s       min=417µs    med=3.19s max=15.47s p(90)=9.82s  p(95)=11.09s
      { expected_response:true }...: avg=3.7s        min=417µs    med=3.1s  max=15.47s p(90)=9.61s  p(95)=10.56s
    http_req_failed................: 1.83% 1707 out of 93161
    http_reqs......................: 93161 819.515354/s

    EXECUTION
    iteration_duration.............: avg=5.14s       min=500.87ms med=4.6s  max=17.55s p(90)=11.16s p(95)=12.55s
    iterations.....................: 93161 819.515354/s
    vus............................: 101   min=10            max=10000
    vus_max........................: 10000 min=10000         max=10000

    NETWORK
    data_received..................: 20 MB 179 kB/s
    data_sent......................: 11 MB 100 kB/s

# Add Cache (Redis)


    █ TOTAL RESULTS

    checks_total.......: 214218 1883.820731/s
    checks_succeeded...: 98.40% 210804 out of 214218
    checks_failed......: 1.59%  3414 out of 214218

    ✗ status is 200
      ↳  98% — ✓ 105402 / ✗ 1707
    ✗ is json
      ↳  98% — ✓ 105402 / ✗ 1707

    CUSTOM
    custom_response_time_ms........: avg=3353.555163 min=0        med=1921  max=16081  p(90)=9177   p(95)=10191.6

    HTTP
    http_req_duration..............: avg=3.35s       min=466µs    med=1.92s max=15.06s p(90)=9.17s  p(95)=10.19s 
      { expected_response:true }...: avg=3.16s       min=466µs    med=1.88s max=14.89s p(90)=8.94s  p(95)=9.8s   
    http_req_failed................: 1.59%  1707 out of 107109
    http_reqs......................: 107109 941.910365/s

    EXECUTION
    iteration_duration.............: avg=4.56s       min=501.01ms med=3.17s max=17.87s p(90)=10.56s p(95)=11.84s 
    iterations.....................: 107109 941.910365/s
    vus............................: 101    min=11             max=10000
    vus_max........................: 10000  min=10000          max=10000

    NETWORK
    data_received..................: 23 MB  206 kB/s
    data_sent......................: 13 MB  115 kB/s
    running (1m53.7s), 00000/10000 VUs, 107109 complete and 101 interrupted iterations

# Add hikari pool + JVM commands into dockerfile

    █ TOTAL RESULTS

    checks_total.......: 245878 2161.525747/s
    checks_succeeded...: 98.61% 242472 out of 245878
    checks_failed......: 1.38%  3406 out of 245878

    ✗ status is 200
      ↳  98% — ✓ 121236 / ✗ 1703
    ✗ is json
      ↳  98% — ✓ 121236 / ✗ 1703

    CUSTOM
    custom_response_time_ms........: avg=2605.395001 min=0        med=2891  max=15789  p(90)=4972  p(95)=5887 

    HTTP
    http_req_duration..............: avg=2.6s        min=549µs    med=2.89s max=15.09s p(90)=4.97s p(95)=5.88s
      { expected_response:true }...: avg=2.42s       min=549µs    med=2.8s  max=7.78s  p(90)=4.9s  p(95)=5.78s
    http_req_failed................: 1.38%  1703 out of 122939
    http_reqs......................: 122939 1080.762874/s

    EXECUTION
    iteration_duration.............: avg=3.82s       min=500.89ms med=4.21s max=17.47s p(90)=6.47s p(95)=7.16s
    iterations.....................: 122939 1080.762874/s
    vus............................: 44     min=27             max=10000
    vus_max........................: 10000  min=10000          max=10000

    NETWORK
    data_received..................: 27 MB  237 kB/s
    data_sent......................: 15 MB  132 kB/s

