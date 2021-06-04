package top.huqibiao.searchquestion.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huqibiao.searchquestion.pojo.Question;
import top.huqibiao.searchquestion.service.QuestionService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
public class QuestionController {

    @Resource
    private QuestionService questionService;
    
    @GetMapping({"/message1"})
    public Object queryAnswer(String question) {
        List<Question> resultList = questionService.queryAnswersService(question);
        Question result;
        if (resultList.size() != 0 && resultList != null) {
            result = resultList.get(0);
            if (resultList.size() > 1) {
                result.setQuestion("含有该关键字的题目在数据库中不止一个");
                result.setAnswer("你可以试着换个长点的关键字再次进行搜索");
            }
        } else {
            result = new Question();
            result.setQuestion("没有找到这道题");
            result.setAnswer("没有找到该题答案,你可以在测试结束后，到新题添加页面,添加该题目和答案");

        }
        return result;
    }


    @PostMapping({"/uploadQuestion"})
    @CrossOrigin({"*"})
    public Integer uploadData(String question, String answer) {
        int state = questionService.insertOneQuestion(question, answer);
        return state;
    }


    @PostMapping({"/batch"})
    @CrossOrigin({"*"})
    public void batch(String questionAndAnswer) throws UnsupportedEncodingException {
        questionAndAnswer = URLDecoder.decode(questionAndAnswer, "UTF-8");
        String[] data = questionAndAnswer.trim().split("&&");
        questionService.batchProcessing(data);
    }

}
