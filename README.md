# Cache

## Usage

```java
// Configuration setup
final Configuration configuration = Configuration.builder()
    .cleanupInterval(Duration.ofSeconds(1))
    .maxSize(1_000)
    .retentionDuration(Duration.ofMinutes(1))
    .build();

// Cache creation
final Cache<Chicken> cache = CacheFactory.createInMemoryCache(configuration);

// Create data to cache
final Chicken riri = new Chicken("Riri", "Roux", LocalDate.of(2023, 10, 6).minus(15, ChronoUnit.DAYS));
final Chicken fifi = new Chicken("Fifi", "Blanc", LocalDate.of(2023, 10, 6).minus(15, ChronoUnit.DAYS));
final Chicken louou = new Chicken("Loulou", "Noir", LocalDate.of(2023, 10, 6).minus(15, ChronoUnit.DAYS));

// Cache the data and retrieve the associated keys.
final Key ririKey = cache.put(riri);
final Key fifiKey = cache.put(fifi);
final Key loulouKey = cache.put(louou);

// Delete a data from the cache
cache.delete(loulouKey);

Optional<Chicken> missingChicken = cache.get(loulouKey);
Optional<Chicken> existingChicken = cache.get(ririKey);
```