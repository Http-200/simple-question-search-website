package top.huqibiao.searchquestion.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import top.huqibiao.searchquestion.pojo.Question;

import java.util.List;

public interface QuestionMapper {

    @Insert("insert ignore into Innovation values (#{question}, #{answer})")
    Integer insertAnswer(String question, String answer);

    @Select("select question, answer from Innovation where question like concat('%', #{question}, '%')")
    List<Question> queryByQuestion(String question);
}
