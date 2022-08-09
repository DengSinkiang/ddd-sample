package com.sinkiang.ddd.sample;

import com.alibaba.fastjson.JSON;
import com.sinkiang.ddd.sample.adaptor.page.dto.request.UserReqDTO;
import com.sinkiang.ddd.sample.bootstrap.DddSampleApplication;
import com.sinkiang.ddd.sample.common.page.PageRequest;
import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author dengxj
 * @date 2022/7/20 17:16
 */
@SpringBootTest(classes = DddSampleApplication.class)
public class UserTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setUsername("dengxinjiang");
        user.setAge(27);
        user.setEmail("dengxj@qq.com");
        user.setAddress("杭州市");
        userRepository.createUser(user);
    }

    @Test
    void testUser() {
        PageRequest<UserReqDTO> pageRequest = new PageRequest<>();
        pageRequest.setCurrent(1L);
        pageRequest.setPageSize(10L);
        UserReqDTO userReqDTO = new UserReqDTO();
        userReqDTO.setUsername("dengxinjiang");
        pageRequest.setData(userReqDTO);
        System.out.println(JSON.toJSONString(pageRequest));
    }

    /**
     * [11,5,6,7,10,13,20] 二分查找，找到 6
     */
    @Test
    void testRecursionBinarySearch() {
        int[] a = new int[]{11, 5, 6, 7, 10, 13, 20};
        int[] ints = quickSort(a);
        int i = recursionBinarySearch(ints, 6, 0, a.length - 1);
        System.out.println(i);
    }

    /**
     * 二分查找
     *
     * @param arrays
     * @param target
     * @param low
     * @param high
     * @return
     */
    public static int recursionBinarySearch(int[] arrays, int target, int low, int high) {
        if (target < arrays[low] || target > arrays[high] || low > high) {
            return -1;
        }
        int middle = (low + high) / 2; //初始化中间位置的值
        if (arrays[middle] > target) {
            return recursionBinarySearch(arrays, target, low, middle - 1);
        } else if (arrays[middle] < target) {
            return recursionBinarySearch(arrays, target, middle + 1, high);
        } else {
            return middle;
        }
    }

    /**
     * 快速排序算法
     *
     * @param n 待排序的数组
     * @return
     */
    public static int[] quickSort(int[] n) {
        if (isEmpty(n))
            return n;
        quickSort(n, 0, n.length - 1);
        return n;
    }

    private static void quickSort(int[] n, int l, int h) {
        if (isEmpty(n))
            return;
        if (l < h) {
            int pivot = partition(n, l, h);
            quickSort(n, l, pivot - 1);
            quickSort(n, pivot + 1, h);
        }
    }

    private static boolean isEmpty(int[] n) {
        return n == null || n.length == 0;
    }

    private static int partition(int[] n, int start, int end) {
        int tmp = n[start];
        while (start < end) {
            while (n[end] >= tmp && start < end)
                end--;
            if (start < end) {
                n[start++] = n[end];
            }
            while (n[start] < tmp && start < end)
                start++;
            if (start < end) {
                n[end--] = n[start];
            }
        }
        n[start] = tmp;
        return start;
    }

    @Test
    public void test22222() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        }, asyncTaskExecutor).thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });

        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> {
                    int number = new Random().nextInt(10);
                    System.out.println("任务1结果：" + number);
                    return number;
                });
        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> {
                    int number = new Random().nextInt(10);
                    System.out.println("任务2结果：" + number);
                    return number;
                });
        CompletableFuture<Integer> result = future1
                .thenCombine(future2, Integer::sum);
        System.out.println("组合后结果：" + result.join());

        CompletableFuture<String> future11 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future11完成");
            return "future11完成";
        });

        CompletableFuture<String> future22 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future22完成");
            return "future22完成";
        });

        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future11, future22);
        combindFuture.join();

    }


}
