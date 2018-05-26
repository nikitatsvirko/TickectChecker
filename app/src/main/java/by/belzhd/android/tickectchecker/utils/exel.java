package by.belzhd.android.tickectchecker.utils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class exel {
 /*   public static List<Object> parse(String name) {

        List<Object> result = null;
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream(name);
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        result.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        result.add(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        result.add(cell.getNumericCellValue());
                        break;
                    default:
                        result.add(cell.getStringCellValue());
                        break;
                }
            }
        }

        return result;
    }
*/
 public static String parse(String name) {

     String result = "";
     InputStream in = null;
     HSSFWorkbook wb = null;
     try {
         in = new FileInputStream(name);
         wb = new HSSFWorkbook(in);
     } catch (IOException e) {
         e.printStackTrace();
     }

     Sheet sheet = wb.getSheetAt(0);
     Iterator<Row> it = sheet.iterator();
     while (it.hasNext()) {
         Row row = it.next();
         Iterator<Cell> cells = row.iterator();
         while (cells.hasNext()) {
             Cell cell = cells.next();
             int cellType = cell.getCellType();
             switch (cellType) {
                 case Cell.CELL_TYPE_STRING:
                     result += cell.getStringCellValue();
                     break;
                 case Cell.CELL_TYPE_NUMERIC:
                     result += cell.getNumericCellValue();
                     break;

                 case Cell.CELL_TYPE_FORMULA:
                     result += cell.getNumericCellValue();
                     break;
                 default:
                     result += cell.getStringCellValue();
                     break;
             }
         }
     }

     return result;
 }
}
