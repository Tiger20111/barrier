Если рассматривать два барьера, то можем заметить:
Тест при 8 потоках:

Num iter: 0
time to execute TestAndSet: 7903666
time to execute Array: 3716619
Time diff(T/A): 2.1265741793818522
Num iter: 1
time to execute TestAndSet: 3853205
time to execute Array: 3460381
Time diff(T/A): 1.113520447603891
Num iter: 2
time to execute TestAndSet: 4000360
time to execute Array: 3511237
Time diff(T/A): 1.1393021889436685

Тест при 6 потоках:
Num iter: 0
time to execute TestAndSet: 8296090
time to execute Array: 3147524
Time diff(T/A): 2.6357511491572425
Num iter: 1
time to execute TestAndSet: 2273098
time to execute Array: 2279517
Time diff(T/A): 0.9971840525865786
Num iter: 2
time to execute TestAndSet: 3412958
time to execute Array: 2725583
Time diff(T/A): 1.252193750841563
Num iter: 3
time to execute TestAndSet: 2579652
time to execute Array: 2648253
Time diff(T/A): 0.9740957529359922
Num iter: 4
time to execute TestAndSet: 2785716
time to execute Array: 2685939
Time diff(T/A): 1.0371479024653947

С при большом кол-ве потоков TaS получался быстрее. Это логично так, как при работе в Array поток один дважды тормозит все другие потоки. А при TaS лишь только, если он был последним. 

Первую же итерацию можно назвать разогревочной, потому, что потоки новые не всегда сразу создаются, а лениво для оптимизации, поэтому может отличаться время.

