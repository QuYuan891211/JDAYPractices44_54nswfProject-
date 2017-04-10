package cn.qy.core.Utils;

import cn.qy.nswf.user.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qy on 2017/3/6.
 */
public class POIExcelUtil {
    public static void export(List<User> list, ServletOutputStream outputStream, String name) throws IOException {

        HSSFWorkbook workbooks = new HSSFWorkbook();

        HSSFSheet sheet = workbooks.createSheet("列表1");
        CellRangeAddress address = new CellRangeAddress(1,1,1,5);
        sheet.addMergedRegion(address);
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell = row1.createCell(1);
        //传入样式
        cell.setCellStyle(setCellStyleForLine1(workbooks));
        cell.setCellValue(name);
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellStyle(setCellStyleForLine2(workbooks));
        cell1.setCellValue("UserName");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellStyle(setCellStyleForLine2(workbooks));
        cell2.setCellValue("Account");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellStyle(setCellStyleForLine2(workbooks));
        cell3.setCellValue("Department");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellStyle(setCellStyleForLine2(workbooks));
        cell4.setCellValue("Gender");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellStyle(setCellStyleForLine2(workbooks));
        cell5.setCellValue("Email");
        //写入数据
        int i = 3;
        for (User user: list) {
            HSSFRow rowData = sheet.createRow(i);
            HSSFCell cellData1 = rowData.createCell(1);
            cellData1.setCellValue(user.getName());
            HSSFCell cellData2 = rowData.createCell(2);
            cellData2.setCellValue(user.getAccount());
            HSSFCell cellData3 = rowData.createCell(3);
            cellData3.setCellValue(user.getDept());
            HSSFCell cellData4 = rowData.createCell(4);
            cellData4.setCellValue(user.isGender()?"Male":"Female");
            HSSFCell cellData5 = rowData.createCell(5);
            cellData5.setCellValue(user.getEmail());
            i++;
        }
        workbooks.write(outputStream);
        workbooks.close();
    }

    public static List<User> importExcel(FileInputStream inputStream,boolean is03Excel){
        List<User> list = new LinkedList<User>();
        try {
            Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumbers = sheet.getPhysicalNumberOfRows();
            if (rowNumbers>2){
                User user = null;
                for (int i = 2; i<rowNumbers; i++){
                    Row row = sheet.getRow(i);
                    user = new User();
                    String name = row.getCell(0).getStringCellValue();
                    user.setName(name);
                    String name2 = row.getCell(1).getStringCellValue();
                    user.setAccount(name2);
                    String name3 = row.getCell(2).getStringCellValue();
                    user.setDept(name3);
                    String name4 = row.getCell(3).getStringCellValue();
                    user.setGender("男".equals(name4));
                    String name5 = row.getCell(4).getStringCellValue();
                    user.setEmail(name5);
                    user.setMobile("");
                    user.setPassword("123");
                    user.setState(User.USER_STATE_VALID);
                    list.add(user);
                }

            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private static HSSFCellStyle setCellStyleForLine1(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(setFontForLine1(workbook));
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    private static HSSFFont setFontForLine1(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFFont.COLOR_RED);
        font.setFontHeightInPoints((short)48);
        return font;
    }
    private static HSSFCellStyle setCellStyleForLine2(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(setFontForLine2(workbook));
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    private static HSSFFont setFontForLine2(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }
}
