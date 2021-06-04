package top.huqibiao.searchquestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huqibiao.searchquestion.dao.QuestionMapper;
import top.huqibiao.searchquestion.pojo.Question;
import top.huqibiao.searchquestion.service.QuestionService;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    @Transactional
    public Integer insertOneQuestion(String question, String answer) {
        question = question.trim(); answer = answer.trim();
        int i = questionMapper.insertAnswer(question, answer);
        return i ;
    }

    @Override
    public List<Question> queryAnswersService(String question) {
        List<Question> resultList = questionMapper.queryByQuestion(question);
        return resultList;
    }

    @Override
    @Transactional
    public void batchProcessing(String[] data) {
        int i = 0;
        for(int j = 1; i < data.length; j += 2) {
            questionMapper.insertAnswer(data[i], data[j]);
            i += 2;
        }
    }

}
