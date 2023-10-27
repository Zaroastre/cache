package io.nirahtech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import io.nirahtech.cache.Cache;
import io.nirahtech.cache.CacheFactory;
import io.nirahtech.cache.Key;
import io.nirahtech.cache.configuration.Configuration;

/**
 * Unit test for simple App.
 */
class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws InterruptedException
     */
    @Test
    void shouldAnswerWithTrue() throws InterruptedException
    {
        final Configuration configuration = Configuration.builder().cleanupInterval(Duration.ofSeconds(1)).build();
        final Cache<Chicken> cache = CacheFactory.createInMemoryCache(configuration);
        final Chicken riri = new Chicken("Riri", "Roux", LocalDate.of(2023, 10, 6).minus(15, ChronoUnit.DAYS));
        final Chicken fifi = new Chicken("Fifi", "Blanc", LocalDate.of(2023, 10, 6).minus(15, ChronoUnit.DAYS));
        final Chicken louou = new Chicken("Loulou", "Noir", LocalDate.of(2023, 10, 6).minus(15, ChronoUnit.DAYS));
        final Key ririKey = cache.put(riri);
        final Key fifiKey = cache.put(fifi);
        final Key loulouKey = cache.put(louou);
        assertEquals(riri, cache.get(ririKey).get());
        assertEquals(fifi, cache.get(fifiKey).get());
        assertEquals(louou, cache.get(loulouKey).get());
        cache.delete(loulouKey);
        assertTrue(cache.get(loulouKey).isEmpty());
    }

    private static final record Chicken (
        String name,
        String color,
        LocalDate bithDate
    ) {

    } 
}
