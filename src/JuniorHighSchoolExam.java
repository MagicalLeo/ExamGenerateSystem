import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JuniorHighSchoolExam implements GenerateExam {

  /**
   * 生成初中问题
   *
   * @param questions 问题数
   * @param user      用来生成用户的活页夹
   * @throws IOException
   */
  @Override
  public void generateQuestion(int questions, User user) throws IOException {
    String[] operators = {"+", "-", "*", "÷"};
    int operator = 0;
    String[] questionList = new String[questions];
    for (int i = 0; i < questions; i++) {
      Random r = new Random();
      StringBuilder question = new StringBuilder(); //问题
      operator = r.nextInt(5) + 1;// 操作数为 1 + (0 ~ 4)
      question.append(Integer.toString(i + 1)).append("."); //加上题号
      generateSingleQuestion(operators, operator, question);
      if (checkCorrespond(user, question)) {//查重成功
        questionList[i] = String.valueOf(question); //将问题加入list
      } else {
        i--;//重新生成问题
        continue;
      }
    }

    generateFile(questionList, user);

  }

  private void getOperatingNumber(StringBuilder question, Random r, int j, int operator,
      int specialOperatorLocation) {
    //生成操作数
    String[] specialOperators = {"√", "²"};
    int number;
    number = r.nextInt(99) + 1;//1~100
    if (j == specialOperatorLocation || r.nextInt(operator * 4) == 0) {
      if (r.nextInt(2) == 0) {
        question.append(specialOperators[0]);
        question.append(String.valueOf(number));
      } else {
        question.append(String.valueOf(number));
        question.append(specialOperators[1]);
      }
    } else {
      question.append(String.valueOf(number));
    }
  }

  private void generateSingleQuestion(String[] operators, int operator,
      StringBuilder question) {
    Random r = new Random();
    String sign;
    int bracket = 0, nextBracket = 0, leftBracket = 0; //出现的左右括号个数
    int specialOperatorLocation = r.nextInt(operator);
    for (int j = 0; j < operator; j++) { //生成题目
      if (r.nextInt(operator * 2) == 0 && bracket == 0
          && j != operator - 1) { //1/2n的机率出现左括号且不能出现在最右边
        question.append("(");
        leftBracket = j;
        bracket++;
        nextBracket = r.nextInt(operator - -j - 1) + 1;
        if ((leftBracket + nextBracket) > operator - 1) {
          nextBracket = 1;
        }
      }
      getOperatingNumber(question, r, j, operator, specialOperatorLocation);//将问题的操作数加入

      if (bracket == 1 && j == leftBracket + nextBracket) {
        question.append(")");
        bracket++;
      }
      //最后一位操作数后不会有操作符
      if (j == operator - 1) {
        return;
      }
      //生成操作符
      sign = operators[r.nextInt(4)]; // 0 ~ 3
      question.append(String.valueOf(sign));
    }
  }
}

