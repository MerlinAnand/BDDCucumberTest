package utilities;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GetDateFromPicker {

    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    WebDriver driver = Shared.getWebDriver(sessionName);

    @Test
    public void datePickerTest() {
        //Declare a implicit wait for synchronisation
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Get Today's number
        String today = getCurrentDay();
        System.out.println("Today's number: " + today + "\n");

        //Click and open the datepickers
       //driver.findElement(By.xpath(".//*[fusion_builder_container hundred_percent="yes" overflow="visible"][fusion_builder_row][fusion_builder_column type="1_1" background_position="left top" background_color="" border_size="" border_color="" border_style="solid" spacing="yes" background_image="" background_repeat="no-repeat" padding="" margin_top="0px" margin_bottom="0px" class="" id="" animation_type="" animation_speed="0.3" animation_direction="left" hide_on_mobile="no" center_content="no" min_height="none"][@id='godate']")).click();

        //This is from date picker table
        WebElement dateWidgetFrom = driver.findElement(By.xpath(".//*[@id='calendar-100000000']/div[3]/table/tbody"));

        //This are the rows of the from date picker table
        //List<WebElement> rows = dateWidgetFrom.findElements(By.tagName("tr"));

        //This are the columns of the from date picker table
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

        //DatePicker is a table. Thus we can navigate to each cell
        //and if a cell matches with the current date then we will click it.
        for (WebElement cell: columns) {
            /*
            //If you want to click 18th Date
            if (cell.getText().equals("18")) {
            */
            //Select Today's Date
            if (cell.getText().equals(today)) {
                cell.click();
                break;
            }
        }

        //Wait for 4 Seconds to see Today's date selected.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentDay (){
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt +"\n");

        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");

        return todayStr;
    }


    public String getSdf(Integer days) {
        LocalDate localDate = LocalDate.now();
        System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
        // String oldDate = localDate;
        System.out.println("Date before Addition: "+localDate);
        //Specifying date format that matches the given date
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd yyyy",
                Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
       /* try{
            //Setting the date to the given date
            c.setTime(sdf.parse(localDate.toString()));
        }catch(ParseException e){
            e.printStackTrace();
        }*/
        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, days);
        //Date after adding the days to the given date
        String newDate1 = sdf.format(c.getTime());

        //Displaying the new Date after addition of Days
        return newDate1;
    }




}
