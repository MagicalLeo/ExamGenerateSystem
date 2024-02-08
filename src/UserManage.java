import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManage {

  private ArrayList<User> userList;

  public UserManage() {
    this.userList = new ArrayList<>();
  }

  protected void registerUser(int type, String userName, String password) {
    User man = new User(type, userName, password);
    userList.add(man);
  }


  /**
   * 登入菜单
   */
  public User login() {
    String userName, password;
    int userId = -1;
    Scanner in = new Scanner(System.in);
    while (true) {
      System.out.println("请输入账号密码:");
      String tempStr[] = in.nextLine().split("\\s+");
      if (tempStr.length < 2) {
        System.out.println("账号密码请用空格区分");
        continue;
      }
      userName = tempStr[0];
      password = tempStr[1];

      for (int i = 0; i < userList.size(); i++) {
        if (userName.equals(userList.get(i).getUserName())) {
          userId = i;
        }
      }
      //用户名错误
      if (userId == -1) {
        System.out.println("请输入正确的用户名、密码");
        continue;
      }
      //密码错误
      if (password.equals(userList.get(userId).getPassword())) {
        System.out.println("登入成功!");
        User user = userList.get(userId);
        switch (user.getType()) {
          case 0:
            System.out.println("当前选择为小学出题");
            break;
          case 1:
            System.out.println("当前选择为初中出题");
            break;
          case 2:
            System.out.println("当前选择为高中出题");
            break;
        }
        return user;
      } else {
        System.out.println("请输入正确的用户名、密码");
        continue;
      }
    }
  }

  /**
   * 选择菜单的转运站，将用户导向到属于他的菜单有点像抽象的概念
   *
   * @param user
   * @throws IOException
   */
  public static void callMenu(User user) throws IOException {
    switch (user.getType()) {
      case 0:
        menuElementary(user);
        break;
      case 1:
        menuJunior(user);
        break;
      case 2:
        menuHigh(user);
        break;
    }
    // order = in.nextLine().split("\\s+");
  }

  /**
   * 小学题目生成菜单
   *
   * @param user
   * @throws IOException
   */
  public static int menuElementary(User user) throws IOException {
    String command;
    int questions = 0;
    Scanner in = new Scanner(System.in);
    ElementarySchoolExam elementarySchoolExam = new ElementarySchoolExam();
    System.out.println(
        "准备生成小学题目，请输入生成题目数量(输入-1将退出当前用户，重新登录，题目数需介于10~30之间)");
    while (true) {
      command = in.nextLine();
      if (command.equals("-1")) {
        return -1;
      } else if (command.startsWith("切换为")) {
        if (command.equals("切换为小学")) {
          user.setType(0);
          callMenu(user);
          return 0;
        } else if (command.equals("切换为初中")) {
          user.setType(1);
          callMenu(user);
          return 0;
        } else if (command.equals("切换为高中")) {
          user.setType(2);
          callMenu(user);
          return 0;
        } else {
          System.out.println("请输入小学、初中、高中三个选项中的一个");
        }
      } else if (Character.isDigit(command.charAt(0))) {
        questions = Integer.parseInt(command);
        if (questions < 10 || questions > 30) {
          System.out.println("输入题目数量无效，请输入10~30之间的题目数");
          menuJunior(user);
          return -1;
        } else {
          elementarySchoolExam.generateQuestion(questions, user);
        }
      } else {
        System.out.println("请正确输入题目数量、注销数-1或是切换为其他用户类型!");
        callMenu(user);
      }
    }
  }

  /**
   * 初中题目生成菜单
   *
   * @param user
   * @throws IOException
   */
  public static int menuJunior(User user) throws IOException {
    String command;
    int questions = 0;
    Scanner in = new Scanner(System.in);
    System.out.println("准备生成初中题目，请输入生成题目数量(输入-1将退出当前用户，重新登录)");
    JuniorHighSchoolExam juniorHighSchoolExam = new JuniorHighSchoolExam();
    while (true) {
      command = in.nextLine();
      if (command.equals("-1")) {
        return -1;
      } else if (command.startsWith("切换为")) {
        if (command.equals("切换为小学")) {
          user.setType(0);
          callMenu(user);
          return 0;
        } else if (command.equals("切换为初中")) {
          user.setType(1);
          callMenu(user);
          return 0;
        } else if (command.equals("切换为高中")) {
          user.setType(2);
          callMenu(user);
          return 0;
        } else {
          System.out.println("请输入小学、初中、高中三个选项中的一个");
        }
      } else if (Character.isDigit(command.charAt(0))) {
        questions = Integer.parseInt(command);
        if (questions < 10 || questions > 30) {
          System.out.println("输入题目数量无效，请输入10~30之间的题目数");
          menuJunior(user);
          return -1;
        } else {
          juniorHighSchoolExam.generateQuestion(questions, user);
        }
      } else {
        System.out.println("请正确输入题目数量、注销数-1或是切换为其他用户类型!");
        callMenu(user);
      }
    }

  }

  /**
   * 高中题目生成菜单
   *
   * @param user
   * @throws IOException
   */
  public static int menuHigh(User user) throws IOException {
    String command;
    int questions = 0;
    Scanner in = new Scanner(System.in);
    System.out.println("准备生成高中题目，请输入生成题目数量(输入-1将退出当前用户，重新登录)");
    HighSchoolExam highSchoolExam = new HighSchoolExam();
    while (true) {
      command = in.nextLine();
      if (command.equals("-1")) {
        return -1;
      } else if (command.startsWith("切换为")) {
        if (command.equals("切换为小学")) {
          user.setType(0);
          callMenu(user);
          return 0;
        } else if (command.equals("切换为初中")) {
          user.setType(1);
          callMenu(user);
          return 0;
        } else if (command.equals("切换为高中")) {
          user.setType(2);
          callMenu(user);
          return 0;
        } else {
          System.out.println("请输入小学、初中、高中三个选项中的一个");
        }
      } else if (Character.isDigit(command.charAt(0))) {
        questions = Integer.parseInt(command);
        if (questions < 10 || questions > 30) {
          System.out.println("输入题目数量无效，请输入10~30之间的题目数");
          menuJunior(user);
          return -1;
        } else {
          highSchoolExam.generateQuestion(questions, user);
        }
      } else {
        System.out.println("请正确输入题目数量、注销数-1或是切换为其他用户类型!");
        callMenu(user);
      }
    }
  }

  /**
   * 如果链接数据库的话就不用这部份了
   *
   * @param manager
   */
  public static void userInit(UserManage manager) {
    manager.registerUser(0, "张三1", "123");
    manager.registerUser(0, "张三2", "123");
    manager.registerUser(0, "张三3", "123");

    manager.registerUser(1, "李四1", "123");
    manager.registerUser(1, "李四2", "123");
    manager.registerUser(1, "李四3", "123");

    manager.registerUser(2, "王五1", "123");
    manager.registerUser(2, "王五2", "123");
    manager.registerUser(2, "王五3", "123");
  }
}

