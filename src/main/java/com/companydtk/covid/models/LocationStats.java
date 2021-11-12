package com.companydtk.covid.models;

public class LocationStats {
	private String state;
	private String country;
	private int latestTotalReportCases;
	private int diffFromPrevDay;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalReportCases() {
		return latestTotalReportCases;
	}
	public void setLatestTotalReportCases(int latestTotalReportCases) {
		this.latestTotalReportCases = latestTotalReportCases;
	}
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotalReportCases="
				+ latestTotalReportCases + "]";
	}

}
