package com.example.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonExcelReader {

    /**
     * This method reads data from an Excel file and returns it as a HashMap.
     *
     * @param testCase     the name of the test case whose data is to be retrieved
     * @param workbookName the name of the Excel workbook that contains the data
     * @param sheetName    the name of the sheet in the workbook that contains the data
     * @return a HashMap containing the data read from the Excel file, with the keys being the column names and the values being the corresponding cell values
     * @throws IOException if an error occurs while reading the Excel file
     */
    public Map<String, String> getDataFromExcel(String testCase, String workbookName, String sheetName) throws IOException {


        // Create ExcelData Map to store Test Data
        /*
         In Java, a HashMap is a class that implements the Map interface.
          It stores key-value pairs in a hash table, which allows for fast access,
          insertion, and deletion of elements based on their keys.
          The keys in a HashMap must be unique, but the values can be duplicated.

          The basic methods that can be used on a HashMap include:
              put(key, value) : adds a key-value pair to the map
              get(key) : returns the value associated with the given key
              remove(key) : removes the key-value pair associated with the given key
              containsKey(key) : returns true if the key is in the map, false otherwise
              size() : returns the number of key-value pairs in the map

         */
        HashMap<String, String> excelData = new HashMap<>();

        // Add Test Case Name to Excel Map
        excelData.put("Test Case :", testCase);


        // Create File Input Stream
        /*
           In Java, a FileInputStream is a class that allows a program to read bytes from a file.
           It reads bytes from a file in a binary format and is used for reading binary data such
           as image or audio files.The FileInputStream class is a subclass of the InputStream class,
           which is the superclass of all input stream classes in Java.The read() method of the FileInputStream
           class is used to read bytes from the file, and the close() method is used to close the stream and
           release any system resources associated with it.
          */
        FileInputStream inputStream = new FileInputStream("src/test/resources/TestData/" + workbookName + ".xlsx");


        // Create Workbook Object to read values from excel.
        /*
          The Workbook class in Apache POI is an abstract class that represents a workbook in
          the Microsoft Excel file format. It is the parent class for specific implementations
          for different Excel file formats, such as HSSFWorkbook for the older .xls format, and
          XSSFWorkbook for the newer .xlsx format.

          The Workbook class provides methods for creating and manipulating workbooks,
          including methods for creating new sheets, setting cell values, and formatting cells.
          It also provides a way to access the underlying low-level structure of the Excel file format,
          through the low-level API.

          A common use case for the Workbook class is to read data from an existing Excel file
          and process it in a Java program, or to create a new Excel file and write data to it
          from a Java program.
          */
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Create Sheet Class
        /*
          The Sheet class provides methods for creating and manipulating worksheets,
          including methods for creating new rows and cells, setting cell values,
          and formatting cells.

          It also provides methods for working with the underlying low-level structure
          of the Excel file format, such as getting and setting the sheet's name,
          and getting the number of rows and columns in the sheet.
          */
        Sheet sheet = workbook.getSheet(sheetName);

        // Create DataFormatter for to be able to extract the values of the cell as String.
        /*
          DataFormatter is a class provided by the Apache POI library, which is used to format cell values in Microsoft Excel files.
          The class provides methods for formatting cell values as strings, regardless of the cell's data type. This can be useful
          for extracting data from Excel files and displaying it in a more human-readable format, or for comparing cell values in
          a programmatic way. The specific method being used in the provided code is the formatCellValue method, which returns
          the formatted value of a cell as a string.
          */
        DataFormatter dataFormatter = new DataFormatter();


        // Iterate through the rows in the sheet to find the cell that contains the String value "TestCase".
        int rowIndexOfTestCase = 0;
        int columnIndexOfTestCase = 0;

        // Find the "TestCase" column
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).equalsIgnoreCase("TestCase")) {
                    rowIndexOfTestCase = row.getRowNum();
                    columnIndexOfTestCase = cell.getColumnIndex();
                    break;
                }
            }
        }

        // Iterate through the rows below the header and find the matching test case
        for (int rowIndex = rowIndexOfTestCase + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            Cell testCaseCell = currentRow.getCell(columnIndexOfTestCase);

            if (testCaseCell != null && testCaseCell.getStringCellValue().equalsIgnoreCase(testCase)) {

                // Iterate through the cells in the row
                for (int columnIndex = columnIndexOfTestCase + 1; columnIndex < currentRow.getLastCellNum(); columnIndex++) {

                    Cell currentCell = currentRow.getCell(columnIndex);
                    Cell headerCell = sheet.getRow(rowIndexOfTestCase).getCell(columnIndex);

                    if (currentCell != null && headerCell != null) {
                        String key = dataFormatter.formatCellValue(headerCell);
                        String value = dataFormatter.formatCellValue(currentCell);
                        excelData.put(key, value);
                    }
                }
                break;
            }
        }

        // Note : What is the purpose of using the 'close()' method?
        /*
          The "close()" method is used to release the resources that are associated with an
          InputStream, Selenium WebDriver, or Apache POI Workbook object. When a program is
          finished using these objects, it is important to close them to free up system
          resources and prevent memory leaks. Failure to close these resources can lead to performance issues,
          such as increased memory usage, and may cause the program to become unresponsive or crash.

          Additionally, it is a good practice to close resources when they are no longer needed
          to ensure that the program is not holding on to unnecessary resources.

          */

        inputStream.close();
        workbook.close();

        return excelData;
    }
}
