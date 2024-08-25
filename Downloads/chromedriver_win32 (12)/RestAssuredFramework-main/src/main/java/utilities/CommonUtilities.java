package src.main.java.utilities;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CommonUtilities {

    public static TestBase oTest = new TestBase();
    Logger log = Logger.getLogger(getClass().getSimpleName());
    static Properties props = new Properties();
    static FileInputStream fileIn = null;

    public void loadPropertyFiles(String propertiesFilePath) throws IOException {
        log.info("current dir using system" + propertiesFilePath);
        fileIn = new FileInputStream(propertiesFilePath);
        props.load(fileIn);
        System.getProperties().putAll(props);
    }

    public void loadLog4jProperty(String propertiesFilePath) throws IOException {
        log.info("Log4j Property file path: ");
        fileIn = new FileInputStream(propertiesFilePath);
        props.load(fileIn);
        System.getProperties().putAll(props);
    }

    public void ufUserException(boolean bExpectedBoolean, boolean ActualBoolean, String strExceptionSummary) throws Exception {
        if (bExpectedBoolean != ActualBoolean) {
            throw new Exception(strExceptionSummary);
        }

    }

    public String convertToSeconds(String sActualTime) {
        String sTotalSec = null;
        String[] strSplit = sActualTime.split(":");
        if (strSplit.length == 3) {
            int iHourToSec = Integer.parseInt(strSplit[0].toString()) * 60 * 60;
            // log.info(iHourToSec)
            int iMinuteToSec = Integer.parseInt(strSplit[1].toString()) * 60;
            // log.info(iMinuteToSec);
            double iSeconds = Double.parseDouble(strSplit[2].toString());
            // log.info(iSeconds);
            double iTotalSec = iHourToSec + iMinuteToSec + iSeconds;
            // log.info(iTotalSec);
            sTotalSec = Double.toString(iTotalSec);
        }
        return sTotalSec;
    }

    public JSONObject ReadJsonFileGetJsonObject(String sPathOfJson) throws IOException {
        String sJsonContent = readFileReturnInString(sPathOfJson);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(sJsonContent.trim());
        } catch (Exception pj) {
            try {
                jsonObject = new JSONObject(sPathOfJson.substring(sPathOfJson.indexOf("{")));
            } catch (Exception pja) {
                log.error("unable to parse " + pj);
            }
        }
        return jsonObject;
    }

    public JSONObject convertStringToJsonObject(String sJsonContent) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(sJsonContent.substring(sJsonContent.indexOf("{")));
        } catch (Exception pj) {
            try {
                jsonObject = new JSONObject(sJsonContent.trim());
            } catch (Exception pja) {
                log.error("Unable to parse " + pj);
            }
        }
        return jsonObject;
    }

    public String readFileReturnInString(String sPathOfJson) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(sPathOfJson));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public String unixTimeStampString() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

    public String unixToNormalTime(String unixTime) {
        long unixTime1 = Long.parseLong(unixTime);

        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM dd, yyyy");

        final String formattedDtm = Instant.ofEpochSecond(unixTime1)
                .atZone(ZoneId.of("GMT+05:30")).format(formatter);
        return String.valueOf(formattedDtm);
    }

    public String generateDate(int dayCount) {
        log.info(LocalDate.now().plusDays(dayCount).toString());
        return LocalDate.now().plusDays(dayCount).toString();
    }

    public String getSystemP() {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
            System.out.println("System IP Address: " + (localhost.getHostAddress().trim()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return (localhost.getHostAddress().trim());
    }

    public static String cyWareDAte(int days) {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, +days);
        Date s = c.getTime();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(s);
        return dateString;
    }

    public String[] splitParamGetInArray(String property) {
        return property.split(",");
    }

    public Set<String> splitParamGetInSet(String property) {

        Set<String> set = new HashSet<String>();
        for (String s : property.split(",")) {
            set.add(s);
        }
        return set;
    }

    public List<String> splitParamGetInList(String property) {
        List<String> list = new ArrayList<>();
        for (String s : property.split(",")) {
            list.add(s);
        }
        return list;
    }

    public String getCurrentDate() {
        Format formatter = new SimpleDateFormat("MMM dd, yyyy");
        String today = formatter.format(new Date());
        return today;
    }

}
