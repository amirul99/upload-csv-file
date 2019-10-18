package hello.model;

import com.opencsv.bean.CsvBindByPosition;

public class CsvBean {
	
	@CsvBindByPosition(position = 0)
	private String chr;
	
	@CsvBindByPosition(position = 1)
	private String start;

	@CsvBindByPosition(position = 2)
	private String end;

	@CsvBindByPosition(position = 3)
	private String geneId;

	@CsvBindByPosition(position = 4)
	private String geneSysm;

	@CsvBindByPosition(position = 5)
	private String disorder;

	@CsvBindByPosition(position = 6)
	private String length;

	@CsvBindByPosition(position = 7)
	private String cnv;

	@CsvBindByPosition(position = 8)
	private String info;

	@CsvBindByPosition(position = 9)
	private String frequence;

	@CsvBindByPosition(position = 10)
	private String sampleId;

}
