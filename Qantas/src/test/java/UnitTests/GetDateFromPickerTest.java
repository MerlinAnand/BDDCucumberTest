package UnitTests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.GetDateFromPicker;
import utilities.Shared;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;



public class GetDateFromPickerTest {

        GetDateFromPicker getDateFromPicker;

    public GetDateFromPickerTest(){
        getDateFromPicker = new GetDateFromPicker();

    }

    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    WebDriver driver = Shared.getWebDriver(sessionName);

    @Test
    public void datePickerTest() {
        //Declare a implicit wait for synchronisation
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Calendar calendar;
        calendar = Calendar.getInstance();
        String customDate = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + (calendar.get(Calendar.DAY_OF_MONTH)+15);
       // System.out.println("Custom Date "+ customDate);

        String nDate = getDateFromPicker.getSdf(15);

        String[]  result = nDate.split(" ");
        System.out.println("Month of New Date  -->" + result[0]);
        System.out.println("Month of New Date  -->" + result[1]);
        System.out.println("Month of New Date  -->" + result[2]);

        String datefir= "AUGUST 2019";
        String datesec= "SEPTEMBER 2019";

        if (datefir.toLowerCase().contains(result[1].toLowerCase()))
            System.out.println("The month to select is August");

        if (datesec.toLowerCase().contains(result[1].toLowerCase()))
            System.out.println("The month to select is September");


   }




}
