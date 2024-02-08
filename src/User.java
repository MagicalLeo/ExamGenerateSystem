
public class User {

  private int type;//type 0-小学, 1-初中, 2-高中
  private String userName;
  private String password;
  /**
   * user的构建函数
   * @param type
   * @param userName
   * @param password
   * */
  protected User(int type, String userName, String password) {
    this.type = type;
    this.userName = userName;
    this.password = password;
  }

  protected int getType() {
    return type;
  }

  protected String getUserName() {
    return userName;
  }

  protected String getPassword() {
    return password;
  }

  public void setType(int type) {
    this.type = type;
  }
}

