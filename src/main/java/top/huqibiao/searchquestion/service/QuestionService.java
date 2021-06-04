package top.huqibiao.searchquestion.service;

import top.huqibiao.searchquestion.pojo.Question;

import java.util.List;

public interface QuestionService {
    Integer insertOneQuestion(String question, String answer);

    List<Question> queryAnswersService(String question);

    void batchProcessing(String[] data);
}
