package cn.maaa.test.thread;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/4/12 11:40
 */
public class ThreadLocalTest {
    private List<String> messages = Lists.newArrayList();

    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        ThreadLocalTest threadLocalTest = holder.get();  //不为空，会调用java.lang.ThreadLocal.SuppliedThreadLocal.initialValue
        System.out.println(threadLocalTest);

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalTest.add("一枝花算不算浪漫");
        System.out.println(holder.get().messages);
        ThreadLocalTest.clear();
    }
}
