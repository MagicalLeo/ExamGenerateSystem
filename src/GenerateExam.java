import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public interface GenerateExam {
    public void generateQuestion(int questions, User user) throws IOException;

    /**
     * 将问题集储存成文件
     * @param question
     * @param user
     * @throws IOException
     * */
    default void generateFile(String[] question, User user) throws IOException {
        boolean success;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String folderPath = "src/" + user.getUserName() + "/";
        String filePath = ft.format(dNow) + ".txt";

        File userFolder = new File(folderPath);
        userFolder.mkdirs();
        File file = new File(folderPath + filePath);

        // 创建文件
        success = file.createNewFile();
        // creates a FileWriter Object
        if (success) {
            FileWriter writer = new FileWriter(file);
            // 向文件写入内容
            for (int i = 0; i < question.length; i++) {
                writer.write(question[i] + "\n" + "\n");
                System.out.println(question[i]);
            }
            writer.flush();
            writer.close();
            System.out.printf("完成输出%d个问题，文件储存至%s，若要重新生成请输入题数,输入-1注销\n",
                    question.length, folderPath + filePath);
        }
    }
    /**
     * check if the question is corresponded
     * @param user
     * @param question
     * @throws IOException
     * */
    default boolean checkCorrespond(User user, StringBuilder question) throws IOException {
        String folderPath = "src/" + user.getUserName() + "/";
        String context = question.toString().split("\\.")[1]; //取得题目的内容
        File userFolder = new File(folderPath);
        File[] fs = userFolder.listFiles();
        if (fs == null) {
            return true;
        }
        // 创建 FileReader 对象
        for (int i = 0; i < fs.length; i++) {
            Scanner fileIn = new Scanner(new File(fs[i].getPath()));
            while (fileIn.hasNextLine()) {
                String[] historyQuestion = fileIn.nextLine().split("\\."); //取得内文部分
                if (historyQuestion[0] == "") {
                    continue;
                }
                if (historyQuestion[1].equals(context)) {
                    return false;
                }
            }
        }
        return true;
    }
}

