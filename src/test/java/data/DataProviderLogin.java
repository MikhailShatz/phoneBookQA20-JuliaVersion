package data;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DataProviderLogin {
    @DataProvider
    public Iterator<Object[]> positiveDataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aa$")
                .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> negativePasswordLoginDataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aaa")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aaa")
                        .build()
        });
        return list.iterator();
    }
}
