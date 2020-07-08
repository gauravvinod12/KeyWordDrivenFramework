package com.invoiceplane.keyword.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//https://vimeo.com/user114020724
public class base
{
    static public WebDriver driver;
    static public Properties prop;

    public static WebDriver init_driver(String browserName) // this is for selecting BROWSER which has no locators in the excel file
    {
        if (browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static Properties init_properties() throws IOException //code to get PROPERTIES
    {
        prop = new Properties();
        FileInputStream ip = new FileInputStream("/Users/gaurav/IdeaProjects/KeyWordDrivenFramework/Resources/config.properties.properties");
        prop.load(ip);
        return prop;
    }

    public static String getBrowser() throws IOException      //code to get BROWSER
    {
        return init_properties().getProperty("browser");
    }

    public static String getUrl() throws IOException         //code to get URL
    {
        return init_properties().getProperty("url");
    }

    public static String getUsername() throws IOException    //code to get USERNAME
    {
        return init_properties().getProperty("username");
    }

    public static String getPassword() throws IOException    //code to get PASSWORD
    {
        return init_properties().getProperty("password");
    }

}

