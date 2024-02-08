import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HighSchoolExam implements GenerateExam {

  /**
   * 生成高中问题.
   *
   * @param questions 问题数
   * @param user      用来生成用户的活页夹
   * @throws IOException
   */
  @Override
  public void generateQuestion(int questions, User user) throws IOException {
    String[] questionList = new String[questions];
    String[] operators = {"+", "-", "*", "÷"};
    String sign;
    int operator = 0;
    for (int i = 0; i < questions; i++) {
      Random r = new Random();
      StringBuilder question = new StringBuilder(); //问题
      operator = r.nextInt(5) + 1;// 操作数为 1 + (0 ~ 4)
      question.append(Integer.toString(i + 1)).append("."); //加上题号
      int bracket = 0, nextBracket = 0, leftBracket = 0; //出现的左右括号个数
      int trigonometricLocation = r.nextInt(operator);
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
        getOperatingNumber(question, r, j, operator, trigonometricLocation);//将问题的操作数加入

        if (bracket == 1 && j == leftBracket + nextBracket) {
          question.append(")");
          bracket++;
        }
        //最后一位操作数后不会有操作符
        if (j == operator - 1) {
          break;
        }
        //生成操作符
        sign = operators[r.nextInt(4)]; // 0 ~ 3
        question.append(String.valueOf(sign));
      }
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
      int trigonometricLocation) {
    //生成操作数
    String[] specialOperators = {"√", "²"};
    String[] trigonometricMethod = {"sin", "cos", "tan"};
    int number;
    number = r.nextInt(99) + 1;//1~100
    if (j == trigonometricLocation || r.nextInt(operator * 2) == 0) {
      switch (r.nextInt(3)) {
        case 0:
          question.append(trigonometricMethod[0]);
          break;
        case 1:
          question.append(trigonometricMethod[1]);
          break;
        case 2:
          question.append(trigonometricMethod[2]);
          break;
      }
    }
    if (r.nextInt(operator * 3) == 0) {
      question.append(specialOperators[0]);
    }
    question.append(number);
    if (r.nextInt(operator * 3) == 0) {
      question.append(specialOperators[1]);
    }
  }
}

