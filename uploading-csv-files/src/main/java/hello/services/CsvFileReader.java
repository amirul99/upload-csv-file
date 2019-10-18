package hello.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import hello.model.UserSample;

@Service
public class CsvFileReader {

	public static ArrayList<UserSample> readCsv(String filePath) {
		ArrayList<UserSample> users = new ArrayList<UserSample>();
		// UserSample user = new UserSample();

		BufferedReader reader = null;

		try {
			// csv file containing data
			String strFile = filePath;
			CSVReader csvreader = new CSVReader(new FileReader(strFile));
			String[] nextLine;
			int lineNumber = 0;
			while ((nextLine = csvreader.readNext()) != null) {
				if (nextLine.length > 1) {
					UserSample user = new UserSample();
					user.setChr(nextLine[0]);
					user.setStart(nextLine[1]);
					user.setEnd(nextLine[2]);
					user.setGeneId(nextLine[3]);
					user.setGeneSysm(nextLine[4]);
					user.setDisorder(nextLine[5]);
					user.setLength(nextLine[6]);
					user.setCnv(nextLine[7]);

					user.setInfo(nextLine[8]);
					user.setFrequence(nextLine[9]);
					//user.setSampleId(sampleId);

					users.add(user);
				}

				lineNumber++;
				/*
				 * String str = nextLine[8].toString(); // System.out.println(nextLine[8]);
				 * String[] splt = str.split(";");
				 * 
				 * if (splt.length > 1) {
				 * 
				 * // System.out.println(splt.length); // System.out.println(splt[5]);
				 * 
				 * //System.out.println(splt[5]); String sampleId = splt[5]; String newSId;
				 * 
				 * 
				 * 
				 * }
				 */
				/*
				 * sampleId.replace("ABC", "001"); System.out.println(sampleId);
				 */

			}
			// System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;

	}

	public static void writeCsv(String filePath) {
		ArrayList<UserSample> users = readCsv(filePath);
		ArrayList<UserSample> userList = new ArrayList<UserSample>();
		String[] nextLine;
		String newSId="";
		String sampleId="";
		for (UserSample u : users) {
			String str = u.getInfo();
			String[] splt = str.split(";");
			String renameId = "";
			if (splt.length > 1) {
				sampleId = splt[5];
				if(sampleId.charAt(0)=='A') {
					newSId = sampleId.replaceAll("ABC", "001");
				}
				else if(sampleId.charAt(0)=='B') {
					newSId = sampleId.replaceAll("BAC", "002");
				}
				else if(sampleId.charAt(0)=='C') {
					newSId = sampleId.replaceAll("CBD", "ASD");
				}
				
				u.setSampleId(newSId);
			}
			//u.setSampleId(sampleId);
			
			userList.add(u);
			System.out.println(u.getSampleId());
		}
		
		// System.out.println(nextLine[8]);
		
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);

			// fileWriter.append("Chr,Start,End,GeneID,GeneSym,Disorders(>=25%-overlap),Length,CNV,info,freq.,SampleID\n");
			StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(fileWriter).build();
			beanToCsv.write(userList);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

}
