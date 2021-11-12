package com.companydtk.covid.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.companydtk.covid.models.LocationStats;

@Service
public class CoronavirusDataService {
	
	public static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/archived_data/archived_time_series/time_series_19-covid-Confirmed_archived_0325.csv";
	
	private List<LocationStats> allStates = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStates = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(VIRUS_DATA_URL))
			.build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		//System.out.println(httpResponse.body());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStats = new LocationStats();
			locationStats.setState(record.get("Province/State"));
			locationStats.setCountry(record.get("Country/Region"));
			locationStats.setLatestTotalReportCases(Integer.parseInt(record.get(record.size()-2)));
			locationStats.setDiffFromPrevDay(Integer.parseInt(record.get(record.size()-2))-Integer.parseInt(record.get(record.size()-3)));
		    //String state = record.get("Province/State");
		    //System.out.println(state);
			System.out.println(locationStats);
			newStates.add(locationStats);
		}
		
		this.allStates = newStates;
	}

	public List<LocationStats> getAllStates() {
		return allStates;
	}

	public void setAllStates(List<LocationStats> allStates) {
		this.allStates = allStates;
	}
	
}
