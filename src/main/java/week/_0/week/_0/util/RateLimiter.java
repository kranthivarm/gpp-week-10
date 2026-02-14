package week._0.week._0.util;

import java.util.concurrent.ConcurrentHashMap;
import java.time.Instant;

public class RateLimiter {

    private static final ConcurrentHashMap<String, Long> userLastSent =
            new ConcurrentHashMap<>();

    private static final long LIMIT_WINDOW = 5000; // 5 seconds

    public static boolean allow(String key) {

        long now = Instant.now().toEpochMilli();

        Long lastTime = userLastSent.get(key);

        if (lastTime == null || (now - lastTime) > LIMIT_WINDOW) {
            userLastSent.put(key, now);
            return true;
        }

        return false;
    }
}
