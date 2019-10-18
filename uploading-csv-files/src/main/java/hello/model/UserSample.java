package hello.model;

import java.io.Serializable;

public class UserSample{
	
	
	/**
	 * 
	 */
	
	private String chr;
	private String start;
	private String end;
	private String geneId;
	private String geneSysm;
	private String disorder;
	private String length;
	private String cnv;
	private String info;
	private String frequence;
	private String sampleId;
		
	
	public UserSample() {
		
	}
		
	public UserSample(String chr, String start, String end, String geneId, String geneSysm, String disorder,
			String length, String cnv, String info, String frequence, String sampleId) {
		
		this.chr = chr;
		this.start = start;
		this.end = end;
		this.geneId = geneId;
		this.geneSysm = geneSysm;
		this.disorder = disorder;
		this.length = length;
		this.cnv = cnv;
		this.info = info;
		this.frequence = frequence;
		this.sampleId = sampleId;
	}

	public String getChr() {
		return chr;
	}

	public void setChr(String chr) {
		this.chr = chr;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getGeneId() {
		return geneId;
	}

	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	public String getGeneSysm() {
		return geneSysm;
	}

	public void setGeneSysm(String geneSysm) {
		this.geneSysm = geneSysm;
	}

	public String getDisorder() {
		return disorder;
	}

	public void setDisorder(String disorder) {
		this.disorder = disorder;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCnv() {
		return cnv;
	}

	public void setCnv(String cnv) {
		this.cnv = cnv;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	@Override
	public String toString() {
		return "UserSample [chr=" + chr + ", start=" + start + ", end=" + end + ", geneId=" + geneId + ", geneSysm="
				+ geneSysm + ", disorder=" + disorder + ", length=" + length + ", cnv=" + cnv + ", info=" + info
				+ ", frequence=" + frequence + ", sampleId=" + sampleId + "]";
	}
	
	






	
	
	
	
	
	
	
	
	

}
