package com.invoiceplane.keyword.Engine;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.invoiceplane.keyword.Base.base.driver;
import static com.invoiceplane.keyword.Base.base.init_driver;

//https://vimeo.com/user114020724
public class KeyWordEngine extends Base
{
    WebElement element;

    public final String SENARIO_SHEET_PATH = "KeywordExcel/Keywords.xls"; //final will make sure u cannot change inside program later

    public void startExecution(String sheetName) throws IOException, InterruptedException {
        FileInputStream file = new FileInputStream(SENARIO_SHEET_PATH);
        HSSFWorkbook workbook = new HSSFWorkbook(file); //XSSFWorkbook statement is used instead of HSSFWorkbook when the excel file is in the format of .xlsx
        HSSFSheet sheet = workbook.getSheet(sheetName);

        int k=0;
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i=0; i<rowCount;i++) {
            String locatorName = "";
            String locatorValue = "";
            String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); //to string converts to string and trim trims the space

            if (!locatorColValue.equalsIgnoreCase("NA"))
            {
                locatorName = locatorColValue.split("=")[0].trim();  //locator is seperated as seen from excel file and named as locatorName
                locatorValue = locatorColValue.split("=")[1].trim(); //Values is seperated as seen from excel file and named as locatorValue
            }

            String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
            String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

            switch (action)    // -------->  SWITCH case for All these switch cases are used for ACTIONS who DON'T have LOCATORS "NA", check in excel file
            {
                case "open browser":
                    init_driver(value); //intialising this by on the syntax by "cmd enter" getting the code from base file
                    break;

                case "enter url":
                    init_driver(value);
                    break;

                case "quit":
                    driver.close();
                    break;
            }

            switch (locatorName) // -------->  SWITCH case for Locators with Values
            {
                case "id":
                    element = driver.findElement(By.id(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys"))
                        element.sendKeys(value);
                    else if (action.equalsIgnoreCase("click"))
                        element.click();
                    Thread.sleep(5000);
                    break;

                case "name":
                    element = driver.findElement(By.name(locatorValue));
                    if(action.equalsIgnoreCase("sendkeys"))
                        element.sendKeys(value);
                    else if(action.equalsIgnoreCase("click"))
                        element.click();
                    Thread.sleep(5000);
                    break;

                case "linkText":
                    element = driver.findElement(By.id(locatorValue));
                    if(action.equalsIgnoreCase("click"))
                        element.click();
                    break;

                default:break; // means ???????????????????????????????
            }
        }
    }
}
