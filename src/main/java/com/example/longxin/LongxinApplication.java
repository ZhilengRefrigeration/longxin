package com.example.longxin;
import com.example.longxin.cache.Conversion_Rate.Conversion_Rate_Cache_Update;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LongxinApplication {

   public static void main(String[] args) {
        SpringApplication.run(LongxinApplication.class, args);
       Thread cacheUpdaterThread = new Thread(new Conversion_Rate_Cache_Update());
       cacheUpdaterThread.setName("CacheUpdateThread");
       cacheUpdaterThread.start();
    }
}
