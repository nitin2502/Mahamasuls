package Login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class RenamePdf {

    private static ExtentReports extentReports;

    public static void main(String[] args) throws IOException {
        // Initialize Extent Reports
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent_report.html");
        extentReports.attachReporter(sparkReporter);

        ExtentTest test = extentReports.createTest("Rename PDFs Using Excel");

        try {
            // Path to Excel file and folder containing the PDFs
            String excelFilePath = "C:\\Users\\niting\\Downloads\\Gadchirolis.xlsx\\";
            String pdfFolderPath = "D:\\IFR\\Sironcha\\"; // Add a trailing backslash

            // Load the Excel file and read the name data
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Sheet"); // Assuming data is in the first sheet

            // Initialize a counter for successfully renamed files
            int renamedFileCount = 0;
            System.out.println(renamedFileCount);
            for (Row row : sheet) {
                Cell pdfOldNameCell = row.getCell(0);  // Assuming old PDF names are in column A
                Cell pdfNewNameCell = row.getCell(1);  // Assuming new PDF names are in column B

                if (pdfOldNameCell != null && pdfNewNameCell != null) {
                    String oldName = pdfOldNameCell.getStringCellValue().trim();  // Trim to remove extra spaces
                    String newName = pdfNewNameCell.getStringCellValue().trim();  // Trim to remove extra spaces

                    // Rename the PDF file with logging
                    boolean isRenamed = renamePDF(pdfFolderPath, oldName, newName, test);

                    // Increment the counter if renaming is successful
                    if (isRenamed) {
                        renamedFileCount++;
                    }
                }
            }

            // Close resources
            workbook.close();
            fileInputStream.close();

            // Log success message
            test.pass("Successfully renamed " + renamedFileCount + " files.");

        } catch (Exception e) {
            // Log any exceptions with stack trace
            test.fail(e.getMessage());  // Consider adding screenshot capture if needed
        }

        // Flush reports
        extentReports.flush();
    }

    private static boolean renamePDF(String folderPath, String oldName, String newName, ExtentTest test) {
        String oldFilePath = folderPath + oldName + ".pdf";
        String newFilePath = folderPath + newName + ".pdf";

        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);

        // Debugging: print the paths being checked
        System.out.println("Old File Path: " + oldFilePath);
        System.out.println("New File Path: " + newFilePath);

        // Rename the file
        if (oldFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                System.out.println("Renamed: " + oldName + " to " + newName);
                test.log(Status.PASS, "Renamed file: " + oldName + " to " + newName);
                return true;  // Renaming successful
            } else {
                System.out.println("Failed to rename: " + oldName);
                test.log(Status.FAIL, "Failed to rename file: " + oldName);
                return false;  // Renaming failed
            }
        } else {
            System.out.println("File not found: " + oldFilePath);
            test.log(Status.WARNING, "File not found: " + oldFilePath);
            return false; 
        }
    }
}
