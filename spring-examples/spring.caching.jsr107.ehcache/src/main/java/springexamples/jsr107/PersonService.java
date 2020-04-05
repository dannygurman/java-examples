package springexamples.jsr107;

import java.util.Date;
import static java.util.concurrent.TimeUnit.SECONDS;
import javax.cache.CacheManager;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

/**
 *
 * @author GGIB
 */

@Component
//Allows the configuration of defaults for CacheResult, CachePut, CacheRemove, and CacheRemoveAll at the class level.
// Without the method level annotations this annotation has no effect.
@CacheDefaults(cacheName = "people")
public class PersonService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    //create cache
    @Component
    public static class CachingSetup implements JCacheManagerCustomizer
    {
      @Override
      public void customize(CacheManager cacheManager)
      {
        cacheManager.createCache("people", new MutableConfiguration<>()
          .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 10)))
          .setStoreByValue(false)
          .setStatisticsEnabled(true));
      }
    }


    //When a method annotated with CacheResult is invoked a GeneratedCacheKey will be generated and
    // Cache.get(Object) is called before the annotated method actually executes.
    // If a value is found in the cache it is returned and the annotated method is never actually executed.
    // If no value is found the annotated method is invoked and the returned value is stored in the cache with the generated key.
    @CacheResult
    public Person getPerson(int ssn)
    {
        LOGGER.info("ssn " + ssn + " not found in cache. TimeStamp: {}", new Date());

        switch (ssn)
        {
            case 123456789:
                return new Person(ssn, "Geoff", "Gibson");
            case 987654321:
                return new Person(ssn, "Cory", "Beck");
            default:
                return new Person(ssn,"John","Doe");
        }
    }


}
