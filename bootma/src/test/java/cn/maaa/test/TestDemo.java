package cn.maaa.test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.maaa.Application;

@RunWith(SpringJUnit4ClassRunner.class) // 指定运行的主类
@SpringBootTest(classes=Application.class) // 指定SpringBoot启动类
public class TestDemo {
}
