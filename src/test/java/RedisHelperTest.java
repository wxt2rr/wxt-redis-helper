import com.wangxt.redis.helper.config.RedisConfig;
import com.wangxt.redis.helper.helper.Cache;
import com.wangxt.redis.helper.helper.RedisHelper;
import com.wangxt.redis.helper.pojo.RedisDBIdentity;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class RedisHelperTest {

    private RedisHelper redisHelper;

    @Before
    public void before(){
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setHost("");
        redisConfig.setPort(6379);
        redisConfig.setPasswd("");

        Set<Integer> assignedDbIndexes = new HashSet<>();
        assignedDbIndexes.add(1);

        redisHelper = new RedisHelper(redisConfig, assignedDbIndexes);
    }

    @AllArgsConstructor
    public enum RedisDb implements RedisDBIdentity{
        IDX1(1);

        private final Integer index;

        @Override
        public int getIndex() {
            return index;
        }
    }

    @Test
    public void test(){
        Cache cache = redisHelper.get(RedisDb.IDX1);

        String key = "wxt_test";
        String set = cache.set(key, "good");
        Assert.assertEquals(set, "ok");

        String s = cache.get(key);
        Assert.assertEquals(s, "good");
    }
}
