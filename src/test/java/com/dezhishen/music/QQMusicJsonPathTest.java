package com.dezhishen.music;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QQMusicJsonPathTest {

    @Test
    public void test() {
        String res = "{\"data\":{\"004Z8Ihr0JIu5s\":\"http://ws.stream.qqmusic.qq.com/C4000012Ez0a1tFcOI.m4a?guid=2796982635&vkey=DB0E16676C581AE2E762FCA5D32FBB52454EA229C6BD58D9BA2976BA7CC3E49532EA299860E0F219148005848530335ABC254EC7A3374F80&uin=1899&fromtag=66\"},\"result\":100}";
        Object root = JsonPath.read(res, "$.data.*");
        String url = JsonPath.read(root, "$[0]");
        assert url.equals("http://ws.stream.qqmusic.qq.com/C4000012Ez0a1tFcOI.m4a?guid=2796982635&vkey=DB0E16676C581AE2E762FCA5D32FBB52454EA229C6BD58D9BA2976BA7CC3E49532EA299860E0F219148005848530335ABC254EC7A3374F80&uin=1899&fromtag=66");
    }
}
