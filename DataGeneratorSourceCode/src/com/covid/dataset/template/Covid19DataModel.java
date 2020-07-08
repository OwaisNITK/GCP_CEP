package com.covid.dataset.template;

// TODO: Auto-generated Javadoc
/** The Class ClickStreamDataModel. */
public class Covid19DataModel {

    /** The user id. */
    private String fips;

    /** The url. */
    private String admin2;

    /** The action. */
    private String provinceState;

    /** The location. */
    private String countryRegion;

    /** The log date. */
    private String lastUpdate;

    /** The log time. */
    private String latitude;

    /** The payment method. */
    private String longitude;
    
    /** The confirmed. */
    private String confirmed;
    
    /** The death. */
    private String death;
    
    /** The recovered. */
    private String recovered;
    
    /** The active. */
    private String active;
    
    /** The combined key. */
    private String combinedKey;
    
    /** The incidence rate. */
    private String incidenceRate;
    
    /** The case fatality ratio. */
    private String caseFatalityRatio;

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
    }

    public String getIncidenceRate() {
        return incidenceRate;
    }

    public void setIncidenceRate(String incidenceRate) {
        this.incidenceRate = incidenceRate;
    }

    public String getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(String caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }

    @Override
    public String toString() {
        return fips + "," +
                admin2 + "," +
                provinceState + "," +
                countryRegion + "," +
                lastUpdate + "," +
                latitude + "," +
                longitude + "," +
                confirmed + "," + 
                death + "," +
                recovered + "," +
                active + "," +
                combinedKey + "," +
                incidenceRate + "," + 
                caseFatalityRatio ;
    }
}