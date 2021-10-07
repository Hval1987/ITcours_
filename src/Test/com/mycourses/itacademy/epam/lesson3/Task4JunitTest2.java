package Test.com.mycourses.itacademy.epam.lesson3;

import com.mycourses.itacademy.epam.lesson3.Task4Junit;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public  class Task4JunitTest2 {
    @Test
    public void test_createMatrix01() {
        int arr[]={0,0,0,0};
        int [][] expectedArr={{0,0,0,0},
                              {0,0,0,0},
                              {0,0,0,0},
                              {0,0,0,0}};

        Assert.assertArrayEquals("найдены ошибки",expectedArr, Task4Junit.createMatrix(arr));
    }
    @Test
    public void test_createMatrix02() {
        int arr[]={1,2,3,4};
        int [][] expectedArr={{1,2, 3, 4},
                              {1,4, 9, 16},
                              {1,8, 27,64},
                              {1,16,81,256}};

        Assert.assertArrayEquals("найдены ошибки",expectedArr, Task4Junit.createMatrix(arr));
    }


}
