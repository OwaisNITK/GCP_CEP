package com.covid.dataset.generator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.covid.dataset.template.Covid19DataModel;

// TODO: Auto-generated Javadoc
/** The Class DataGenerator. */
public class DataGenerator {

    /** The Constant random. */
    private static final Random random = new Random();

    /** The Constant lookUpMap. */
    private static final Map<String, String> lookUpMap = new HashMap<String, String>();

    /** Generate random data.
     *
     * @param date
     *            the date
     * @return the list
     * @throws Exception
     *             the exception */
    public List<String> generateRandomData(String date) throws Exception {
        List<String> dataList = new ArrayList<String>();

        populateLookUpMap();
        InputStream inputStream = getClass().getResourceAsStream("/lastreport.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        boolean header = false;
        while ((line = br.readLine()) != null) {
            String[] values = line.split("\\|");
            if (header) {
                String combinedKey = values[11];
                String data = lookUpMap.get(combinedKey);
                if (data != null) {
                    String[] fields = data.split("\\|");

                    Covid19DataModel dto = new Covid19DataModel();
                    dto.setFips(fields[4]);
                    dto.setAdmin2(fields[5]);
                    dto.setProvinceState(fields[6]);
                    dto.setCountryRegion(fields[7]);
                    dto.setLastUpdate(date + " 05:00:00");
                    dto.setLatitude(fields[8]);
                    dto.setLongitude(fields[9]);

                    int confirmedToday = generateRandomInteger(1, 50);
                    int deathToday = generateRandomInteger(0, (confirmedToday / 10));
                    int recoveredToday = generateRandomInteger(1, (confirmedToday - deathToday));
                    int active = confirmedToday - deathToday - recoveredToday;

                    int population;
                    try {
                        population = Integer.valueOf(fields[11]);
                    } catch (Exception ex) {
                        continue;
                    }

                    float incidenceRate = ((confirmedToday * 100) / population) * 1000;
                    float caseFatalityRatio = (deathToday * 100) / confirmedToday;

                    dto.setConfirmed(String.valueOf(confirmedToday));
                    dto.setDeath(String.valueOf(deathToday));
                    dto.setRecovered(String.valueOf(recoveredToday));
                    dto.setActive(String.valueOf(active));
                    dto.setCombinedKey(combinedKey);
                    dto.setIncidenceRate(String.valueOf(incidenceRate));
                    dto.setCaseFatalityRatio(String.valueOf(caseFatalityRatio));

                    dataList.add(dto.toString());
                }
            }
            header = true;
        }
        return dataList;
    }

    /** Populate look up map.
     *
     * @throws Exception
     *             the exception */
    public void populateLookUpMap() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/lookuptable.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        boolean header = false;
        while ((line = br.readLine()) != null) {
            String[] values = line.split("\\|");
            if (header) {
                lookUpMap.put(values[10], line);
            }
            header = true;
        }
    }

    /** Generate random integer.
     *
     * @param min
     *            the min
     * @param max
     *            the max
     * @return the int */
    public int generateRandomInteger(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /** Generate random time.
     *
     * @return the string */
    public static String generateRandomTime() {
        final int millisInDay = 24 * 60 * 60 * 1000;
        Time time = new Time((long) random.nextInt(millisInDay));
        return time.toString();
    }
}