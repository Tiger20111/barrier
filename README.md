Если рассматривать два барьера, то можем заметить:
Тест при 6 потоках: TestAndSet(68591000) и Array(5797552) наносекунд

По моему мнению при очень высокой нагрузке TestAndSet барьер будет однопоточным и будет работать медленнее, чем работа Array барьера.
Но вот задержка времени при Array происходит из-за того, что потоки взаимосвязаны. Дополнительное время из-за зависимости каждой пары потоков.
То есть при маленьком числе потоков должно быть дольше. 

При работе с кэш памятью идут проблемы в Array. Каждое обновление данных меняет кэши в каждом потоке, что грузит проц сильнее, чем TestAndSet дополнительно.


