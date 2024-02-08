
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    UserManage managerA = new UserManage();
    System.out.println("***********欢迎来到中小学数学卷子自动生成器!***********");
    UserManage.userInit(managerA);
    while (true) {
      User user;
      user = managerA.login();
      UserManage.callMenu(user);
    }
  }
}


