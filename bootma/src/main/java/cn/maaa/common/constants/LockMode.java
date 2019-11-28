package cn.maaa.common.constants;

/**
 * @author :  mazh
 * @date :  2019/11/26 17:32
 */
/**
 * 锁的模式
 */
public enum  LockMode {
    //可重入锁
    REENTRANT,
    //公平锁
    FAIR,
    //联锁
    MULTIPLE,
    //红锁
    REDLOCK,
    //读锁
    READ,
    //写锁
    WRITE,
    //自动
    AUTO
}
