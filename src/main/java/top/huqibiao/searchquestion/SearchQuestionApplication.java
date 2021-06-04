package top.huqibiao.searchquestion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.huqibiao.searchquestion.dao")
public class SearchQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchQuestionApplication.class, args);
    }

}
