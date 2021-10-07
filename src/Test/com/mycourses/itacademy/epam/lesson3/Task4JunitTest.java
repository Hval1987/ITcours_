package Test.com.mycourses.itacademy.epam.lesson3;

import com.mycourses.itacademy.epam.lesson3.Task4Junit;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public  class Task4JunitTest {

    @Test
    public void test_arrayCompress1() {
        int arr[]={1,2,3,4,5,6,7,8,9};
        int [] expectedArr={1,0,3,0,5,0,7,0,9};
        Assert.assertArrayEquals(expectedArr, Task4Junit.arrayCompress(arr));
    }
    @Test
    public void test_arrayCompress2() {
        int arr[]={0,1};
        int [] expectedArr={0,0};
        Assert.assertArrayEquals(expectedArr, Task4Junit.arrayCompress(arr));
    }
    @Test
    public void test_arrayCompress_verifySize() {
        int arr[]={0,1,2,3,4};
        int  expected_size=5;
        Assert.assertEquals(expected_size,Task4Junit.arrayCompress(arr).length);
    }
}

