package Utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class data {


    @DataProvider(name = "reservationData")
    public static Iterator<Object[]> getReservationData() {
        List<Object[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream("src/test/java/Resources/TestData.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Sheet1");
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String searchText = row.getCell(0).getStringCellValue();
                String checkInDate = row.getCell(1).getStringCellValue();
                String checkOutDate = row.getCell(2).getStringCellValue();
                String Calender_head = row.getCell(3).getStringCellValue();
                String HotelName = row.getCell(4).getStringCellValue();

                data.add(new Object[]{searchText, checkInDate, checkOutDate, Calender_head , HotelName});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.iterator();
    }
}
