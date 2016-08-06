import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 用于生成values-sw文件夹，进行屏幕适配
 */
public class GenerateValueFiles {

    private int baseW;

    private String dirStr = "./res";

    private final static String WTemplate    = "<dimen name=\"dp_{0}\">{1}dp</dimen>\n";
    private final static String TextTemplate = "<dimen name=\"sp_{0}\">{1}sp</dimen>\n";


    private final static String VALUE_TEMPLATE = "values-sw{0}dp";

    private static final String supportStr = "300;320;340;360;400;480;520;600;720;";

    public GenerateValueFiles(int baseX) {
        this.baseW = baseX;
        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdir();

        }
    }


    public void generate() {
        String[] vals = supportStr.split(";");
        for (String val : vals) {
            generateXmlFile(Integer.parseInt(val));
        }

    }

    public String getInteger(int i) {
        if (i % 2 == 0) {
            return i / 2 + "";
        } else {
            return i / 2.0 + "";
        }
    }

    private void generateXmlFile(int w) {

        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>");
        float cellw = w * 2.0f / baseW;//2表示屏幕密度xhdpi

        for (int i = 1; i < baseW; i++) {
            sbForWidth.append(WTemplate.replace("{0}", getInteger(i) + "").replace("{1}",
                    change(cellw * i / 2) + ""));

        }

        sbForWidth.append(WTemplate.replace("{0}", baseW / 2 + "").replace("{1}",
                w + ""));

        for (int i = 1; i < baseW; i++) {
            sbForWidth.append(TextTemplate.replace("{0}", getInteger(i) + "").replace("{1}",
                    change(cellw * i / 2) + ""));

        }


        sbForWidth.append(TextTemplate.replace("{0}", baseW / 2 + "").replace("{1}",
                w + ""));
        sbForWidth.append("</resources>");

        File fileDir = new File(dirStr + File.separator
                + VALUE_TEMPLATE.replace("{0}", w + ""));
        fileDir.mkdir();

        File layxFile = new File(fileDir.getAbsolutePath(), "dimens.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }

    public static void main(String[] args) {
        int baseW = 720;//720*1280(xhdpi)屏幕宽度为360dp
        new GenerateValueFiles(baseW).generate();
    }

}
