package com.example.springboot;

import com.example.springboot.entity.User;
import com.example.springboot.utils.RedisUtils;
import lombok.Data;
import org.elasticsearch.common.UUIDs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testSet() {
        //User u1 = new User("kk", 17, "123@qq.com");
        TraceInfo info = new TraceInfo();
        info.setId("i1");
        info.setRecordId("r1");
        info.setUserId("000000005ec2389e015ec26f98e00097");
        info.setWgsLat(45023.13989576258D);
        info.setWgsLon(35902.15067037437D);
        redisUtils.set("wgypos:440104:000000005ec2389e015ec26f98e00097", info);

        info.setId("i2");
        info.setRecordId("r2");
        info.setUserId("000000005ec2c006015ec35e99330301");
        info.setWgsLat(45053.03787222519D);
        info.setWgsLon(35902.944421961874D);
        redisUtils.set("wgypos:440104:000000005ec2c006015ec35e99330301", info);


        info.setId("i3");
        info.setRecordId("r3");
        info.setUserId("000000005ec2c006015ec39ae97104b7");
        info.setWgsLat(43099.69921875D);
        info.setWgsLon(42963.38879394531D);
        redisUtils.set("wgypos:440104:000000005ec2c006015ec39ae97104b7", info);

    }

    @Test
    public void testGet() {
        User u = (User) redisUtils.get("kk");
        System.out.println(u);
    }


}

@Data
class TraceInfo {
    private String id;
    private String recordId;
    private String userId;
    private Double wgsLon; // x
    private Double wgsLat; // y
}
