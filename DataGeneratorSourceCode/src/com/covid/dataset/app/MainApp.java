package com.covid.dataset.app;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.covid.dataset.generator.CEPFileUtils;
import com.covid.dataset.generator.DataGenerator;
import com.covid.dataset.template.DataSetModel;
import com.covid.dataset.template.TemplateCreatorService;

// TODO: Auto-generated Javadoc
/** The Class MainApp. */
public class MainApp {

    /** The main method.
     *
     * @param args
     *            the arguments
     * @throws Exception
     *             the exception */
    public static void main(String[] args) throws Exception {

        System.out.println("Date Supplied : " + args[0]);
        System.out.println("Output Directory : " + args[1]);

        // Validating Date
        Boolean formatValidation = isValidFormat("yyyy-MM-dd", args[0], Locale.getDefault());
        if (!formatValidation) {
            System.out.println("Date Format does not match YYYY-MM-dd. Please Restart the Application");
            System.exit(0);
        }

        String date = args[0];
        String directoryPath = args[1];

        // Resolving Output Directory Path
        String fileSeparator = System.getProperty("file.separator");
        directoryPath.replaceAll("/", Matcher.quoteReplacement(fileSeparator));
        SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
        String absolutePath = directoryPath + sdf.format(new SimpleDateFormat("yyyy-mm-dd").parse(date)) + ".csv";

        long start_time = System.nanoTime();

        TemplateCreatorService templateCreator = new TemplateCreatorService();
        DataSetModel dataset = new DataSetModel();

        DataGenerator dataGenerator = new DataGenerator();
        List<String> dataList = dataGenerator.generateRandomData(date);

        dataset.setDataList(dataList);
        String data = templateCreator.createTemplate(dataset);

        try {
            CEPFileUtils.writeToFile(data, absolutePath);
        } catch (Exception e) {
            System.out.println("Error while writing file. Please check with file path");
            System.exit(0);
        }

        long end_time = System.nanoTime();
        double difference = ((end_time - start_time) / 1e6) / 1000;
        System.out.println("Data Successfully generated at : " + absolutePath);
        System.out.println("Total Time Taken In Seconds : " + difference);
    }

    /** Checks if is valid format.
     *
     * @param format
     *            the format
     * @param value
     *            the value
     * @param locale
     *            the locale
     * @return true, if is valid format */
    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    // Suppress any Validation Error and return false
                }
            }
        }

        return false;
    }
}