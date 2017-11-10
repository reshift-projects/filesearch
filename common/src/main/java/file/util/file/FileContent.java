package file.util.file;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/10
 * @description 文件具体信息处理
 */
public class FileContent {
    private String content;
    private int rowNumber;
    private int orderNumber;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
